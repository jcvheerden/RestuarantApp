package com.example.tastehaven_application;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddMenuItemActivity extends AppCompatActivity {

    private EditText etName, etDescription, etPrice;
    private Button btnAddItem;
    private DatabaseReference menuRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);

        etName = findViewById(R.id.etMenuName);
        etDescription = findViewById(R.id.etMenuDescription);
        etPrice = findViewById(R.id.etMenuPrice);
        btnAddItem = findViewById(R.id.btnAddMenuItem);

        menuRef = FirebaseDatabase.getInstance().getReference("menu_items");

        btnAddItem.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String desc = etDescription.getText().toString().trim();
            String priceStr = etPrice.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(priceStr)) {
                Toast.makeText(this, "Name and Price required", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceStr);
            String id = menuRef.push().getKey();

            MenuItem item = new MenuItem(id, name, desc, price, true);
            if (id != null) {
                menuRef.child(id).setValue(item).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Error adding item", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
