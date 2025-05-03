package com.example.tastehaven_application;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

public class CustomerLoginActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button loginButton;
    DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        usersRef = FirebaseDatabase.getInstance().getReference("users");

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            usersRef.orderByChild("email").equalTo(email)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean success = false;
                            for (DataSnapshot userSnap : snapshot.getChildren()) {
                                String passHash = userSnap.child("password_hash").getValue(String.class);
                                String role = userSnap.child("role").getValue(String.class);

                                if (passHash.equals(password) && role.equals("customer")) {
                                    success = true;
                                    // Navigate to the customer dashboard (e.g., MenuActivity)
                                    break;
                                }
                            }
                            if (!success)
                                Toast.makeText(CustomerLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(CustomerLoginActivity.this, "DB Error", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
