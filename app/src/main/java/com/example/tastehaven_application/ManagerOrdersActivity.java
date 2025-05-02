package com.example.tastehaven_application;

import androidx.appcompat.app.AppCompatActivity;
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

public class ManagerOrdersActivity extends AppCompatActivity {

    private ListView ordersListView;
    private ArrayAdapter<String> ordersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_orders);

        ordersListView = findViewById(R.id.ordersListView);
        ArrayList<String> orderList = new ArrayList<>(Arrays.asList("Order 1 - Pizza", "Order 2 - Burger", "Order 3 - Salad"));

        ordersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderList);
        ordersListView.setAdapter(ordersAdapter);
    }
}


