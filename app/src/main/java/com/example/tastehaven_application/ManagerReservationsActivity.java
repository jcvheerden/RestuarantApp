package com.example.tastehaven_application;

import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerReservationsActivity extends AppCompatActivity {

    private ListView reservationsListView;
    private ArrayAdapter<String> reservationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_overview);

        reservationsListView = findViewById(R.id.reservationsListView);
        ArrayList<String> reservationList = new ArrayList<>(Arrays.asList("Reservation 1 - 2 Guests", "Reservation 2 - 4 Guests", "Reservation 3 - 3 Guests"));

        reservationsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reservationList);
        reservationsListView.setAdapter(reservationsAdapter);
    }
}

