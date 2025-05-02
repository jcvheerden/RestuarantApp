package com.example.tastehaven_application;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ReservationActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private EditText numberOfGuests;
    private Button reserveButton;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReservationRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        mDatabase = FirebaseDatabase.getInstance();
        mReservationRef = mDatabase.getReference("reservations");

        datePicker = findViewById(R.id.datePicker);
        numberOfGuests = findViewById(R.id.numberOfGuests);
        reserveButton = findViewById(R.id.reserveButton);

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();
                String guests = numberOfGuests.getText().toString();

                if (guests.isEmpty()) {
                    Toast.makeText(ReservationActivity.this, "Please enter number of guests", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Reservation object to store
                HashMap<String, Object> reservation = new HashMap<>();
                reservation.put("date", date);
                reservation.put("guests", guests);
                reservation.put("status", "pending");

                // Add reservation to Firebase
                mReservationRef.push().setValue(reservation)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ReservationActivity.this, "Reservation made successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ReservationActivity.this, "Failed to make reservation", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
