package com.example.tastehaven_application;

import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerReservationsActivity extends AppCompatActivity {

    private ListView reservationsListView;
    private ArrayAdapter<String> reservationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_reservations);

        reservationsListView = findViewById(R.id.reservationsListView);
        ArrayList<String> reservationList = new ArrayList<>(Arrays.asList("Reservation 1 - 2 Guests", "Reservation 2 - 4 Guests", "Reservation 3 - 3 Guests"));

        reservationsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reservationList);
        reservationsListView.setAdapter(reservationsAdapter);
    }
}

