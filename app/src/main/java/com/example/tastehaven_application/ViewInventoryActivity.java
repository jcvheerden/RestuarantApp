package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;


import java.util.ArrayList;

public class ViewInventoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InventoryAdapter adapter;
    private ArrayList<InventoryItem> inventoryList;
    private DatabaseReference inventoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);

        recyclerView = findViewById(R.id.recyclerViewInventory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        inventoryList = new ArrayList<>();
        adapter = new InventoryAdapter(inventoryList);
        recyclerView.setAdapter(adapter);

        inventoryRef = FirebaseDatabase.getInstance().getReference("inventory");

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
                Toast.makeText(ViewInventoryActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
