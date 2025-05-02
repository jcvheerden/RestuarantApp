package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManagerMenuActivity extends AppCompatActivity {

    private EditText nameEditText, descriptionEditText, priceEditText;
    private Button addItemButton, removeItemButton;
    private RecyclerView menuRecyclerView;
    private FirebaseDatabase database;
    private DatabaseReference menuRef;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_menu);

        // Initialize UI elements
        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        priceEditText = findViewById(R.id.priceEditText);
        addItemButton = findViewById(R.id.addItemButton);
        removeItemButton = findViewById(R.id.removeItemButton);
        menuRecyclerView = findViewById(R.id.menuRecyclerView);

        // Initialize Firebase database references
        database = FirebaseDatabase.getInstance();
        menuRef = database.getReference("menu_items");

        // Initialize the list and adapter for the menu
        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(this, menuList);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRecyclerView.setAdapter(menuAdapter);

        // Load the menu items
        loadMenuItems();

        // Handle add item button click
        addItemButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();
            String price = priceEditText.getText().toString().trim();

            if (!name.isEmpty() && !description.isEmpty() && !price.isEmpty()) {
                addMenuItem(name, description, price);
            }
        });

        // Handle remove item button click
        removeItemButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            removeMenuItem(name);
        });
    }

    private void loadMenuItems() {
        // Load menu items from Firebase database
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menuList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MenuItem menuItem = snapshot.getValue(MenuItem.class);
                    menuList.add(menuItem);
                }
                menuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ManagerMenuActivity.this, "Failed to load menu items", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addMenuItem(String name, String description, String price) {
        String menuId = menuRef.push().getKey();
        if (menuId != null) {
            // Create a new menu item and add it to Firebase
            MenuItem menuItem = new MenuItem(menuId, name, Double.parseDouble(price), description, "");  // Pass empty imageUrl
            menuRef.child(menuId).setValue(menuItem);
            Toast.makeText(ManagerMenuActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
        }
    }


    private void removeMenuItem(String name) {
        // Search for the item in the database and remove it
        menuRef.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        snapshot.getRef().removeValue();
                        Toast.makeText(ManagerMenuActivity.this, "Item removed successfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ManagerMenuActivity.this, "Item not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ManagerMenuActivity.this, "Failed to remove item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
