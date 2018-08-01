package com.sbtech.myquizapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClosingActivity extends AppCompatActivity {

    private FirebaseDatabase dbase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = dbase.getReference("highScore");

    {
        // Attach a listener to read the data at our posts reference
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Player p = dataSnapshot.getValue(Player.class);
                //System.out.println(p);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("MMMMMMMMMMMMMMMMMMM","The read failed: " + databaseError.getCode());
            }
        });

    }
    private List<Player> mHighScoreList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HighScoresAdapter mAdapter;

    private TextView mEndgameText;
    private TextView mScoreText;
    private TextView mHighScore;

 //   SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

    public void killOnClick(View v){
        //int versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        //if(versionCode>=16 && versionCode<21){
        finishAffinity();
        //} else if(versionCode>=21){
        //    finishAndRemoveTask();
        //}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closing);
        //dbase = FirebaseDatabase.getInstance();
        //myRef = dbase.getReference("highScore");
        mEndgameText = (TextView)findViewById(R.id.endgame_text_view);
        mScoreText = (TextView)findViewById(R.id.endscore_text_view);
        mHighScore = (TextView)findViewById(R.id.highscore_text_view);


        int score = getIntent().getIntExtra("SCORE",-1);

        int highScore = -1;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new HighScoresAdapter(mHighScoreList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

/*
        if(sharedpreferences.contains("HighScoreKey")) {
            highScore = Integer.parseInt(sharedpreferences.getString("HighScoreKey", "-1"));
            mHighScore.setText("Current High Score: "+highScore);
        }
*/
        String scoreMessage = "";
        if(score>highScore){
 /*           SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("HighScoreKey", ""+score);
            editor.commit();
*/
            Toast.makeText(this,R.string.newHighScoreMsg,Toast.LENGTH_LONG).show();

            myRef.child(myRef.push().getKey()).setValue(new Integer(score));
        }

        scoreMessage = ""+score;


        mScoreText.setText(scoreMessage);




    }

    public void resetOnClick(View view) {


    }
}
