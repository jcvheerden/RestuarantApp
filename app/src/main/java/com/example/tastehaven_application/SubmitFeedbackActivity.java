package com.example.tastehaven_application;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;

public class SubmitFeedbackActivity extends AppCompatActivity {

    private EditText feedbackEditText;
    private Button submitFeedbackButton;
    private DatabaseReference feedbackRef;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_feedback);

        feedbackEditText = findViewById(R.id.feedbackEditText);
        submitFeedbackButton = findViewById(R.id.submitFeedbackButton);

        // For demo purposes, we assume the userId is fetched from the current logged-in user
        userId = "dummy_user_id";  // You can replace this with actual user authentication

        feedbackRef = FirebaseDatabase.getInstance().getReference("feedback");

        submitFeedbackButton.setOnClickListener(v -> submitFeedback());
    }

    private void submitFeedback() {
        String feedback = feedbackEditText.getText().toString().trim();

        if (!feedback.isEmpty()) {
            String feedbackId = feedbackRef.push().getKey();
            Feedback newFeedback = new Feedback(feedbackId, userId, feedback);

            feedbackRef.child(feedbackId).setValue(newFeedback).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(SubmitFeedbackActivity.this, "Feedback submitted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SubmitFeedbackActivity.this, "Feedback submission failed", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
        }
    }

    public static class Feedback {
        public String feedback_id;
        public String user_id;
        public String feedback;

        public Feedback() {}

        public Feedback(String feedback_id, String user_id, String feedback) {
            this.feedback_id = feedback_id;
            this.user_id = user_id;
            this.feedback = feedback;
        }
    }
}
