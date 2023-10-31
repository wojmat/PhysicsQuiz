package pl.edu.uwr.pum.physicsquiz;

// Importing required classes and packages
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import pl.edu.uwr.pum.physicsquiz.R;

// Declaring MainActivity class that extends AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // Declaring variables for UI elements
    private TextView mQuestionNumberTextView; // TextView to display the question number
    private ProgressBar mQuestionProgressBar; // ProgressBar to display question progress
    private TextView mQuestionTextView; // TextView to display the question text
    private RadioGroup mAnswersRadioGroup; // RadioGroup to hold answer options
    private Button mNextQuestionButton; // Button to go to next question

    // Declaring and initializing question bank
    private ArrayList<Question> mQuestionBank = new ArrayList<>(Arrays.asList(
            new Question("Ciało jest w ruchu, jeżeli w miarę upływu czasu zmienia się położenie względem:",
                    new String[]{"a-przyjętego układu odniesienia", "b-siebie", "c-Słońca", "d-położenie nie zmienia się"},
                    0,
                    false
            ),
            new Question("Tor to:",
                    new String[]{"a-linia, którą zakreśla poruszające się ciało", "b-to przebyta droga", "c-droga, którą należy przebyć", "d-wyznaczona droga"},
                    0,
                    false
            ),
            new Question("W ruchu jednostajnie przyspieszonym przyspieszenie jest:",
                    new String[]{"a) Stałe", "b) Zmienne", "c) Zależy od prędkości", "d) Opóźnione"},
                    0,
                    false
            ),
            new Question("Energia kinetyczna to:",
                    new String[]{"a) Energia, którą posiadają wszystkie ciała będące w ruchu", "b) Ciała poniesione ponad powierzchnię Ziemi", "c) Suma energii potencjalnej i mechanicznej", "d) Energia mechaniczna"},
                    0,
                    false
            ),
            new Question("Jednostką siły jest:",
                    new String[]{"a) 1 niuton (1N)", "b) 1 kilogram (1 kg)", "c) 1 tona", "d) 1 gram"},
                    0,
                    false
            ),
            new Question("Ciała, na które działają siły równoważące się, pozostają:",
                    new String[]{"a) W spoczynku", "b) Poruszają się", "c) Unoszą się", "d) Opadają"},
                    0,
                    false
            ),
            new Question("Siła wypadkowa sił równoważących jest:",
                    new String[]{"a) Równa zeru", "b) Jest większa od zera", "c) Jest dużo większa od zera", "d) Jest stała"},
                    0,
                    false
            ),
            new Question("Jednostką przyspieszenia jest:",
                    new String[]{"a) Metr na sekundę kwadrat", "b) Metr na sekundę", "c) Kilometr na godzinę", "d) Kilometr na godzinę do kwadratu"},
                    0,
                    false
            ),
            new Question("Mechanika to dział:",
                    new String[]{"a) Fizyki", "b) Chemii", "c) Geografii", "d) Biologii"},
                    0,
                    false
            ),
            new Question("Izaak Newton odkrył zasady dynamiki, ile:",
                    new String[]{"a) 3", "b) 1", "c) 2", "d) 4"},
                    0,
                    false
            )
    ));

    // Declaring and initializing index for the current question and user's score
    private int mCurrentIndex = 0;
    private int mUserScore = 0;

    // Overriding the onCreate method from AppCompatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the content view to activity_main.xml
        setContentView(R.layout.activity_main);

        // Initializing UI elements
        mQuestionNumberTextView = findViewById(R.id.question_number_textview);
        mQuestionProgressBar = findViewById(R.id.question_progressbar);
        mQuestionTextView = findViewById(R.id.question_textview);
        mAnswersRadioGroup = findViewById(R.id.answers_radiogroup);
        mNextQuestionButton = findViewById(R.id.next_question_button);

        // Calling method to update UI with first question
        updateQuestion();

        // Setting click listener for next question button
        mNextQuestionButton.setOnClickListener(v -> {
            // Checking if any answer option is selected
            if (mAnswersRadioGroup.getCheckedRadioButtonId() != -1) {
                // Checking if selected answer is correct and updating user's score
                if (((RadioButton) findViewById(mAnswersRadioGroup.getCheckedRadioButtonId())).getText().equals(
                        mQuestionBank.get(mCurrentIndex).getAnswers()[mQuestionBank.get(mCurrentIndex).getCorrectAnswerIndex()])) {
                    mUserScore += 10;
                }
                // Incrementing index for next question
                mCurrentIndex++;
                // Checking if this was the last question
                if (mCurrentIndex == mQuestionBank.size()) {
                    // If last question, going to SummaryActivity
                    Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                    intent.putExtra("SCORE", mUserScore); // Passing user's score to SummaryActivity
                    intent.putExtra("TOTAL_QUESTIONS", mQuestionBank.size()); // Passing total number of questions to SummaryActivity
                    startActivity(intent); // Starting SummaryActivity
                    finish(); // Finishing MainActivity
                } else {
                    // If not last question, updating UI with next question
                    updateQuestion();
                }
            }
        });
    }

    // Method to update UI with current question
    private void updateQuestion() {
        // Checking if index is within range
        if (mCurrentIndex < mQuestionBank.size()) {
            // Getting current question from question bank
            Question currentQuestion = mQuestionBank.get(mCurrentIndex);
            // Updating question number text view
            mQuestionNumberTextView.setText("Pytanie " + (mCurrentIndex + 1) + "/" + mQuestionBank.size());
            // Updating question progress bar
            mQuestionProgressBar.setProgress((int) (((float) mCurrentIndex / mQuestionBank.size()) * 100));
            // Updating question text view
            mQuestionTextView.setText(currentQuestion.getTextResId());

            // Removing all radio buttons from radio group
            mAnswersRadioGroup.removeAllViews();
            // Getting answers for current question
            String[] answers = currentQuestion.getAnswers();
            // Adding radio buttons for each answer
            for (int i = 0; i < answers.length; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(answers[i]);
                mAnswersRadioGroup.addView(radioButton);
            }
        } else {
            // If index is out of range, going to SummaryActivity
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            intent.putExtra("SCORE", mUserScore); // Passing user's score to SummaryActivity
            intent.putExtra("TOTAL_QUESTIONS", mQuestionBank.size()); // Passing total number of questions to SummaryActivity
            startActivity(intent); // Starting SummaryActivity
            finish(); // Finishing MainActivity
        }
    }
}
