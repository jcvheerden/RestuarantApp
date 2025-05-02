package com.example.tastehaven_application;

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
        setContentView(R.layout.activity_manage_menu);

        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        priceEditText = findViewById(R.id.priceEditText);
        addItemButton = findViewById(R.id.addItemButton);
        removeItemButton = findViewById(R.id.removeItemButton);
        menuRecyclerView = findViewById(R.id.menuRecyclerView);

        database = FirebaseDatabase.getInstance();
        menuRef = database.getReference("menu_items");

        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(menuList);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRecyclerView.setAdapter(menuAdapter);

        loadMenuItems();

        addItemButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String description = descriptionEditText.getText().toString().trim();
            String price = priceEditText.getText().toString().trim();

            if (!name.isEmpty() && !description.isEmpty() && !price.isEmpty()) {
                addMenuItem(name, description, price);
            }
        });

        removeItemButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            removeMenuItem(name);
        });
    }

    private void loadMenuItems() {
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
                Toast.makeText(ManageMenuActivity.this, "Failed to load menu items", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addMenuItem(String name, String description, String price) {
        String menuId = menuRef.push().getKey();
        if (menuId != null) {
            MenuItem menuItem = new MenuItem(name, description, Double.parseDouble(price));
            menuRef.child(menuId).setValue(menuItem);
            Toast.makeText(ManageMenuActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void removeMenuItem(String name) {
        menuRef.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        snapshot.getRef().removeValue();
                        Toast.makeText(ManageMenuActivity.this, "Item removed successfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ManageMenuActivity.this, "Item not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ManageMenuActivity.this, "Failed to remove item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

