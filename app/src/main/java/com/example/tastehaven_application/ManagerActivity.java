package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ManagerActivity extends AppCompatActivity {

    private Button btnManageMenu, btnManageReservations, btnViewReports, btnInventory, btnAssignStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        // Initialize Buttons
        btnManageMenu = findViewById(R.id.btnManageMenu);
        btnManageReservations = findViewById(R.id.btnManageReservations);
        btnViewReports = findViewById(R.id.btnViewReports);
        btnInventory = findViewById(R.id.btnInventory);
        btnAssignStaff = findViewById(R.id.btnAssignStaff);

        // Set onClickListeners for each button
        btnManageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuActivity();
            }
        });

        btnManageReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReservationActivity();
            }
        });

        btnViewReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReportsActivity();
            }
        });

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInventoryActivity();
            }
        });

        btnAssignStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStaffActivity();
            }
        });
    }

    // Method to navigate to the Menu Management activity
    private void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the Reservations Management activity
    private void openReservationActivity() {
        Intent intent = new Intent(this, ReservationManagementActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the Reports activity
    private void openReportsActivity() {
        Intent intent = new Intent(this, AdminDashboardActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the Inventory Management activity
    private void openInventoryActivity() {
        Intent intent = new Intent(this, InventoryManagementActivity.class);
        startActivity(intent);
    }

    // Method to navigate to the Staff Management activity
    private void openStaffActivity() {
        Intent intent = new Intent(this, AdminDashboardActivity.class);
        startActivity(intent);
    }
}
