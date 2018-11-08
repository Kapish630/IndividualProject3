package com.example.kapis.individualproject3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LVL3 extends AppCompatActivity {

    //variable declarations
    ImageView guy;
    Button go;
    Button upbtn;
    Button downbtn;
    Button leftbtn;
    Button rightbtn;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    TextView tv;
    Drawable clear;

    LVL3.MyStrtDragListener startDrag;
    LVL3.endDragListener endDrag;

    SharedPreferences sharedPref;
    SharedPreferences sharedPref2;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor2;

    int accountNum;
    int counter;
    int scoreCheck;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl3);

        // gets score from previous activity
        Intent i = getIntent();
        score = i.getIntExtra("ttlscore", 0);

        // Assigning values to all the variables
        startDrag = new LVL3.MyStrtDragListener();
        endDrag = new LVL3.endDragListener();
        go = (Button)findViewById(R.id.lv3go);
        guy = (ImageView)findViewById(R.id.lv3imageView);
        tv = (TextView)findViewById(R.id.lv3tv1);

        upbtn = (Button)findViewById(R.id.lv3upBtn);
        downbtn = (Button)findViewById(R.id.lv3downBtn);
        leftbtn = (Button)findViewById(R.id.lv3leftBtn);
        rightbtn = (Button)findViewById(R.id.lv3rightBtn);
        btn1  = (Button)findViewById(R.id.lv3Btn1);
        btn2  = (Button)findViewById(R.id.lv3Btn2);
        btn3 = (Button)findViewById(R.id.lv3Btn3);
        btn4  = (Button)findViewById(R.id.lv3Btn4);

        //setting the background so I can call it when the buttons are cleared
        clear = btn1.getBackground();

        // calling class to begin dragging the object
        upbtn.setOnLongClickListener(startDrag);
        downbtn.setOnLongClickListener(startDrag);
        leftbtn.setOnLongClickListener(startDrag);
        rightbtn.setOnLongClickListener(startDrag);

        // calling class to end dragging event
        btn1.setOnDragListener(endDrag);
        btn2.setOnDragListener(endDrag);
        btn3.setOnDragListener(endDrag);
        btn4.setOnDragListener(endDrag);

        sharedPref = getSharedPreferences(getString(R.string.pref_file_key),
                Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        accountNum = sharedPref.getInt("account", 1);

        sharedPref2 = getSharedPreferences(getString(R.string.pref_file_key2),
                Context.MODE_PRIVATE);
        editor2 = sharedPref2.edit();
        counter = sharedPref2.getInt("EasyScoreCounter", 1);

        scoreCheck = sharedPref2.getInt("Score_Easy_"+accountNum+"_"+(counter-1), -1);

        if (scoreCheck == -1){
            counter = 1;
        }


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking if the correct arrows were placed in the correct buttons by
                // comparing their background bitimage values
                if(btn1.getBackground() == rightbtn.getBackground()
                        && btn2.getBackground() == upbtn.getBackground()
                        && btn3.getBackground() == leftbtn.getBackground()) {
                    tv.setText("Good Job!\nYour Score So Far Is: " + score); // displaying the score

                    // Animating the guy to complete the track
                    ObjectAnimator a1 = ObjectAnimator.ofFloat(guy, "translationX", 500f);
                    a1.setDuration(1500);
                    ObjectAnimator a2 = ObjectAnimator.ofFloat(guy, "translationY", -500f);
                    a2.setDuration(1500);
                    ObjectAnimator a3 = ObjectAnimator.ofFloat(guy, "translationX", 0f);
                    a3.setDuration(1500);
                    // using AnimatorSet to have the animations occur in sequence
                    AnimatorSet set = new AnimatorSet();
                    set.playSequentially(a1, a2, a3);
                    set.start();

                    // moving on to the next page after a delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LVL3.this, ScoreScreen.class);

                            sharedPref2 = getApplicationContext().getSharedPreferences("Scores",0);
                            editor2 = sharedPref2.edit();
                            //editor2.putInt("ScoreAccount"+accountNum, accountNum);
                            editor2.putInt("Score_Easy_"+accountNum + "_" + counter, score);
                            counter++;
                            editor2.putInt("EasyScoreCounter", counter);
                            editor2.apply();

                            intent.putExtra("easyScore", score); // passing score to next page
                            startActivity(intent);
                            finish();
                        }
                    }, 8000);

                }
                else {
                    // clearing out the buttons for incorrect answers
                    tv.setText("Try Again");
                    btn1.setBackground(clear);
                    btn2.setBackground(clear);
                    btn3.setBackground(clear);
                    btn4.setBackground(clear);
                    if (score > 0){score -= 10;} // decrementing the score
                }

            }
        });

    }

    private class MyStrtDragListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view){
            LVL3.WithDragShadow shadow = new LVL3.WithDragShadow(view);
            ClipData data= ClipData.newPlainText("","");
            view.startDrag(data, shadow,view,0);
            return false;
        }
    }

    private class endDragListener implements View.OnDragListener{
        @Override
        public boolean onDrag(View view, DragEvent dragEvent){
            if(dragEvent.getAction()==DragEvent.ACTION_DROP){
                ((Button)view).setBackground(
                        ((Button)dragEvent.getLocalState()).getBackground());
            }
            return true;
        }
    }

    private class WithDragShadow extends View.DragShadowBuilder{
        public WithDragShadow(View v){
            super(v);
        }

        @Override
        public void onDrawShadow(Canvas canvas){
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint){
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }

    }


}
