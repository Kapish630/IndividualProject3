package com.example.kapis.individualproject3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    TextView results;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    SharedPreferences sharedPref2;
    SharedPreferences.Editor editor2;

    int score;
    int easy;
    int hard;
    int accountNum;
    int scoreCounter;
    String fName;
    String lName;
    Boolean isParent;

    Button rtrnMain;
    Button report;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        results = (TextView)findViewById(R.id.scoreview);
        rtrnMain = (Button)findViewById(R.id.rtrnMain);
        report = (Button)findViewById(R.id.parentReport);

        Intent i = getIntent();
        easy = i.getIntExtra("easyScore", -1);

        Intent j = getIntent();
        hard = j.getIntExtra("hardScore", -1);

        if (easy == -1) {// hard score
            score = hard;

        }
        else if (hard == -1) { // easy score
            score = easy;
        }


        sharedPref = getSharedPreferences(getString(R.string.pref_file_key),
                Context.MODE_PRIVATE);

        editor = sharedPref.edit();
        accountNum = sharedPref.getInt("account", 0);

        fName = sharedPref.getString("FirstName"+accountNum, "");
        lName = sharedPref.getString("LastName"+accountNum, "");
        isParent = sharedPref.getBoolean("Parent"+accountNum, false);
        editor.apply();

        sharedPref2 = getSharedPreferences(getString(R.string.pref_file_key2),
                Context.MODE_PRIVATE);
        editor2 = sharedPref2.edit();
        scoreCounter = sharedPref2.getInt("scoreCounter", 0);
        score = sharedPref2.getInt("Score_Hard"+accountNum+"_"+(scoreCounter-1), -1);
        editor2.apply();

        results.setText(fName + " " + lName + " your final score is: " + score);

        if(isParent == true){
            report.setVisibility(View.VISIBLE);
        }

        rtrnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rtrnMainPage();
            }
        });



    }

    public void rtrnMainPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}
