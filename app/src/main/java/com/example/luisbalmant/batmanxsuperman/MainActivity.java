package com.example.luisbalmant.batmanxsuperman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = Superman
    // 1 = Batman
    int activePlayer = 0;

    // 2 means = unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    boolean gameIsActive = true;

    int[][] winnerPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        //System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        /**if**/
        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.if_superman);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.if_batman);

                activePlayer = 0;

            }

            counter.animate().translationYBy(-1000f).rotation(360).setDuration(300);

            for (int[] winningPositions : winnerPositions) {

                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                        gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]] != 2) {

                    // Someone has won!
                    gameIsActive = false;

                    String winner = "Batman";

                    if (gameState[winningPositions[0]] == 0) {

                        winner = "Superman";

                    }

                    TextView winnerMessage_textView = findViewById(R.id.winnerMessage_textView);

                    winnerMessage_textView.setText(winner + " has won!");

                    LinearLayout playAgain_Layout = findViewById(R.id.playAgain_Layout);
                    playAgain_Layout.setVisibility(View.VISIBLE);

                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameState) {

                        if (counterState == 2) gameIsOver = false;

                    }

                    if (gameIsOver) {

                        TextView winnerMessage_textView = findViewById(R.id.winnerMessage_textView);

                        winnerMessage_textView.setText("Nobody won!");

                        LinearLayout playAgain_Layout = findViewById(R.id.playAgain_Layout);
                        playAgain_Layout.setVisibility(View.VISIBLE);

                    }
                }
            }

        }
    }

    public void playAgain_Button(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
