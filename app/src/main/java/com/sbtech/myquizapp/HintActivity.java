package com.sbtech.myquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private Button mHintButton;
    private TextView mAnswerText;
    private boolean mHintViewed;

    public void clickShow(View v){

        String hint = getIntent().getStringExtra("HINT");
        mAnswerText.setText(hint);
        mHintViewed = true;
    }

    public void onBackPressed(){

        Intent BackIntent = new Intent();
        BackIntent.putExtra("SEEN",mHintViewed);
        setResult(RESULT_OK,BackIntent);
        finish();

    }
    public static boolean wasHintShown(Intent result) {
        return result.getBooleanExtra("SEEN",true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mHintButton = (Button)findViewById(R.id.show_answer_button);
        mAnswerText= (TextView)findViewById(R.id.answer_text_view);

        mHintViewed = false;
    }
}
