package org.meicode.ticktactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // activePlayer = 0 red , = 1 yellow
    int activePlayer = 0;
    boolean gameActive  = true;
    int arrChoose [] = {2 , 2 , 2 , 2 , 2, 2, 2, 2, 2};
    int arrWinning [][] = {{0,1,2} , {3,4,5} , {6,7,8} , {0,3,6} , {1,4,7} , {2,5,8},{0,4,8} , {2,4,6}};
    //2 is un chose
    public void choose(View view) {

        ImageView counter = (ImageView) view;

        int position = Integer.parseInt(counter.getTag().toString());
        int oldPos = activePlayer;
        if (arrChoose[position] == 2 && gameActive == true) {
            arrChoose[position] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            counter.setTranslationY(-1000f);
            counter.animate().alpha(1f)
                    .translationYBy(1000f)
                    .rotation(3600f)
                    .setDuration(300);
        }
        for(int[] arrWinnings : arrWinning){

            if(arrChoose[arrWinnings[0]] == arrChoose[arrWinnings[1]] && arrChoose[arrWinnings[2]] == arrChoose[arrWinnings[1]] &&arrChoose[arrWinnings[1]] != 2){
                String str_winner = "";
                if(oldPos == 0) str_winner = "Red";
                else if(oldPos == 1) str_winner = "Yellow";
                gameActive = false;
                TextView tv_winner =(TextView) findViewById(R.id.tv_winner);
                tv_winner.setText(str_winner + " has won !!");
                LinearLayout layout_over = (LinearLayout) findViewById(R.id.ln_over);
                layout_over.setVisibility(View.VISIBLE);
            }
            else{
                boolean over = true;
                for(int arr : arrChoose){
                    if(arr == 2) over = false;
                }
                if(over){
                    TextView tv_winner =(TextView) findViewById(R.id.tv_winner);
                    tv_winner.setText( "It's draw !! ");
                    LinearLayout layout_over = (LinearLayout) findViewById(R.id.ln_over);
                    layout_over.setVisibility(View.VISIBLE);
                }
            }

        }

    }
    public void playAgain(View view){
        gameActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.ln_over);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0 ;
        for(int i = 0 ; i < arrChoose.length ; i++) arrChoose[i] = 2;
        GridLayout gridLayout = (GridLayout) findViewById(R.id.board);
        for(int i = 0 ; i < gridLayout.getChildCount() ; i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}