package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void btnClick(View view){
        TextView rupee = (TextView) findViewById(R.id.editTextName);
        String Rs = rupee.getText().toString();
        double R = (double) Double.parseDouble(Rs);
        double D = R*0.014;
        String Ds = Double.toString(D);
        Toast.makeText(this, Rs+" Rupees in Dollars is "+Ds+" .", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}