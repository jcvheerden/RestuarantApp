package com.example.tastehaven_application;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class AdminDashboardActivity extends AppCompatActivity {

    private Button btnSalesTracking;
    private Button btnStaffReports;
    private Button btnRoleManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnSalesTracking = findViewById(R.id.btnSalesTracking);
        btnStaffReports = findViewById(R.id.btnStaffReports);
        btnRoleManagement = findViewById(R.id.btnRoleManagement);

        btnSalesTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, SalesReportActivity.class);
                startActivity(intent);
            }
        });

        btnStaffReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, StaffReportActivity.class);
                startActivity(intent);
            }
        });

        btnRoleManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, RoleManagementActivity.class);
                startActivity(intent);
            }
        });
    }
}

