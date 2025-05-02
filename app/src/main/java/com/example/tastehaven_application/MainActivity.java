package com.example.tastehaven_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText roleEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roleEditText = findViewById(R.id.roleEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String role = roleEditText.getText().toString().trim().toLowerCase();

            Intent intent;
            switch (role) {
                case "customer":
                    intent = new Intent(MainActivity.this, ReservationActivity.class);
                    break;
                case "waiter":
                    intent = new Intent(MainActivity.this, OrderActivity.class);
                    break;
                case "manager":
                    intent = new Intent(MainActivity.this, DashboardActivity.class);
                    break;
                default:
                    Toast.makeText(this, "Invalid role", Toast.LENGTH_SHORT).show();
                    return;
            }

            intent.putExtra("userRole", role);
            startActivity(intent);
        });
    }
}
