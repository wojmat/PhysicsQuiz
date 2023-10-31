// Package declaration
package pl.edu.uwr.pum.physicsquiz;

// Importing required classes and packages

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Declaring SummaryActivity class that extends AppCompatActivity
public class SummaryActivity extends AppCompatActivity {

    // Declaring variables for UI elements
    private TextView mScoreTextView; // TextView to display the user's score
    private Button mRestartButton; // Button to restart the quiz

    // Overriding the onCreate method from AppCompatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the content view to activity_summary.xml
        setContentView(R.layout.activity_summary);

        // Initializing UI elements
        mScoreTextView = findViewById(R.id.score_textview);
        mRestartButton = findViewById(R.id.restart_button);

        // Getting score and total questions from intent extras
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        // Updating score text view with user's score
        mScoreTextView.setText("Gratulacje! Twój wynik to: " + score + "/" + (totalQuestions * 10));

        // Setting click listener for restart button
        mRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating intent to go back to MainActivity
                Intent intent = new Intent(SummaryActivity.this, pl.edu.uwr.pum.physicsquiz.MainActivity.class);
                // Starting MainActivity
                startActivity(intent);
                // Finishing SummaryActivity
                finish();
            }
        });
    }
}
