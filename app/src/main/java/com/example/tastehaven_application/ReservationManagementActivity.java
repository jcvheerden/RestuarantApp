package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class ReservationManagementActivity extends AppCompatActivity {

    private Button btnViewReservations, btnUpdateReservation, btnCancelReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_management);

        btnViewReservations = findViewById(R.id.btnViewReservations);
        btnUpdateReservation = findViewById(R.id.btnUpdateReservation);
        btnCancelReservation = findViewById(R.id.btnCancelReservation);

        btnViewReservations.setOnClickListener(v ->
                startActivity(new Intent(this, ReservationView.class)));

        btnUpdateReservation.setOnClickListener(v ->
                startActivity(new Intent(this, UpdateReservationActivity.class)));

        btnCancelReservation.setOnClickListener(v ->
                startActivity(new Intent(this, ReservationCancel.class)));
    }
}