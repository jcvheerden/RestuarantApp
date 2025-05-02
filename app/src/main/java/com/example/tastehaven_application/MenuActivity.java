package com.example.tastehaven_application;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button btnAddMenuItem, btnEditMenuItem, btnRemoveMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnAddMenuItem = findViewById(R.id.btnAddMenuItem);
        btnEditMenuItem = findViewById(R.id.btnEditMenuItem);
        btnRemoveMenuItem = findViewById(R.id.btnRemoveMenuItem);

        btnAddMenuItem.setOnClickListener(v -> openAddMenuItemActivity());
        btnEditMenuItem.setOnClickListener(v -> openEditMenuItemActivity());
        btnRemoveMenuItem.setOnClickListener(v -> openRemoveMenuItemActivity());
    }

    private void openAddMenuItemActivity() {
        // Transition to Add Menu Item Activity
    }

    private void openEditMenuItemActivity() {
        // Transition to Edit Menu Item Activity
    }

    private void openRemoveMenuItemActivity() {
        // Transition to Remove Menu Item Activity
    }
}
