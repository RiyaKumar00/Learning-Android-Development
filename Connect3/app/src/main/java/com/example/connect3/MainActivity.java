package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameOver = false;
    int movesCount=0;
    public void DropIn(View view){
        if(!gameOver) {
            movesCount++;
            ImageView img = (ImageView) view;
            int counter = Integer.parseInt(img.getTag().toString());
            if(gameState[counter]==2) {
                img.setTranslationY(-1500);
                gameState[counter] = activePlayer;
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.red);
                    activePlayer = 1;
                } else {
                    img.setImageResource(R.drawable.yellow);
                    activePlayer = 0;
                }
                img.animate().translationYBy(1500).setDuration(500);
                for (int[] pos : winningPositions) {
                    if (gameState[pos[0]] == gameState[pos[1]] && gameState[pos[1]] == gameState[pos[2]] && gameState[pos[0]] != 2) {
                        String winner;
                        if (gameState[pos[0]] == 1) {
                            winner = "Yellow";
                        } else {
                            winner = "Red";
                        }
                        gameOver = true;
                        DisplayResult(winner);
                    }
                }
            }
            if(movesCount==9){
                DisplayResult("No one");
                gameOver = true;
            }
        }
    }
    public void DisplayResult(String winner){
        TextView res = (TextView) findViewById(R.id.winnerTextView);
        res.setText(winner + " has won !");
        res.setVisibility(View.VISIBLE);
        Button playAgain = (Button) findViewById(R.id.button2);
        playAgain.setVisibility(View.VISIBLE);
    }
    public void NewGame(View view){
        TextView res = (TextView) findViewById(R.id.winnerTextView);
        Button playAgain = (Button) findViewById(R.id.button2);
        res.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        for(int i=0;i<9;i++){
            gameState[i]=2;
        }
        GridLayout grid = (GridLayout) findViewById(R.id.GridLay);
        for(int i=0;i<grid.getChildCount();i++){
            ImageView img = (ImageView) grid.getChildAt(i);
            img.setImageDrawable(null);
        }
        activePlayer = 0;
        gameOver = false;
        movesCount = 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}