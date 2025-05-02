package com.example.tastehaven_application;

public class ManagerInventoryActivity extends AppCompatActivity {

    private ListView inventoryListView;
    private ArrayAdapter<String> inventoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_overview);

        inventoryListView = findViewById(R.id.inventoryListView);
        ArrayList<String> inventoryList = new ArrayList<>(Arrays.asList("Item 1 - 10 units", "Item 2 - 20 units", "Item 3 - 5 units"));

        inventoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, inventoryList);
        inventoryListView.setAdapter(inventoryAdapter);
    }
}