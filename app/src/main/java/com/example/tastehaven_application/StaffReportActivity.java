package com.example.tastehaven_application;



import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class StaffReportActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> staffList;
    private ArrayAdapter<String> adapter;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_report);

        listView = findViewById(R.id.staffListView);
        staffList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, staffList);
        listView.setAdapter(adapter);

        usersRef = FirebaseDatabase.getInstance().getReference("users");

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                staffList.clear();
                for (DataSnapshot user : snapshot.getChildren()) {
                    String role = user.child("role").getValue(String.class);
                    if (!role.equals("customer")) {
                        String name = user.child("name").getValue(String.class);
                        staffList.add(name + " (" + role + ")");
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }
}

