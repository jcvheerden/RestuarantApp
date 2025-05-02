package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {

    private EditText nameEditText, priceEditText, descriptionEditText, imageUrlEditText;
    private Button addButton;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mMenuRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mDatabase = FirebaseDatabase.getInstance();
        mMenuRef = mDatabase.getReference("menu");

        nameEditText = findViewById(R.id.nameEditText);
        priceEditText = findViewById(R.id.priceEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        imageUrlEditText = findViewById(R.id.imageUrlEditText);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenuItem();
            }
        });
    }

    private void addMenuItem() {
        String name = nameEditText.getText().toString().trim();
        String priceStr = priceEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String imageUrl = imageUrlEditText.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty() || description.isEmpty() || imageUrl.isEmpty()) {
            Toast.makeText(MenuActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        String id = mMenuRef.push().getKey();

        MenuItem menuItem = new MenuItem(id, name, price, description, imageUrl);
        mMenuRef.child(id).setValue(menuItem);

        Toast.makeText(MenuActivity.this, "Menu item added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
