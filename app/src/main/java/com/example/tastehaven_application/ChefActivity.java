package com.example.tastehaven_application;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChefActivity extends AppCompatActivity {

    private ListView ordersListView;
    private ArrayAdapter<String> ordersAdapter;
    private FirebaseDatabase database;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);

        ordersListView = findViewById(R.id.ordersListView);
        database = FirebaseDatabase.getInstance();
        ordersRef = database.getReference("orders");

        ordersRef.orderByChild("status").equalTo("received").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> ordersList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String order = snapshot.child("table_number").getValue(String.class);
                    ordersList.add("Order from Table: " + order);
                }
                ordersAdapter = new ArrayAdapter<>(ChefActivity.this, android.R.layout.simple_list_item_1, ordersList);
                ordersListView.setAdapter(ordersAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChefActivity.this, "Failed to load orders.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to update the order status (Preparing, Ready, Served)
    public void updateOrderStatus(String orderId, String status) {
        ordersRef.child(orderId).child("status").setValue(status);
    }
}


