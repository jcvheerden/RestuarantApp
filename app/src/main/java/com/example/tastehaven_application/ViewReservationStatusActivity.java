package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

import java.util.*;

public class ViewReservationStatusActivity extends AppCompatActivity {

    private TextView reservationStatusTextView;
    private DatabaseReference reservationsRef;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation_status);

        reservationStatusTextView = findViewById(R.id.reservationStatusTextView);

        // For demo purposes, we assume the userId is fetched from the current logged-in user
        userId = "dummy_user_id";  // You can replace this with actual user authentication

        reservationsRef = FirebaseDatabase.getInstance().getReference("reservations");

        fetchReservationStatus();
    }

    private void fetchReservationStatus() {
        reservationsRef.orderByChild("user_id").equalTo(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot reservationSnapshot : snapshot.getChildren()) {
                                String status = reservationSnapshot.child("status").getValue(String.class);
                                reservationStatusTextView.setText("Reservation Status: " + status);
                            }
                        } else {
                            reservationStatusTextView.setText("No reservations found.");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ViewReservationStatusActivity.this, "Error fetching reservation status", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
