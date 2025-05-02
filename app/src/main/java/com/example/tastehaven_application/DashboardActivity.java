package com.example.tastehaven_application;

public class DashboardActivity extends AppCompatActivity {

    private Button viewOrdersButton, viewReservationsButton, viewInventoryButton;
    private ListView dashboardListView;
    private ArrayAdapter<String> dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        viewOrdersButton = findViewById(R.id.viewOrdersButton);
        viewReservationsButton = findViewById(R.id.viewReservationsButton);
        viewInventoryButton = findViewById(R.id.viewInventoryButton);
        dashboardListView = findViewById(R.id.dashboardListView);

        viewOrdersButton.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, OrdersOverviewActivity.class)));
        viewReservationsButton.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, ReservationsOverviewActivity.class)));
        viewInventoryButton.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, InventoryOverviewActivity.class)));

        // Example of dashboard data display
        ArrayList<String> dashboardItems = new ArrayList<>(Arrays.asList("Orders Overview", "Reservations Overview", "Inventory Overview"));
        dashboardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dashboardItems);
        dashboardListView.setAdapter(dashboardAdapter);
    }
}

