package com.example.tastehaven_application;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

import java.util.*;

public class TableReservationActivity extends AppCompatActivity {

    private Spinner availableSlotsSpinner;
    private Button reserveButton;
    private DatabaseReference reservationsRef, usersRef;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_reservation);

        availableSlotsSpinner = findViewById(R.id.availableSlotsSpinner);
        reserveButton = findViewById(R.id.reserveButton);

        // For demo purposes, we assume the userId is fetched from the current logged-in user
        userId = "dummy_user_id";  // You can replace this with actual user authentication

        reservationsRef = FirebaseDatabase.getInstance().getReference("reservations");
        usersRef = FirebaseDatabase.getInstance().getReference("users");

        loadAvailableSlots();

        reserveButton.setOnClickListener(v -> reserveTable());
    }

    private void loadAvailableSlots() {
        // Simulate some available slots for the purpose of this demo
        List<String> availableSlots = new ArrayList<>();
        availableSlots.add("12:00 PM");
        availableSlots.add("2:00 PM");
        availableSlots.add("4:00 PM");
        availableSlots.add("6:00 PM");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, availableSlots);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        availableSlotsSpinner.setAdapter(adapter);
    }

    private void reserveTable() {
        String selectedSlot = (String) availableSlotsSpinner.getSelectedItem();
        int numberOfGuests = 2;  // You can prompt the user to input the number of guests

        String reservationId = reservationsRef.push().getKey();
        Reservation newReservation = new Reservation(reservationId, userId, selectedSlot, numberOfGuests, "pending");

        reservationsRef.child(reservationId).setValue(newReservation).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(TableReservationActivity.this, "Reservation successful", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(TableReservationActivity.this, "Reservation failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class Reservation {
        public String reservation_id;
        public String user_id;
        public String reservation_time;
        public int number_of_guests;
        public String status;

        public Reservation() {}

        public Reservation(String reservation_id, String user_id, String reservation_time, int number_of_guests, String status) {
            this.reservation_id = reservation_id;
            this.user_id = user_id;
            this.reservation_time = reservation_time;
            this.number_of_guests = number_of_guests;
            this.status = status;
        }
    }
}
