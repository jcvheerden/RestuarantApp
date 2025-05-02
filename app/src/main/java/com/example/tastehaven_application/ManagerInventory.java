package com.example.tastehaven_application;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class ManagerInventory extends AppCompatActivity {

    private Button btnViewInventory, btnUpdateStock, btnReorderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);

        btnViewInventory = findViewById(R.id.btnViewInventory);
        btnUpdateStock = findViewById(R.id.btnUpdateInventoryItem);
        btnReorderItems = findViewById(R.id.btnAddInventoryItem);

        btnViewInventory.setOnClickListener(v -> openViewInventoryActivity());
        btnUpdateStock.setOnClickListener(v -> openUpdateStockActivity());
        btnReorderItems.setOnClickListener(v -> openReorderItemsActivity());
    }

    private void openViewInventoryActivity() {
        // Transition to View Inventory Activity
    }

    private void openUpdateStockActivity() {
        // Transition to Update Stock Activity
    }

    private void openReorderItemsActivity() {
        // Transition to Reorder Items Activity
    }
}