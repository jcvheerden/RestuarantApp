package com.example.tastehaven_application;



import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;


public class SalesReportActivity extends AppCompatActivity {

    private TextView tvTotalSales;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);

        tvTotalSales = findViewById(R.id.tvTotalSales);
        ordersRef = FirebaseDatabase.getInstance().getReference("orders");

        calculateTotalSales();
    }

    private void calculateTotalSales() {
        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                double total = 0;
                for (DataSnapshot order : snapshot.getChildren()) {
                    if (order.child("status").getValue(String.class).equals("served")) {
                        for (DataSnapshot item : order.child("order_items").getChildren()) {
                            double price = item.child("price").getValue(Double.class);
                            int qty = item.child("quantity").getValue(Integer.class);
                            total += price * qty;
                        }
                    }
                }
                tvTotalSales.setText("Total Sales: $" + total);
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }
}

