package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderActivity extends AppCompatActivity {

    private EditText tableNumberEditText;
    private RecyclerView selectedItemsRecyclerView;
    private SelectedItemsAdapter selectedItemsAdapter;
    private ArrayList<MenuItem> selectedItems;
    private Button btnSubmitOrder;

    private DatabaseReference ordersRef, orderItemsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tableNumberEditText = findViewById(R.id.editTextTableNumber);
        selectedItemsRecyclerView = findViewById(R.id.recyclerViewSelectedItems);
        btnSubmitOrder = findViewById(R.id.btnSubmitOrder);

        ordersRef = FirebaseDatabase.getInstance().getReference("orders");
        orderItemsRef = FirebaseDatabase.getInstance().getReference("order_items");

        selectedItems = (ArrayList<MenuItem>) getIntent().getSerializableExtra("selectedItems");

        selectedItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        selectedItemsAdapter = new SelectedItemsAdapter(selectedItems);
        selectedItemsRecyclerView.setAdapter(selectedItemsAdapter);

        btnSubmitOrder.setOnClickListener(v -> {
            String tableNumber = tableNumberEditText.getText().toString().trim();

            if (tableNumber.isEmpty()) {
                Toast.makeText(this, "Enter a table number", Toast.LENGTH_SHORT).show();
                return;
            }

            String orderId = ordersRef.push().getKey();
            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

            Order order = new Order(orderId, tableNumber, "waiter_001", currentTime, "received");

            ordersRef.child(orderId).setValue(order).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (MenuItem item : selectedItems) {
                        String orderItemId = orderItemsRef.push().getKey();
                        OrderItem orderItem = new OrderItem(orderItemId, orderId, item.getItem_id(), 1, "");
                        orderItemsRef.child(orderItemId).setValue(orderItem);
                    }
                    Toast.makeText(this, "Order sent to kitchen!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to place order", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
