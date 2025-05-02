package com.example.tastehaven_application;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;


public class UpdateReservationActivity extends AppCompatActivity {

    private EditText etReservationId;
    private Spinner statusSpinner;
    private Button btnUpdate;
    private DatabaseReference reservationRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reservation);

        etReservationId = findViewById(R.id.etReservationId);
        statusSpinner = findViewById(R.id.statusSpinner);
        btnUpdate = findViewById(R.id.btnUpdateReservation);
        reservationRef = FirebaseDatabase.getInstance().getReference("reservations");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"pending", "confirmed", "cancelled"});
        statusSpinner.setAdapter(adapter);

        btnUpdate.setOnClickListener(v -> {
            String id = etReservationId.getText().toString().trim();
            String status = statusSpinner.getSelectedItem().toString();

            if (TextUtils.isEmpty(id)) {
                Toast.makeText(this, "Enter Reservation ID", Toast.LENGTH_SHORT).show();
                return;
            }

            reservationRef.child(id).child("status").setValue(status)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Status Updated", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}