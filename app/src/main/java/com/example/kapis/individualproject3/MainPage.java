package com.example.kapis.individualproject3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    Button startEasy;
    Button startHard;
    Button rtrn;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // playing background music
        mediaPlayer = MediaPlayer.create(MainPage.this, R.raw.background);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        // assigning values to the buttons
        startEasy = (Button)findViewById(R.id.strtEasy);
        startHard = (Button)findViewById(R.id.strtHard);
        rtrn = (Button)findViewById(R.id.rtrn);

        startEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtGameE();
            }
        });

        startHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtGameH();
            }
        });

        rtrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rtrnLogin();
            }
        });


    }

    public void strtGameE(){
        Intent intent = new Intent(this, LVL1.class);
        startActivity(intent);
    }

    public void strtGameH(){
        mediaPlayer.stop();
        Intent intent = new Intent(this, LVL4.class);
        startActivity(intent);
    }

    public void rtrnLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


}
