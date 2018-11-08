package com.example.kapis.individualproject3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Intent;
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

public class LVL2 extends AppCompatActivity {


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

    LVL2.MyStrtDragListener startDrag;
    LVL2.endDragListener endDrag;

    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl2);

        // gets score from previous activity
        Intent i = getIntent();
        score = i.getIntExtra("ttlscore", 0);

        startDrag = new LVL2.MyStrtDragListener();
        endDrag = new LVL2.endDragListener();
        go = (Button)findViewById(R.id.lv2go);

        upbtn = (Button)findViewById(R.id.lv2upBtn);
        downbtn = (Button)findViewById(R.id.lv2downBtn);
        leftbtn = (Button)findViewById(R.id.lv2leftBtn);
        rightbtn = (Button)findViewById(R.id.lv2rightBtn);
        btn1  = (Button)findViewById(R.id.lv2Btn1);
        btn2  = (Button)findViewById(R.id.lv2Btn2);
        btn3 = (Button)findViewById(R.id.lv2Btn3);
        btn4  = (Button)findViewById(R.id.lv2Btn4);

        clear = btn1.getBackground();

        upbtn.setOnLongClickListener(startDrag);
        downbtn.setOnLongClickListener(startDrag);
        leftbtn.setOnLongClickListener(startDrag);
        rightbtn.setOnLongClickListener(startDrag);

        btn1.setOnDragListener(endDrag);
        btn2.setOnDragListener(endDrag);
        btn3.setOnDragListener(endDrag);
        btn4.setOnDragListener(endDrag);

        guy = (ImageView)findViewById(R.id.lv2imageView);

        tv = (TextView)findViewById(R.id.lv2tv1);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(btn1.getBackground() == rightbtn.getBackground()
                       && btn2.getBackground() == upbtn.getBackground()) { //821a49d
                   tv.setText("Good Job!\nYour Score So Far Is: " + score);

                   ObjectAnimator a1 = ObjectAnimator.ofFloat(guy, "translationX", 525f);
                   a1.setDuration(1500);
                   ObjectAnimator a2 = ObjectAnimator.ofFloat(guy, "translationY", -525f);
                   a2.setDuration(1500);
                   AnimatorSet set = new AnimatorSet();
                   set.playSequentially(a1, a2);
                   set.start();

                   new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           Intent intent = new Intent(LVL2.this, LVL3.class);
                           intent.putExtra("ttlscore", score);
                           startActivity(intent);
                           finish();
                       }
                   }, 6000);

               }
               else {
                   tv.setText("Try Again");
                   btn1.setBackground(clear);
                   btn2.setBackground(clear);
                   btn3.setBackground(clear);
                   btn4.setBackground(clear);
                   if (score > 0){score -= 10;}
               }

            }
        });

    }

    private class MyStrtDragListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view){
            LVL2.WithDragShadow shadow = new LVL2.WithDragShadow(view);
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
