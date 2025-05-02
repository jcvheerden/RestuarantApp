package com.example.tastehaven_application;



import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ManagerRoleActivity extends AppCompatActivity {

    private EditText userIdInput, roleInput;
    private Button updateRoleButton, removeUserButton;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mUsersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_role);

        mDatabase = FirebaseDatabase.getInstance();
        mUsersRef = mDatabase.getReference("users");

        userIdInput = findViewById(R.id.userIdInput);
        roleInput = findViewById(R.id.roleInput);
        updateRoleButton = findViewById(R.id.updateRoleButton);
        removeUserButton = findViewById(R.id.removeUserButton);

        updateRoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = userIdInput.getText().toString();
                String newRole = roleInput.getText().toString();

                if (userId.isEmpty() || newRole.isEmpty()) {
                    Toast.makeText(ManagerRoleActivity.this, "Please enter user ID and role", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update role in Firebase
                HashMap<String, Object> updates = new HashMap<>();
                updates.put("role", newRole);

                mUsersRef.child(userId).updateChildren(updates)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ManagerRoleActivity.this, "Role updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ManagerRoleActivity.this, "Failed to update role", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        removeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = userIdInput.getText().toString();

                if (userId.isEmpty()) {
                    Toast.makeText(ManagerRoleActivity.this, "Please enter user ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Remove user from Firebase
                mUsersRef.child(userId).removeValue()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ManagerRoleActivity.this, "User removed successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ManagerRoleActivity.this, "Failed to remove user", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}

