package com.example.tastehaven_application;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderActivity extends AppCompatActivity {

    private EditText tableNumberEditText;
    private ListView menuListView;
    private Button placeOrderButton;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tableNumberEditText = findViewById(R.id.tableNumberEditText);
        menuListView = findViewById(R.id.menuListView);
        placeOrderButton = findViewById(R.id.placeOrderButton);

        items = new ArrayList<>(Arrays.asList("Pizza", "Burger", "Pasta", "Salad"));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);
        menuListView.setAdapter(adapter);
        menuListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        placeOrderButton.setOnClickListener(v -> {
            String table = tableNumberEditText.getText().toString();
            ArrayList<OrderItem> selectedItems = new ArrayList<>();

            for (int i = 0; i < items.size(); i++) {
                if (menuListView.isItemChecked(i)) {
                    selectedItems.add(new OrderItem(items.get(i), 10.0, 1)); // Example price and quantity
                }
            }

            Order order = new Order(table, "waiter123", "received", System.currentTimeMillis(), selectedItems);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("orders");
            String id = ref.push().getKey();
            if (id != null) {
                ref.child(id).setValue(order);
            }

            Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();
        });
    }
}
