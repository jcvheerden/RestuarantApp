package com.example.tastehaven_application;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

public class ReservationCancel extends AppCompatActivity {

    private EditText etReservationId;
    private Button btnCancelReservation;
    private DatabaseReference reservationRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);

        etReservationId = findViewById(R.id.etCancelReservationId);
        btnCancelReservation = findViewById(R.id.btnCancelReservation);
        reservationRef = FirebaseDatabase.getInstance().getReference("reservations");

        btnCancelReservation.setOnClickListener(v -> {
            String id = etReservationId.getText().toString().trim();

            if (TextUtils.isEmpty(id)) {
                Toast.makeText(this, "Enter Reservation ID", Toast.LENGTH_SHORT).show();
                return;
            }

            reservationRef.child(id).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Reservation Cancelled", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to Cancel", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}