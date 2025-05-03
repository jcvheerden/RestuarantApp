package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import com.google.firebase.database.*;

import java.util.ArrayList;

public class WaiterMenuActivity extends AppCompatActivity {

    private RecyclerView menuRecyclerView;
    private MenuAdapter menuAdapter;
    private ArrayList<MenuItem> menuItems;
    private Button btnSendOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_menu);

        menuRecyclerView = findViewById(R.id.waiterMenuRecyclerView);
        btnSendOrder = findViewById(R.id.btnSendOrder);

        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuItems = new ArrayList<>();
        menuAdapter = new MenuAdapter(menuItems);
        menuRecyclerView.setAdapter(menuAdapter);

        // Load data from Firebase
        DatabaseReference menuRef = FirebaseDatabase.getInstance().getReference("menu_items");
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                menuItems.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    MenuItem item = snap.getValue(MenuItem.class);
                    if (item != null && item.isAvailability()) {
                        menuItems.add(item);
                    }
                }
                menuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WaiterMenuActivity.this, "Failed to load menu.", Toast.LENGTH_SHORT).show();
            }
        });

        btnSendOrder.setOnClickListener(v -> {
            ArrayList<MenuItem> selectedItems = menuAdapter.getSelectedItems();
            if (selectedItems.isEmpty()) {
                Toast.makeText(WaiterMenuActivity.this, "Select at least one item", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(WaiterMenuActivity.this, OrderActivity.class);
                intent.putExtra("selectedItems", selectedItems);
                startActivity(intent);
            }
        });
    }
}
