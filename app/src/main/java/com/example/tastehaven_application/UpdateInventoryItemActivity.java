package com.example.tastehaven_application;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateInventoryItemActivity extends AppCompatActivity {

    private EditText etItemId, etNewQuantity, etNewThreshold;
    private Button btnUpdate;
    private DatabaseReference inventoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory_item);

        etItemId = findViewById(R.id.etInventoryId);
        etNewQuantity = findViewById(R.id.etNewQuantity);
        etNewThreshold = findViewById(R.id.etNewThreshold);
        btnUpdate = findViewById(R.id.btnUpdateInventory);

        inventoryRef = FirebaseDatabase.getInstance().getReference("inventory");

        btnUpdate.setOnClickListener(v -> {
            String id = etItemId.getText().toString().trim();
            String quantityStr = etNewQuantity.getText().toString().trim();
            String thresholdStr = etNewThreshold.getText().toString().trim();
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());

            if (TextUtils.isEmpty(id)) {
                Toast.makeText(this, "Item ID is required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!TextUtils.isEmpty(quantityStr)) {
                inventoryRef.child(id).child("quantity_in_stock").setValue(Integer.parseInt(quantityStr));
            }

            if (!TextUtils.isEmpty(thresholdStr)) {
                inventoryRef.child(id).child("reorder_threshold").setValue(Integer.parseInt(thresholdStr));
            }

            inventoryRef.child(id).child("last_updated").setValue(timestamp);
            Toast.makeText(this, "Inventory Updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

