package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class DashboardActivity extends AppCompatActivity {

    private Button viewOrdersButton, viewReservationsButton, viewInventoryButton;
    private ListView dashboardListView;
    private ArrayAdapter<String> dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbaord);

        viewOrdersButton = findViewById(R.id.viewOrdersButton);
        viewReservationsButton = findViewById(R.id.viewReservationsButton);
        viewInventoryButton = findViewById(R.id.viewInventoryButton);
        dashboardListView = findViewById(R.id.dashboardListView);

        viewOrdersButton.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, ManagerOrdersActivity.class)));
        viewReservationsButton.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, ManagerReservationsActivity.class)));
        viewInventoryButton.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, ManagerInventoryActivity.class)));

        // Example of dashboard data display
        ArrayList<String> dashboardItems = new ArrayList<>(Arrays.asList("Orders Overview", "Reservations Overview", "Inventory Overview"));
        dashboardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dashboardItems);
        dashboardListView.setAdapter(dashboardAdapter);
    }
}

