package com.sbtech.myquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HighScoreNameActivity extends AppCompatActivity {

    private Button mOkayButton;
    private TextView mMessageText;
    private EditText mNameEditText;
    private String mName;

    public void clickShow(View v){

        String hint = getIntent().getStringExtra("HINT");
        mMessageText.setText(hint);
        mName = mNameEditText.getText().toString();
        Intent BackIntent = new Intent();
        BackIntent.putExtra("NAME",mName);
        setResult(RESULT_OK,BackIntent);
        finish();
    }

    public void onBackPressed(){

        Intent BackIntent = new Intent();
        BackIntent.putExtra("NAME",mName);
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

        mOkayButton = (Button)findViewById(R.id.okay_button);
        mMessageText= (TextView)findViewById(R.id.answer_text_view);
        mNameEditText = (EditText)findViewById(R.id.name_edit_box);

        mName = "";
    }
}
