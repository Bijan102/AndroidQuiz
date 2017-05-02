package com.bijan.android.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //The two buttons the user can click
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;     // A next button to go on to the next question
    private ImageButton mPreviousButton;

    // Create a TAG to use for debugging program
    private static final String TAG = "QuizActivity";

    // A TextView for displaying a question
    private TextView mQuestionTextView;

    // Declare and initialize an array of Question objects, 'mQuestionBank', to serve as a question bank
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    // Declare and initialize a current index for the Question array to be 0, 'mCurrentIndex'
    private int mCurrentIndex = 0;

    /**
     * updateQuestion:  Updates myQuestionTextView's text to the question at the current index in mQuestionBank.
     *
     */
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    /**
     * checkAnswer: Identifies whether the user pressed True or False, then checks the user's answer
     *              against the answer in the current Question object and displays a Toast w/ the appropriate message.
     *
     * @param userPressedTrue:  Identifies whether the user pressed True or False
     */
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        messageResId = (userPressedTrue == answerIsTrue) ? R.string.correct_toast : R.string.incorrect_toast;

        Toast toast = Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    /**
     * Dispatch onStart() to all fragments.  Ensure any created loaders are
     * now started.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate(Bundle) called");



        setContentView(R.layout.activity_quiz);

        // Obtaining reference for the TextView
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //Setting up listeners for onClick events triggered by the user after obtaining references
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreviousButton = (ImageButton) findViewById(R.id.prev_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != 0)
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                else mCurrentIndex = mQuestionBank.length - 1;
                updateQuestion();
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });



        updateQuestion();       // Call method to display the first question in the mQuestionBank.
    }
}
