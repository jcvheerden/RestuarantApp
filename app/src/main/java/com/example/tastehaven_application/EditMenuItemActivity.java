package com.example.tastehaven_application;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditMenuItemActivity extends AppCompatActivity {

    private EditText etItemId, etNewName, etNewPrice;
    private Button btnUpdateItem;
    private DatabaseReference menuRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_item);

        etItemId = findViewById(R.id.etEditItemId);
        etNewName = findViewById(R.id.etEditName);
        etNewPrice = findViewById(R.id.etEditPrice);
        btnUpdateItem = findViewById(R.id.btnUpdateItem);

        menuRef = FirebaseDatabase.getInstance().getReference("menu_items");

        btnUpdateItem.setOnClickListener(v -> {
            String id = etItemId.getText().toString().trim();
            String name = etNewName.getText().toString().trim();
            String priceStr = etNewPrice.getText().toString().trim();

            if (TextUtils.isEmpty(id) || TextUtils.isEmpty(name) || TextUtils.isEmpty(priceStr)) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceStr);
            menuRef.child(id).child("name").setValue(name);
            menuRef.child(id).child("price").setValue(price);
            Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}