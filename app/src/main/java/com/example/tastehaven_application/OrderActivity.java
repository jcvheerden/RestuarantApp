package com.example.tastehaven_application;

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
            ArrayList<String> selectedItems = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                if (menuListView.isItemChecked(i)) {
                    selectedItems.add(items.get(i));
                }
            }

            Order order = new Order(table, "waiter123", "received", System.currentTimeMillis(), selectedItems);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("orders");
            String id = ref.push().getKey();
            if (id != null) ref.child(id).setValue(order);

            Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();
        });
    }
}

