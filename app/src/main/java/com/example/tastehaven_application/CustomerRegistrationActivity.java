package com.example.tastehaven_application;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

public class CustomerRegistrationActivity extends AppCompatActivity {

    EditText nameEditText, emailEditText, passwordEditText;
    Button registerButton;
    DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        usersRef = FirebaseDatabase.getInstance().getReference("users");

        registerButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            String userId = usersRef.push().getKey();
            User newUser = new User(userId, name, email, password, "customer", System.currentTimeMillis());

            usersRef.child(userId).setValue(newUser).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(CustomerRegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CustomerRegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }


}
