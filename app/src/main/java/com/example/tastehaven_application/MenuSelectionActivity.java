package com.example.tastehaven_application;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.firebase.database.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MenuSelectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private ArrayList<MenuItemModel> menuItems;
    private DatabaseReference menuRef, orderRef;

    private EditText edtTableNumber;
    private Button btnSubmitOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_selection);

        edtTableNumber = findViewById(R.id.edtTableNumber);
        btnSubmitOrder = findViewById(R.id.btnSubmitOrder);
        recyclerView = findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        menuItems = new ArrayList<>();
        adapter = new MenuAdapter(menuItems);
        recyclerView.setAdapter(adapter);

        menuRef = FirebaseDatabase.getInstance().getReference("menu_items");
        orderRef = FirebaseDatabase.getInstance().getReference("orders");

        loadMenuItems();

        btnSubmitOrder.setOnClickListener(v -> submitOrder());
    }

    private void loadMenuItems() {
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                menuItems.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    MenuItemModel item = snap.getValue(MenuItemModel.class);
                    menuItems.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MenuSelectionActivity.this, "Failed to load menu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitOrder() {
        String tableNumber = edtTableNumber.getText().toString().trim();
        if (tableNumber.isEmpty()) {
            Toast.makeText(this, "Enter table number", Toast.LENGTH_SHORT).show();
            return;
        }

        String orderId = orderRef.push().getKey();
        String orderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("order_id", orderId);
        orderMap.put("table_number", tableNumber);
        orderMap.put("waiter_id", "waiter123"); // You can dynamically fetch this if needed
        orderMap.put("order_time", orderTime);
        orderMap.put("status", "received");

        orderRef.child(orderId).setValue(orderMap);

        // Add items
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("order_items");
        for (MenuItemModel item : menuItems) {
            if (item.getSelectedQuantity() > 0) {
                String itemId = itemsRef.push().getKey();
                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("order_item_id", itemId);
                itemMap.put("order_id", orderId);
                itemMap.put("item_id", item.getItem_id());
                itemMap.put("quantity", item.getSelectedQuantity());
                itemMap.put("notes", "");
                itemsRef.child(itemId).setValue(itemMap);
            }
        }

        Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
