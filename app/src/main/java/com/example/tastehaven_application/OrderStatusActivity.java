package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.firebase.database.*;
import java.util.*;

public class OrderStatusActivity extends AppCompatActivity {

    private RecyclerView ordersRecyclerView;
    private OrderAdapter orderAdapter;
    private ArrayList<Order> ordersList;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordersList = new ArrayList<>();
        orderAdapter = new OrderAdapter(ordersList);
        ordersRecyclerView.setAdapter(orderAdapter);

        ordersRef = FirebaseDatabase.getInstance().getReference("orders");

        // Fetch orders from Firebase
        fetchOrders();

        // Listen for real-time updates
        listenForOrderUpdates();
    }

    private void fetchOrders() {
        ordersRef.orderByChild("order_time").limitToLast(20).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ordersList.clear();
                for (DataSnapshot orderSnapshot : snapshot.getChildren()) {
                    Order order = orderSnapshot.getValue(Order.class);
                    if (order != null) {
                        ordersList.add(order);
                    }
                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrderStatusActivity.this, "Failed to load orders.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listenForOrderUpdates() {
        ordersRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChildName) {
                Order order = snapshot.getValue(Order.class);
                if (order != null) {
                    ordersList.add(order);
                    orderAdapter.notifyItemInserted(ordersList.size() - 1);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, String previousChildName) {
                Order updatedOrder = snapshot.getValue(Order.class);
                if (updatedOrder != null) {
                    for (int i = 0; i < ordersList.size(); i++) {
                        if (ordersList.get(i).getOrder_id().equals(updatedOrder.getOrder_id())) {
                            ordersList.set(i, updatedOrder);
                            orderAdapter.notifyItemChanged(i);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Order removedOrder = snapshot.getValue(Order.class);
                if (removedOrder != null) {
                    for (int i = 0; i < ordersList.size(); i++) {
                        if (ordersList.get(i).getOrder_id().equals(removedOrder.getOrder_id())) {
                            ordersList.remove(i);
                            orderAdapter.notifyItemRemoved(i);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, String previousChildName) {
                // Not needed for this use case
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrderStatusActivity.this, "Failed to listen for order updates.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
