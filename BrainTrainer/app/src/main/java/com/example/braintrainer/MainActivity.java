package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    int correctAnswer,correct,wrong;
    Button next,go,button0,button1,button2,button3;
    TextView scoreboard,timeleft, question,result,name,ques;
    ImageView party,img;
    GridLayout options;

    public void onStart(View view){
        correct=0;wrong=0;
        party.setVisibility(View.INVISIBLE);
        scoreboard.setText(Integer.toString(correct)+"/"+Integer.toString(correct+wrong));
        next.setText("GO TO NEXT QUESTION");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNext(v);
            }
        });
        onNext(view);
        CountDownTimer timer=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String sec=Long.toString(millisUntilFinished/1000);
                if(sec.length()<2)
                    sec = "0" + sec;
                timeleft.setText(sec+"s");
            }

            @Override
            public void onFinish() {
                showResult();
            }
        };
        timer.start();
    }

    public void onNext(View view){

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        getQues();
        go.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        name.setVisibility(View.INVISIBLE);
        options.setVisibility(View.VISIBLE);
        timeleft.setVisibility(View.VISIBLE);
        scoreboard.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);
    }

    public void getAnswer(View view){
        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        int val=Integer.parseInt(String.valueOf(view.getTag()));
        if(val==correctAnswer){
           result.setText("Correct Answer!");
           correct++;
        }
        else{
            result.setText("Wrong Answer!");
            wrong++;
        }
        scoreboard.setText(Integer.toString(correct)+"/"+Integer.toString(correct+wrong));
        next.setVisibility(View.VISIBLE);
    }

    public void getQues(){
        next.setVisibility(View.INVISIBLE);
        Random rand= new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int sum = a+b;
        ques.setText(Integer.toString(a)+" + "+Integer.toString(b));
        int loc = rand.nextInt(4);
        ArrayList<Integer> answers = new ArrayList<>();
        for(int i=0;i<4;i++){
            if(i==loc)
                answers.add(sum);
            else {
                int x=rand.nextInt(5);
                while(x==0 || answers.contains(sum+x))
                    x=rand.nextInt(5);
                answers.add(sum + x);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button0.setTag(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button1.setTag(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button2.setTag(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
        button3.setTag(Integer.toString(answers.get(3)));
        result.setText("Please choose an option");
        correctAnswer=sum;
    }

    public void showResult(){
        options.setVisibility(View.INVISIBLE);
        timeleft.setVisibility(View.INVISIBLE);
        scoreboard.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        party.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
        result.setText("Time over! Final Score: "+correct+" out of "+(correct+wrong));
        next.setText("START AGAIN");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStart(v);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        next = (Button)findViewById(R.id.nextButton);
        scoreboard = (TextView)findViewById(R.id.scoreBoard);
        party = (ImageView) findViewById(R.id.partyImg);
        timeleft = (TextView)findViewById(R.id.timeLeft);
        go = (Button)findViewById(R.id.goButton);
        timeleft = (TextView)findViewById(R.id.timeLeft);
        scoreboard = (TextView)findViewById(R.id.scoreBoard);
        question = (TextView)findViewById(R.id.quesView);
        result = (TextView)findViewById(R.id.resultView);
        name = (TextView)findViewById(R.id.appName);
        options = (GridLayout)findViewById(R.id.ansGrid);
        img = (ImageView) findViewById(R.id.image1);
        button0 = findViewById(R.id.first);
        button1 = findViewById(R.id.second);
        button2 = findViewById(R.id.thrid);
        button3 = findViewById(R.id.forth);
        ques = (TextView) findViewById(R.id.quesView);
    }
}