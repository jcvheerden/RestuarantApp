package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class ManagerDashboardActivity extends AppCompatActivity {

    private Button viewOrdersButton, viewReservationsButton, viewInventoryButton, manageMenuButton, viewSalesReportButton;
    private FirebaseDatabase database;
    private DatabaseReference ordersRef, reservationsRef, inventoryRef, menuRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        viewOrdersButton = findViewById(R.id.viewOrdersButton);
        viewReservationsButton = findViewById(R.id.viewReservationsButton);
        viewInventoryButton = findViewById(R.id.viewInventoryButton);
        manageMenuButton = findViewById(R.id.manageMenuButton);
        viewSalesReportButton = findViewById(R.id.viewSalesReportButton);

        database = FirebaseDatabase.getInstance();
        ordersRef = database.getReference("orders");
        reservationsRef = database.getReference("reservations");
        inventoryRef = database.getReference("inventory");
        menuRef = database.getReference("menu_items");

        viewOrdersButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, OrderActivity.class));
        });

        viewReservationsButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, ReservationActivity.class));
        });

        viewInventoryButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, InventoryActivity.class));
        });

        manageMenuButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, ManagerMenuActivity.class));
        });

        // Sales Report Button Click
        viewSalesReportButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, SalesReportActivity.class));
        });
    }
}
