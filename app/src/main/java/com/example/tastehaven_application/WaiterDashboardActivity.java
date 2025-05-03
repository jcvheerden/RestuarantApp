package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WaiterDashboardActivity extends AppCompatActivity {

    private Button btnPlaceOrder, btnViewOrders, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_dashboard);

        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnViewOrders = findViewById(R.id.btnViewOrders);
        btnLogout = findViewById(R.id.btnLogout);

        btnPlaceOrder.setOnClickListener(v ->
                startActivity(new Intent(this, MenuSelectionActivity.class)) // or your own class
        );

        btnViewOrders.setOnClickListener(v ->
                startActivity(new Intent(this, OrderStatusActivity.class)) // where they see current orders
        );

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
