package com.example.tastehaven_application;

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

public class FeedbackActivity extends AppCompatActivity {

    private EditText feedbackEditText;
    private Button submitFeedbackButton;
    private FirebaseDatabase database;
    private DatabaseReference feedbackRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackEditText = findViewById(R.id.feedbackEditText);
        submitFeedbackButton = findViewById(R.id.submitFeedbackButton);

        database = FirebaseDatabase.getInstance();
        feedbackRef = database.getReference("feedback");

        submitFeedbackButton.setOnClickListener(v -> {
            String feedback = feedbackEditText.getText().toString().trim();

            if (!feedback.isEmpty()) {
                String feedbackId = feedbackRef.push().getKey();
                if (feedbackId != null) {
                    feedbackRef.child(feedbackId).setValue(feedback);
                    Toast.makeText(FeedbackActivity.this, "Feedback submitted!", Toast.LENGTH_SHORT).show();
                    finish(); // Close the feedback activity
                }
            } else {
                Toast.makeText(FeedbackActivity.this, "Please enter your feedback.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
