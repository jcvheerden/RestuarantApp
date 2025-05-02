package com.example.tastehaven_application;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RemoveMenuItemActivity extends AppCompatActivity {

    private EditText etRemoveItemId;
    private Button btnRemoveItem;
    private DatabaseReference menuRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_menu_item);

        etRemoveItemId = findViewById(R.id.etRemoveItemId);
        btnRemoveItem = findViewById(R.id.btnRemoveItem);
        menuRef = FirebaseDatabase.getInstance().getReference("menu_items");

        btnRemoveItem.setOnClickListener(v -> {
            String id = etRemoveItemId.getText().toString().trim();

            if (TextUtils.isEmpty(id)) {
                Toast.makeText(this, "Item ID required", Toast.LENGTH_SHORT).show();
                return;
            }

            menuRef.child(id).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to remove item", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
