package com.example.tastehaven_application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class ManagerReservation extends AppCompatActivity {

    private Button btnViewReservations, btnUpdateReservation, btnCancelReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_management);

        btnViewReservations = findViewById(R.id.btnViewReservations);
        btnUpdateReservation = findViewById(R.id.btnUpdateReservation);
        btnCancelReservation = findViewById(R.id.btnCancelReservation);

        btnViewReservations.setOnClickListener(v -> openViewReservationsActivity());
        btnUpdateReservation.setOnClickListener(v -> openUpdateReservationActivity());
        btnCancelReservation.setOnClickListener(v -> openCancelReservationActivity());
    }

    private void openViewReservationsActivity() {
        // Transition to View Reservations Activity
    }

    private void openUpdateReservationActivity() {
        // Transition to Update Reservation Activity
    }

    private void openCancelReservationActivity() {
        // Transition to Cancel Reservation Activity
    }
}