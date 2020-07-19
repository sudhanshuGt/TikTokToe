package com.tdevelopments.tik_tok_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    int[] gameState = {2 , 2 , 2 , 2 , 2, 2 , 2, 2 , 2};

    int[] [] winningPositions  = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1 ,4 ,7}, {2 ,5 , 8}, {0 , 4 , 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view) {

        MediaPlayer player = null ;

        ImageView Tiktoe = (ImageView) view;


        int tappedTiktoe = Integer.parseInt(Tiktoe.getTag().toString());




      if (gameState[tappedTiktoe] == 2 && gameActive) {

          gameState[tappedTiktoe] = activePlayer;

          Tiktoe.setTranslationY(-1500);

          if (player == null) {
              player = MediaPlayer.create(this, R.raw.music);
          }

          if (activePlayer == 0 ) {


              player.start();
              Tiktoe.setImageResource(R.drawable.red);
              activePlayer = 1;

          } else {

              

              Tiktoe.setImageResource(R.drawable.blue);
              activePlayer = 0;

          }

          Tiktoe.animate().translationYBy(1500).setDuration(300);




              for (int[] winningPosition : winningPositions) {
                  if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                      gameActive = false;

                      String winner = "";

                      if (activePlayer == 0) {

                          winner = "blue";
                      } else {
                          winner = "red";
                      }


                      Button playagainButton = (Button) findViewById(R.id.playAgainButton);
                      TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                      winnerTextView.setText(winner + " has won the game !");

                      playagainButton.setVisibility(View.VISIBLE);
                      winnerTextView.setVisibility(View.VISIBLE);

                  }




              }





          }


      }


    

    public  void playAgain(View view) {


        Button playagainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);



        playagainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);


        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);


        for (int i = 0; i<gridLayout.getChildCount(); i++) {
            
            ImageView Tiktoe = (ImageView) gridLayout.getChildAt(i);
            Tiktoe.setImageDrawable(null);
        }

         for (int i=0; i< gameState.length; i++) {

             gameState[i] = 2;
             
         }

         activePlayer = 0;
         gameActive = true;
        

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }



}
