package com.example.tastehaven_application;

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
