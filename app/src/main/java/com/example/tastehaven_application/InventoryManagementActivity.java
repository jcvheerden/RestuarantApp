package com.example.tastehaven_application;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class InventoryManagementActivity extends AppCompatActivity {

    private Button btnViewInventory, btnAddItem, btnUpdateItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_management);

        btnViewInventory = findViewById(R.id.btnViewInventory);
        btnAddItem = findViewById(R.id.btnAddInventoryItem);
        btnUpdateItem = findViewById(R.id.btnUpdateInventoryItem);

        btnViewInventory.setOnClickListener(v ->
                startActivity(new Intent(this, ViewInventoryActivity.class)));

        btnAddItem.setOnClickListener(v ->
                startActivity(new Intent(this, AddInventoryItemActivity.class)));

        btnUpdateItem.setOnClickListener(v ->
                startActivity(new Intent(this, UpdateInventoryItemActivity.class)));
    }
}

