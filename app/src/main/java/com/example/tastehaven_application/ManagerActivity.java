package com.example.tastehaven_application;

public class ManagerActivity extends AppCompatActivity {

    private ListView userListView;
    private FirebaseDatabase database;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        userListView = findViewById(R.id.userListView);
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> userList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String userName = snapshot.child("name").getValue(String.class);
                    String userRole = snapshot.child("role").getValue(String.class);
                    userList.add(userName + " - " + userRole);
                }

                ArrayAdapter<String> userAdapter = new ArrayAdapter<>(ManagerActivity.this, android.R.layout.simple_list_item_1, userList);
                userListView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ManagerActivity.this, "Failed to load users.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to update the role of a user
    public void updateUserRole(String userId, String newRole) {
        usersRef.child(userId).child("role").setValue(newRole);
    }
}

