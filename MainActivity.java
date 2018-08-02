package com.example.adityakotalwar.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int currentPlayer = 0;

    boolean activeGame = true;

    // 2 means unplayed

    int[] gameBoard = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningMoves = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void fade(View view) {
        ImageView counter = (ImageView) view;



        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameBoard[tappedCounter] == 2 && activeGame) {

            gameBoard[tappedCounter] = currentPlayer;

            counter.setTranslationY(-1000f);

            if (currentPlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                currentPlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                currentPlayer = 0;

            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningMoves) {

                if (gameBoard[winningPosition[0]] == gameBoard[winningPosition[1]] &&
                        gameBoard[winningPosition[1]] == gameBoard[winningPosition[2]] &&
                        gameBoard[winningPosition[0]] != 2) {

                    // Someone has won!

                    activeGame = false;

                    String winner = "Red";

                    if (gameBoard[winningPosition[0]] == 0) {

                        winner = "Yellow";

                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);

                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameBoard) {

                        if (counterState == 2) gameIsOver = false;

                    }

                    if (gameIsOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }

                }

            }
        }
    }
    public void playAgain(View view) {

        activeGame = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        currentPlayer = 0;

        for (int i = 0; i < gameBoard.length; i++) {

            gameBoard[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
