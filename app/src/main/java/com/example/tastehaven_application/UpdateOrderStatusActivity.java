package com.example.tastehaven_application;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

public class UpdateOrderStatusActivity extends AppCompatActivity {

    private Button btnPrepare, btnReady;
    private TextView orderDetailsTextView;
    private Order currentOrder;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order_status);

        btnPrepare = findViewById(R.id.btnPrepare);
        btnReady = findViewById(R.id.btnReady);
        orderDetailsTextView = findViewById(R.id.orderDetailsTextView);

        ordersRef = FirebaseDatabase.getInstance().getReference("orders");

        // Assume that currentOrder is passed via intent (order ID or some identifier)
        String orderId = getIntent().getStringExtra("order_id");
        fetchOrder(orderId);

        btnPrepare.setOnClickListener(v -> updateOrderStatus("Preparing"));
        btnReady.setOnClickListener(v -> updateOrderStatus("Ready"));
    }

    private void fetchOrder(String orderId) {
        ordersRef.child(orderId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentOrder = snapshot.getValue(Order.class);
                if (currentOrder != null) {
                    orderDetailsTextView.setText("Order ID: " + currentOrder.getOrder_id() +
                            "\nTable Number: " + currentOrder.getTable_number() +
                            "\nStatus: " + currentOrder.getStatus());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateOrderStatusActivity.this, "Failed to fetch order.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateOrderStatus(String status) {
        if (currentOrder != null) {
            currentOrder.setStatus(status);
            ordersRef.child(currentOrder.getOrder_id()).setValue(currentOrder)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateOrderStatusActivity.this, "Order status updated to " + status, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateOrderStatusActivity.this, "Failed to update status", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
