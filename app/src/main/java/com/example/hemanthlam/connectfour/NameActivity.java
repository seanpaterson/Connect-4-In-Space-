package com.example.hemanthlam.connectfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class NameActivity extends AppCompatActivity {
    protected String Board;
    protected String gameType;
    protected String Rounds;
    protected EditText player1Name;
    protected EditText player2Name;
    protected String player1Color;
    protected String player2Color;
    protected RadioButton p1blue, p1red, p1green, p1purple;
    protected RadioButton p2blue, p2red, p2green, p2purple;

// Collects the information from the BoardTypeActivity including the grid size, game type, and round number
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        Board = getIntent().getStringExtra("Board");
        gameType = getIntent().getStringExtra("Game");
        Rounds = getIntent().getStringExtra("Round");
        player1Name = (EditText) findViewById(R.id.player1Name);
        player2Name = (EditText) findViewById(R.id.player2Name);
        p1blue = (RadioButton) findViewById(R.id.player1BlueButton);
        p1red = (RadioButton) findViewById(R.id.player1RedButton);
        p1green = (RadioButton) findViewById(R.id.player1GreenButton);
        p1purple = (RadioButton) findViewById(R.id.player1PurpleButton);
        p2blue = (RadioButton) findViewById(R.id.player2BlueButton);
        p2red = (RadioButton) findViewById(R.id.player2RedButton);
        p2green = (RadioButton) findViewById(R.id.player2GreenButton);
        p2purple = (RadioButton) findViewById(R.id.player2PurpleButton);
        player1Color = "Blue";
        player2Color = "Red";
        radioButtonLogic();
        if(gameType.equals("Online Multiplayer")||gameType.equals("AI Mode (Single Player)")){
            player2Name.setText("Not available in this mode");
            player2Name.setFocusable(false);
            p2blue.setFocusable(false);
            p2red.setFocusable(false);
            p2green.setFocusable(false);
            p2purple.setFocusable(false);
        }
    }

// When the user clicks the start button, a lot of information is passed over to the activities
    protected void clickStart(View view){
        Intent intent = null;
        switch (Board){
            case "7 x 6":
                intent = new Intent(getApplicationContext(), Game1Activity.class);
                intent.putExtra("Board", "7 x 6");
                intent.putExtra("Game", gameType);
                intent.putExtra("Round",Rounds);
                intent.putExtra("Player1", player1Name.getText().toString());
                intent.putExtra("Player1Color", player1Color);
                intent.putExtra("Player2Color", player2Color);
                if(gameType.equals("Local Multiplayer"))
                    intent.putExtra("Player2", player2Name.getText().toString());
                break;
            case "8 x 7":
                intent = new Intent(getApplicationContext(), Game2Activity.class);
                intent.putExtra("Board", "8 x 7");
                intent.putExtra("Game", gameType);
                intent.putExtra("Round",Rounds);
                intent.putExtra("Player1", player1Name.getText().toString());
                intent.putExtra("Player1Color", player1Color);
                intent.putExtra("Player2Color", player2Color);
                if(gameType.equals("Local Multiplayer"))
                    intent.putExtra("Player2", player2Name.getText().toString());
                break;
            case "10 x 8":
                intent = new Intent(getApplicationContext(), Game3Activity.class);
                intent.putExtra("Board", "10 x 8");
                intent.putExtra("Game", gameType);
                intent.putExtra("Round",Rounds);
                intent.putExtra("Player1", player1Name.getText().toString());
                intent.putExtra("Player1Color", player1Color);
                intent.putExtra("Player2Color", player2Color);
                if(gameType.equals("Local Multiplayer"))
                    intent.putExtra("Player2", player2Name.getText().toString());
                break;
        }
        startActivity(intent);
    }

// Creates the button logic for all of the radio buttons
    protected void radioButtonLogic(){
        p1blue.setChecked(true);
        p2blue.setEnabled(false);
        p2red.setChecked(true);
        p1red.setEnabled(false);
        p1blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Color = "Blue";
                p2blue.setEnabled(false);
                p2green.setEnabled(true);
                p2red.setEnabled(true);
                p2purple.setEnabled(true);
                p1green.setChecked(false);
                p1purple.setChecked(false);
                p1red.setChecked(false);

            }
        });
        p1red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Color = "Red";
                p2red.setEnabled(false);
                p2green.setEnabled(true);
                p2blue.setEnabled(true);
                p2purple.setEnabled(true);
                p1green.setChecked(false);
                p1purple.setChecked(false);
                p1blue.setChecked(false);

            }
        });
        p1green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Color = "Green";
                p2green.setEnabled(false);
                p2blue.setEnabled(true);
                p2red.setEnabled(true);
                p2purple.setEnabled(true);
                p1blue.setChecked(false);
                p1purple.setChecked(false);
                p1red.setChecked(false);

            }
        });
        p1purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Color = "Purple";
                p2purple.setEnabled(false);
                p2green.setEnabled(true);
                p2red.setEnabled(true);
                p2blue.setEnabled(true);
                p1green.setChecked(false);
                p1blue.setChecked(false);
                p1red.setChecked(false);

            }
        });
        p2blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Color = "Blue";
                p1blue.setEnabled(false);
                p1green.setEnabled(true);
                p1red.setEnabled(true);
                p1purple.setEnabled(true);
                p2green.setChecked(false);
                p2purple.setChecked(false);
                p2red.setChecked(false);

            }
        });
        p2red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Color = "Red";
                p1red.setEnabled(false);
                p1green.setEnabled(true);
                p1blue.setEnabled(true);
                p1purple.setEnabled(true);
                p2green.setChecked(false);
                p2purple.setChecked(false);
                p2blue.setChecked(false);

            }
        });
        p2green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Color = "Green";
                p1green.setEnabled(false);
                p1blue.setEnabled(true);
                p1red.setEnabled(true);
                p1purple.setEnabled(true);
                p2blue.setChecked(false);
                p2purple.setChecked(false);
                p2red.setChecked(false);

            }
        });
        p2purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Color = "Purple";
                p1purple.setEnabled(false);
                p1green.setEnabled(true);
                p1red.setEnabled(true);
                p1blue.setEnabled(true);
                p2green.setChecked(false);
                p2blue.setChecked(false);
                p2red.setChecked(false);

            }
        });
    }
}