package com.raisa.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
                if(testForWinner() == activePlayer){
                        winnerBanner.setTextColor( Color.YELLOW );
                        winnerBanner.setText("Yellow Wins!!!");
                    for(int i = 0; i < gameState.length; i++){
                        gameState[i] = this.activePlayer;
                    }
                }
                activePlayer = 1;
            } else {
                counter.setImageResource( R.drawable.red );
                if(testForWinner() == activePlayer){
                    winnerBanner.setTextColor(Color.RED);
                    winnerBanner.setText("Red Wins!!!");
                    for(int i = 0; i < gameState.length; i++){
                        gameState[i] = this.activePlayer;
                    }
                }
                activePlayer = 0;

            }
            counter.animate().translationYBy( 1000 ).setDuration( 300 );
        }

    }

    public int testForWinner(){
        //no winner yet
        int winner = -1;
        switch (this.activePlayer){
            case 0:
                if (checkWinningPositions(winningPositions[0], this.activePlayer) == activePlayer ||
                checkWinningPositions(winningPositions[1], this.activePlayer) == activePlayer ||
                checkWinningPositions(winningPositions[2], this.activePlayer)  == activePlayer){
                    winner = activePlayer;
                }
                break;
            case 1:
                if (checkWinningPositions(winningPositions[0], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[3], this.activePlayer) == activePlayer ){
                    winner = activePlayer;
                }
                break;
            case 2:
                if (checkWinningPositions(winningPositions[0], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[4], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[5], this.activePlayer)  == activePlayer){
                    winner = activePlayer;
                }
                break;
            case 3:
                if (checkWinningPositions(winningPositions[1], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[6], this.activePlayer)  == activePlayer){
                    winner = activePlayer;
                }
                break;
            case 4:
                if (checkWinningPositions(winningPositions[2], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[3], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[4], this.activePlayer)  == activePlayer ||
                        checkWinningPositions(winningPositions[6], this.activePlayer)  == activePlayer){
                    winner = activePlayer;
                }
                break;
            case 5:
                if (checkWinningPositions(winningPositions[5], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[6], this.activePlayer) == activePlayer ){
                    winner = activePlayer;
                }
                break;
            case 6:
                if (checkWinningPositions(winningPositions[1], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[4], this.activePlayer) == activePlayer ){
                    winner = activePlayer;
                }
                break;
            case 7:
                if (checkWinningPositions(winningPositions[3], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[7], this.activePlayer) == activePlayer ){
                    winner = activePlayer;
                }
                break;
            case 8:
                if (checkWinningPositions(winningPositions[2], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[5], this.activePlayer) == activePlayer ||
                        checkWinningPositions(winningPositions[7], this.activePlayer)  == activePlayer){
                    winner = activePlayer;
                }
                break;
            default:
                winner = -1;
        }

        return winner;
    }

    public int checkWinningPositions(int[] check, int currPlayer){
        int winner = -1;
        if(gameState[check[0]] == currPlayer && gameState[check[1]] == currPlayer &&
                gameState[check[2]] == currPlayer){
            winner = currPlayer;
        }

        return  winner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }
}
