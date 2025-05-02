package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerDashboardActivity extends AppCompatActivity {

    private Button reserveButton, viewMenuButton, giveFeedbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        reserveButton = findViewById(R.id.reserveButton);
        viewMenuButton = findViewById(R.id.viewMenuButton);
        giveFeedbackButton = findViewById(R.id.giveFeedbackButton);

        // Navigate to reservation activity
        reserveButton.setOnClickListener(v -> startActivity(new Intent(CustomerDashboardActivity.this, ReserveTableActivity.class)));

        // Navigate to menu activity
        viewMenuButton.setOnClickListener(v -> startActivity(new Intent(CustomerDashboardActivity.this, MenuActivity.class)));

        // Navigate to feedback activity
        giveFeedbackButton.setOnClickListener(v -> startActivity(new Intent(CustomerDashboardActivity.this, FeedbackActivity.class)));
    }
}
