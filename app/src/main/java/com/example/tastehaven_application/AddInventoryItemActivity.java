package com.example.tastehaven_application;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddInventoryItemActivity extends AppCompatActivity {

    private EditText etItemName, etQuantity, etThreshold;
    private Button btnAdd;
    private DatabaseReference inventoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory_item);

        etItemName = findViewById(R.id.etInventoryName);
        etQuantity = findViewById(R.id.etInventoryQuantity);
        etThreshold = findViewById(R.id.etReorderThreshold);
        btnAdd = findViewById(R.id.btnAddInventory);

        inventoryRef = FirebaseDatabase.getInstance().getReference("inventory");

        btnAdd.setOnClickListener(v -> {
            String name = etItemName.getText().toString().trim();
            String quantityStr = etQuantity.getText().toString().trim();
            String thresholdStr = etThreshold.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(quantityStr) || TextUtils.isEmpty(thresholdStr)) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(quantityStr);
            int threshold = Integer.parseInt(thresholdStr);
            String id = inventoryRef.push().getKey();
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());

            InventoryItem item = new InventoryItem(id, name, quantity, threshold, timestamp);
            if (id != null) {
                inventoryRef.child(id).setValue(item).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Inventory item added", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }
}