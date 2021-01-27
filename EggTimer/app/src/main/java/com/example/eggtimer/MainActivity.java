package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar bar = (SeekBar) findViewById(R.id.seekBar);
        bar.setMax(300001);
        TextView value = (TextView) findViewById(R.id.textView);
        MediaPlayer sound = MediaPlayer.create(this, R.raw.beep);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            CountDownTimer timer;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String min=Long.toString(progress/60000);
                String sec=Long.toString((progress/1000)%60);
                if(min.length()<2)
                    min = "0" + min;
                if(sec.length()<2)
                    sec = "0" + sec;
                value.setText(min + ":" + sec);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(timer!=null){
                    timer.cancel();
                }
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                timer = new CountDownTimer(seekBar.getProgress(),1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String min=Long.toString(millisUntilFinished/60000);
                        String sec=Long.toString((millisUntilFinished/1000)%60);
                        if(min.length()<2)
                            min = "0" + min;
                        if(sec.length()<2)
                            sec = "0" + sec;
                        value.setText(min + ":" + sec);
                        Log.i("check",Long.toString(millisUntilFinished/1000));
                    }

                    @Override
                    public void onFinish() {
                        sound.start();
                    }
                }.start();
            }
        });
    }
}