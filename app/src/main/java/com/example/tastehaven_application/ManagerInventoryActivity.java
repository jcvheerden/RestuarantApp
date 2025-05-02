package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class ManagerInventoryActivity extends AppCompatActivity {

    private ListView inventoryListView;
    private ArrayAdapter<String> inventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_inventory);

        inventoryListView = findViewById(R.id.inventoryListView);
        ArrayList<String> inventoryList = new ArrayList<>(Arrays.asList("Item 1 - 10 units", "Item 2 - 20 units", "Item 3 - 5 units"));

        inventoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, inventoryList);
        inventoryListView.setAdapter(inventoryAdapter);
    }
}