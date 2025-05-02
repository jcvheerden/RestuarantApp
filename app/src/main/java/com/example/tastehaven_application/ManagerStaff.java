package com.example.tastehaven_application;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class ManagerStaff extends AppCompatActivity {

    private Button btnAssignStaff, btnViewStaffReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerstaff);

        btnAssignStaff = findViewById(R.id.btnAssignStaff);
        btnViewStaffReport = findViewById(R.id.btnViewStaffReport);

        btnAssignStaff.setOnClickListener(v -> openAssignStaffActivity());
        btnViewStaffReport.setOnClickListener(v -> openViewStaffReportActivity());
    }

    private void openAssignStaffActivity() {
        // Transition to Assign Staff Activity
    }

    private void openViewStaffReportActivity() {
        // Transition to View Staff Report Activity
    }
}