package com.example.a15_squarespuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/* Name: Anish Karumuri
   Date: 2/27/23
   Class: CS301-B
 */
public class StartScreen extends AppCompatActivity {

    private Button buttonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartGame = findViewById(R.id.button_start_game);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartScreen.this,Main.class)); //when start button clicked the puzzle appears

            }
        });

    }
}