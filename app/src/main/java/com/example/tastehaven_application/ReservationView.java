package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;

import java.util.ArrayList;

public class ReservationView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservationAdapter adapter;
    private ArrayList<Reservation> reservationList;
    private DatabaseReference reservationRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservations);

        recyclerView = findViewById(R.id.recyclerViewReservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reservationList = new ArrayList<>();
        adapter = new ReservationAdapter(reservationList);
        recyclerView.setAdapter(adapter);

        reservationRef = FirebaseDatabase.getInstance().getReference("reservations");

        reservationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reservationList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Reservation reservation = data.getValue(Reservation.class);
                    reservationList.add(reservation);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReservationView.this, "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}