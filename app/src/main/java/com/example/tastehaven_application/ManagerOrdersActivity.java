package com.example.tastehaven_application;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerOrdersActivity extends AppCompatActivity {

    private ListView ordersListView;
    private ArrayAdapter<String> ordersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_overview);

        ordersListView = findViewById(R.id.ordersListView);
        ArrayList<String> orderList = new ArrayList<>(Arrays.asList("Order 1 - Pizza", "Order 2 - Burger", "Order 3 - Salad"));

        ordersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderList);
        ordersListView.setAdapter(ordersAdapter);
    }
}


