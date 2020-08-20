package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;
    int[][] winning = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropin(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int[] win:winning){
                if(gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] &&
                gameState[win[1]] !=2){
                    System.out.println();
                    String message = "Cross";
                    if(gameState[win[0]] == 0){
                        message = "Circle";
                    }
                    TextView winner = findViewById(R.id.WinnerMsg);
                    winner.setText(message+" has won");
                    LinearLayout window = findViewById(R.id.WinnerlLayout);
                    window.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout window = findViewById(R.id.WinnerlLayout);
        window.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i =0 ; i<gameState.length;i++)
        {
            gameState[i] = 2;
        }
        GridLayout gridLay = findViewById(R.id.grid);
        for(int i =1;i<gridLay.getChildCount();i++){
            ((ImageView) gridLay.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
