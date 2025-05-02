package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SalesReportActivity extends AppCompatActivity {

    private TextView totalSalesTextView, totalOrdersTextView, completedOrdersTextView;
    private FirebaseDatabase database;
    private DatabaseReference ordersRef;
    private double totalSales = 0.0;
    private int totalOrders = 0;
    private int completedOrders = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);

        totalSalesTextView = findViewById(R.id.totalSalesTextView);
        totalOrdersTextView = findViewById(R.id.totalOrdersTextView);
        completedOrdersTextView = findViewById(R.id.completedOrdersTextView);

        database = FirebaseDatabase.getInstance();
        ordersRef = database.getReference("orders");

        fetchSalesData();
    }

    private void fetchSalesData() {
        // Get today's date in the format needed to query Firebase (YYYY-MM-DD)
        String todayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        // Query Firebase for orders today
        ordersRef.orderByChild("order_time").startAt(todayDate + " 00:00:00")
                .endAt(todayDate + " 23:59:59")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        totalSales = 0.0;
                        totalOrders = 0;
                        completedOrders = 0;

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Order order = snapshot.getValue(Order.class);

                            // Calculate total sales and count orders
                            if (order != null) {
                                totalOrders++;

                                // Check if the order is completed (status: "served")
                                if ("served".equals(order.getStatus())) {
                                    completedOrders++;

                                    for (DataSnapshot itemSnapshot : snapshot.child("order_items").getChildren()) {
                                        OrderItem orderItem = itemSnapshot.getValue(OrderItem.class);
                                        if (orderItem != null) {
                                            totalSales += orderItem.getQuantity() * orderItem.getPrice();
                                        }
                                    }
                                }
                            }
                        }

                        // Update the UI with the fetched data
                        updateUI();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(SalesReportActivity.this, "Failed to fetch sales data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI() {
        totalSalesTextView.setText(String.format(Locale.getDefault(), "Total Sales: $%.2f", totalSales));
        totalOrdersTextView.setText("Total Orders: " + totalOrders);
        completedOrdersTextView.setText("Completed Orders: " + completedOrders);
    }
}
