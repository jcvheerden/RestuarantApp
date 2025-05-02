package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button customerButton;
    private Button waiterButton;
    private Button managerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        customerButton = findViewById(R.id.customerButton);
        waiterButton = findViewById(R.id.waiterButton);
        managerButton = findViewById(R.id.managerButton);

        // Set onClickListeners for each button
        customerButton.setOnClickListener(v -> navigateToRole("customer"));
        waiterButton.setOnClickListener(v -> navigateToRole("waiter"));
        managerButton.setOnClickListener(v -> navigateToRole("manager"));
    }

    // Helper method to navigate based on the role
    private void navigateToRole(String role) {
        Intent intent;

        switch (role) {
            case "customer":
                intent = new Intent(MainActivity.this, CustomerDashboardActivity.class);
                break;
            case "waiter":
                intent = new Intent(MainActivity.this, OrderActivity.class);
                break;
            case "manager":
                intent = new Intent(MainActivity.this, ManagerDashboardActivity.class);
                break;
            default:
                Toast.makeText(this, "Invalid role", Toast.LENGTH_SHORT).show();
                return;
        }

        intent.putExtra("userRole", role);
        startActivity(intent);
    }
}
