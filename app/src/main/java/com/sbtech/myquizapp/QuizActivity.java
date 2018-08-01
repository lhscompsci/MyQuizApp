package com.sbtech.myquizapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mHintButton;
    private TextView mQuestionTextView;
    private TextView mScoreTextView;
    private ImageView mImageView;
    private boolean mHintViewed;



    private int score = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question1,false,"The capital is in the northern part of the state."),
            new Question(R.string.question2,true,"Boy, that's an oddly shaped state."),
            new Question(R.string.question3,true,"Keep it weird!"),
            new Question(R.string.question4, false,"No ports in this capital."),
            new Question(R.string.question5, true,"This one is in the bag.")
    };

    private int mCurrentIndex = -1;

    public void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        if(userPressedTrue==answerIsTrue){
            if(mHintViewed)
                score += 5;
            else
                score+=10;
            messageResId = R.string.correct_toast;
        }
        else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        mCurrentIndex=(mCurrentIndex+1);// put this on the end to cycle through the questions endlessly: % mQuestionBank.length;

        Log.v("MMMMMMMMMMMMMMMMMMMMMMM",""+mCurrentIndex); // like a debug print statement

        if(mCurrentIndex == mQuestionBank.length) {
            Intent i = new Intent(QuizActivity.this, ClosingActivity.class);
            i.putExtra("SCORE",score);
            startActivity(i);
        }
        else if(mCurrentIndex < mQuestionBank.length) {
            mHintViewed = false;
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
           // mImageView.setImageResource();
        }
        mScoreTextView.setText("Score: "+score);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mNextButton = (Button)findViewById(R.id.next_button);
        mHintButton = (Button)findViewById(R.id.hint_button);

        mImageView = (ImageView)findViewById(R.id.question_image);

        mHintViewed = false;

        mScoreTextView = (TextView)findViewById(R.id.score_text_view);
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();



        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 checkAnswer(false);
                 updateQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if(mCurrentIndex<mQuestionBank.length)
                    updateQuestion();

            }
        });


    }

    public void hintOnClick(View view) {
        Intent i = new Intent(QuizActivity.this, HintActivity.class);
        i.putExtra("HINT",mQuestionBank[mCurrentIndex].getHint());
        startActivityForResult(i,REQUEST_CODE_CHEAT);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_CODE_CHEAT) {
            if(data == null){
                return;
            }
            mHintViewed = HintActivity.wasHintShown(data);
        }
    }
}
