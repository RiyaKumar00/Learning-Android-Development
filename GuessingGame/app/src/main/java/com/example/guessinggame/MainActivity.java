package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    public int x = ThreadLocalRandom.current().nextInt(1, 21);
    public void clickFunc(View view){
        EditText num = (EditText) findViewById(R.id.editTextNum);
        String num1 = num.getText().toString();
        int n = Integer.parseInt(num1);
        if (n == x) {
            Toast.makeText(this, "CORRECT GUESS!", Toast.LENGTH_SHORT).show();
        }
        else if(n < x){
            Toast.makeText(this, "HIGHER!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "LOWER!", Toast.LENGTH_SHORT).show();
        }
    }
    public void clickNew(View view){
        x = ThreadLocalRandom.current().nextInt(1, 21);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}