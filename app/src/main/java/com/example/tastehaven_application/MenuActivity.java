package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView menuRecyclerView;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuRecyclerView = findViewById(R.id.menuRecyclerView); // Add RecyclerView in your XML layout
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Creating dummy data
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("1", "Spaghetti", 12.99, "Delicious spaghetti with marinara sauce.", "image_url_1"));
        menuItems.add(new MenuItem("2", "Cheeseburger", 9.99, "Juicy cheeseburger with all the fixings.", "image_url_2"));
        menuItems.add(new MenuItem("3", "Caesar Salad", 8.49, "Fresh romaine lettuce with Caesar dressing.", "image_url_3"));
        menuItems.add(new MenuItem("4", "Pizza Margherita", 14.99, "Classic pizza with mozzarella and basil.", "image_url_4"));
        menuItems.add(new MenuItem("5", "Tacos", 10.49, "Spicy tacos with beef, lettuce, and salsa.", "image_url_5"));

        // Initialize the adapter and set it to the RecyclerView
        menuAdapter = new MenuAdapter(this, menuItems);
        menuRecyclerView.setAdapter(menuAdapter);
    }
}
