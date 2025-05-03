package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.firebase.database.*;

import java.util.*;

public class BrowseMenuActivity extends AppCompatActivity {

    private RecyclerView menuRecyclerView;
    private MenuAdapter menuAdapter;
    private ArrayList<MenuItem> menuList;
    private DatabaseReference menuItemsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_menu);

        menuRecyclerView = findViewById(R.id.menuRecyclerView);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(menuList);
        menuRecyclerView.setAdapter(menuAdapter);

        menuItemsRef = FirebaseDatabase.getInstance().getReference("menu_items");

        fetchMenuItems();
    }

    private void fetchMenuItems() {
        menuItemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                menuList.clear();
                for (DataSnapshot menuSnapshot : snapshot.getChildren()) {
                    MenuItem menuItem = menuSnapshot.getValue(MenuItem.class);
                    if (menuItem != null && menuItem.isAvailable()) {
                        menuList.add(menuItem);
                    }
                }
                menuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BrowseMenuActivity.this, "Failed to load menu.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
