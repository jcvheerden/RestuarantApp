package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InventoryAdapter adapter;
    private List<InventoryItem> inventoryList = new ArrayList<>();
    private DatabaseReference inventoryRef;
    private Button backToDashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        recyclerView = findViewById(R.id.recyclerViewInventory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backToDashboardButton = findViewById(R.id.backToDashboardButton);
        backToDashboardButton.setOnClickListener(v -> {
            Intent intent = new Intent(InventoryActivity.this, ManagerDashboardActivity.class);
            startActivity(intent);
            finish();
        });

        inventoryRef = FirebaseDatabase.getInstance().getReference("inventory");

        adapter = new InventoryAdapter(inventoryList);
        recyclerView.setAdapter(adapter);

        loadInventoryItems();
        checkLowStock();
    }

    private void loadInventoryItems() {
        inventoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                inventoryList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    InventoryItem item = data.getValue(InventoryItem.class);
                    inventoryList.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(InventoryActivity.this, "Failed to load inventory", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkLowStock() {
        inventoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnap : snapshot.getChildren()) {
                    InventoryItem item = itemSnap.getValue(InventoryItem.class);
                    if (item != null && item.quantity_in_stock <= item.reorder_threshold) {
                        Toast.makeText(InventoryActivity.this, "Low stock: " + item.item_name, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(InventoryActivity.this, "Stock check failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
