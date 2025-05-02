
package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReserveTableActivity extends AppCompatActivity {

    private EditText numberOfGuestsEditText;
    private DatePicker datePicker;
    private Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_table);

        numberOfGuestsEditText = findViewById(R.id.numberOfGuests);
        datePicker = findViewById(R.id.datePicker);
        reserveButton = findViewById(R.id.reserveButton);

        reserveButton.setOnClickListener(v -> {
            String numberOfGuests = numberOfGuestsEditText.getText().toString().trim();
            if (!numberOfGuests.isEmpty()) {
                // Reservation logic (e.g., saving to Firebase)
                Toast.makeText(this, "Reservation made for " + numberOfGuests + " guests!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter the number of guests.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
