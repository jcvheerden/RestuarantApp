package com.example.tastehaven_application;



import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

import java.util.HashMap;

public class RoleManagementActivity extends AppCompatActivity {

    private EditText etUserId;
    private Spinner roleSpinner;
    private Button btnAssignRole;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_managemen);

        etUserId = findViewById(R.id.etUserId);
        roleSpinner = findViewById(R.id.spinnerRole);
        btnAssignRole = findViewById(R.id.btnAssignRole);

        usersRef = FirebaseDatabase.getInstance().getReference("users");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        btnAssignRole.setOnClickListener(v -> {
            String userId = etUserId.getText().toString().trim();
            String role = roleSpinner.getSelectedItem().toString().toLowerCase();

            if (!userId.isEmpty()) {
                HashMap<String, Object> updates = new HashMap<>();
                updates.put("role", role);
                usersRef.child(userId).updateChildren(updates);
                Toast.makeText(this, "Role updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

