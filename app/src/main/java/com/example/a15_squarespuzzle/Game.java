package com.example.a15_squarespuzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {

    private int emptyX = 4; //where the empty space is located
    private int emptyY = 4; // where the empty space is located
    private RelativeLayout group; //groups the buttons
    private Button[][] buttons; //2D array of buttons
    private int[] tiles; //array of tiles
    private Button shuffleButton; //Shuffle button




    @Override
    protected void onCreate(Bundle savedInstanceState) { //runs these methods
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        views();
        numbers();
        createNum();
        loadDataTOViews();



    }

    private void loadDataTOViews() {
        emptyX = 3;
        emptyY = 3;
        for (int i = 0; i < group.getChildCount() - 1; i++) {
            buttons[i/4][i%4].setText(String.valueOf(tiles[i]));
            buttons[i/4][i%4].setBackgroundResource(android.R.drawable.btn_default);

        }
        buttons[emptyX][emptyY].setText("");
        buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this,R.color.emptyspace));
    }
    private void createNum(){
        // generates random integers
        // the while loop shuffles the array and swaps the values at random indexes
        int n = 15;
        Random random = new Random();
        while(n > 1) {
            int randomNum = random.nextInt(n--);
            int temp = tiles[randomNum];
            tiles[randomNum] = tiles[n];
            tiles[n] = temp;

        }
        if(!isSolvable()) //this method checks if the puzzle actually solvable
            createNum();


    }
    private boolean isSolvable() { //checks if the puzzel is solvable
        int countInversion = 0;
        for (int i = 0; i < 15; i++){
            for(int j = 0; j < i; j++){
                if (tiles[j] > tiles [i])
                    countInversion++;
            }

        }
        return countInversion%2 == 0;
    }
    private  void numbers() { //loads the numbers
        tiles = new int[16];
        for(int i = 0; i < group.getChildCount() - 1; i++){
            tiles[i] = i + 1;
        }

    }
    private void views(){
        //places the buttons on the screen
        //makes a two dimensional array to store references to each of the buttons in the grid
        group = findViewById(R.id.group);
        buttons = new Button[4][4];
        shuffleButton = findViewById(R.id.button_shuffle);


        for (int i = 0; i < group.getChildCount(); i++){
            buttons[i/4][i%4] = (Button)  group.getChildAt(i);

        }
        shuffleButton.setOnClickListener(new View.OnClickListener() { //generates a new random pattern when shuffle button is pressed
            @Override
            public void onClick(View view){
                createNum();
                loadDataTOViews();

            }
        });
    }


    public void buttonClick(View view){
        //checks if the adjacent button is empty
        // if it is empty the text and background color swap
        Button button = (Button) view;
        int x = button.getTag().toString().charAt(0)- '0';
        int y = button.getTag().toString().charAt(1)- '0';

        if ((Math.abs(emptyX - x) == 1 && emptyY == y) || (Math.abs(emptyY - y) == 1 && emptyX == x)){
            buttons[emptyX][emptyY].setText(button.getText().toString());
            buttons[emptyX][emptyY].setBackgroundResource(android.R.drawable.btn_default);
            button.setText("");
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.emptyspace));
            emptyX = x;
            emptyY = y;
            winner(); // this method is called to check with player won


        }
    }

    private void winner(){ //checks if player won
        boolean win = false;
        if (emptyX == 3 && emptyY == 3){
            for(int i = 0; i < group.getChildCount() - 1; i++) {
                if (buttons[i / 4][i % 4].getText().toString().equals(String.valueOf(i + 1))){
                    win = true;
                }else{
                    win = false;
                    break;
                }
            }
        }
        if (win) { //if user wins play the win message and send user back to home screen
            Toast.makeText(this,"Win", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Game.this,Game.class));
            shuffleButton.setClickable(false);


        }
    }

}