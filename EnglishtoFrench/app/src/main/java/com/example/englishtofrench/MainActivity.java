package com.example.englishtofrench;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void translate(View view){
        Button pressed = (Button) view;
        String num = pressed.getTag().toString();
        if(num.equals("0")) {
            MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.doyouspeakenglish);
            mediaplayer.start();
        }
        else if(num.equals("1")){
            MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.goodevening);
            mediaplayer.start();
        }
        else if(num.equals("2")){
            MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.hello);
            mediaplayer.start();
        }
        else if(num.equals("3")){
            MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.howareyou);
            mediaplayer.start();
        }
        else if(num.equals("4")){
            MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.ilivein);
            mediaplayer.start();
        }
        else if(num.equals("5")){
            MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.mynameis);
            mediaplayer.start();
        }
        else if(num.equals("6")){
            MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.please);
            mediaplayer.start();
        }
        else{
            MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.welcome);
            mediaplayer.start();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}