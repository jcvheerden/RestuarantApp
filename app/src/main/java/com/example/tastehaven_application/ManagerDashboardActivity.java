package com.example.tastehaven_application;

public class ManagerDashboardActivity extends AppCompatActivity {

    private Button viewOrdersButton, viewReservationsButton, viewInventoryButton, manageMenuButton, viewSalesReportButton;
    private FirebaseDatabase database;
    private DatabaseReference ordersRef, reservationsRef, inventoryRef, menuRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        viewOrdersButton = findViewById(R.id.viewOrdersButton);
        viewReservationsButton = findViewById(R.id.viewReservationsButton);
        viewInventoryButton = findViewById(R.id.viewInventoryButton);
        manageMenuButton = findViewById(R.id.manageMenuButton);
        viewSalesReportButton = findViewById(R.id.viewSalesReportButton);

        database = FirebaseDatabase.getInstance();
        ordersRef = database.getReference("orders");
        reservationsRef = database.getReference("reservations");
        inventoryRef = database.getReference("inventory");
        menuRef = database.getReference("menu_items");

        viewOrdersButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, OrdersActivity.class));
        });

        viewReservationsButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, ReservationsActivity.class));
        });

        viewInventoryButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, InventoryActivity.class));
        });

        manageMenuButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, ManageMenuActivity.class));
        });

        // Sales Report Button Click
        viewSalesReportButton.setOnClickListener(v -> {
            startActivity(new Intent(ManagerDashboardActivity.this, SalesReportActivity.class));
        });
    }
}
