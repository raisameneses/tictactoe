package com.raisa.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 = yellow, 1 = red. Yellow goes first
    int activePlayer = 0;
    int position;
    //-1 means the spot is unplayed
    int [] gameState = {-1,-1, -1,-1,-1,-1,-1,-1,-1};
    int[][] winningPositions = {{0, 1, 2},{0, 3, 6},{0, 4, 8},{1, 4, 7}, {2, 4, 6}, {2, 5, 8}, {3, 4, 5},{6, 7, 8}};

    public void dropIn(View view){
        //get The image that was just tapped
        TextView winnerBanner = (TextView)findViewById( R.id.winner );
        ImageView counter = (ImageView) view;
        String getTag = counter.getTag().toString();
        position = Integer.parseInt(getTag);
        if(gameState[position] == -1) {
            counter.setTranslationY( -1000 );
            gameState[position] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource( R.drawable.yellow );
                activePlayer = 1;
            } else {
                counter.setImageResource( R.drawable.red );

                activePlayer = 0;

            }
            counter.animate().translationYBy( 1000 ).setDuration( 300 );

            for(int[]position : this.winningPositions){
                if(gameState[position[0]] ==  gameState[position[1]] &&
                        gameState[position[1]] == gameState[position[2]] &&
                        gameState[position[0]] != -1){
                    if( activePlayer == 1){
                    winnerBanner.setTextColor( Color.YELLOW );
                    winnerBanner.setText("Yellow Wins!!!");
                  } else{
                        winnerBanner.setTextColor( Color.RED );
                        winnerBanner.setText("Red Wins!!!");
                    }

                    for(int i = 0; i < gameState.length; i++){
                        gameState[i] = this.activePlayer;
                    }
                    break;
                }
            }
        }

    }

    public void reset(View view){
        TextView winnerBanner = (TextView)findViewById( R.id.winner );
        winnerBanner.setText(" ");
        this.activePlayer = 0;
        for(int i = 0; i < this.gameState.length; i++){
            this.gameState[i] = -1;
        }
        android.support.v7.widget.GridLayout layout = (android.support.v7.widget.GridLayout)findViewById( R.id.gridLayout);
        for(int i = 0; i < layout.getChildCount(); i++){
            ((ImageView) layout.getChildAt( i )).setImageResource( 0 );
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }
}
