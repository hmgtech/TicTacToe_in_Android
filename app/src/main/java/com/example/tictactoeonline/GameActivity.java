package com.example.tictactoeonline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    int gameState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameState = 1; // 1-can Play  2-GameOver  3-Draw

    }

    public void GameBoardClick(View view){
        ImageView selectedImage = (ImageView) view;
        int selectedBlock = 0;
        switch ((selectedImage.getId())) {
            case R.id.iv_11: selectedBlock = 1; break;
            case R.id.iv_12: selectedBlock = 2; break;
            case R.id.iv_13: selectedBlock = 3; break;

            case R.id.iv_21: selectedBlock = 4; break;
            case R.id.iv_22: selectedBlock = 5; break;
            case R.id.iv_23: selectedBlock = 6; break;

            case R.id.iv_31: selectedBlock = 7; break;
            case R.id.iv_32: selectedBlock = 8; break;
            case R.id.iv_33: selectedBlock = 9; break;
        }

        PlayGame(selectedBlock, selectedImage);
    }
    int activePlayer = 1;
    ArrayList<Integer> Player1 = new ArrayList<Integer>();
    ArrayList<Integer> Player2 = new ArrayList<Integer>();

    void PlayGame(int selectedBlock, ImageView selectedImage){
        if(gameState == 1) {
            if (activePlayer == 1) {
                selectedImage.setTranslationY(-1000f);
                selectedImage.setImageResource(R.drawable.ttt_x);
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
                Player1.add(selectedBlock);
                activePlayer = 2;
                AutoPlay();
            }else if (activePlayer == 2) {
                selectedImage.setTranslationY(-1000f);
                selectedImage.setImageResource(R.drawable.ttt_o);
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
                Player2.add(selectedBlock);
                activePlayer = 1;
            }
            selectedImage.animate().translationYBy(1000f).setDuration(300);

            selectedImage.setEnabled(false);
            CheckWinner();
        }
    }
    void CheckWinner(){
        int winner = 0;

        /********* for Player 1 *********/
        if(Player1.contains(1) && Player1.contains(2) && Player1.contains(3)){ winner = 1; }
        if(Player1.contains(4) && Player1.contains(5) && Player1.contains(6)){ winner = 1; }
        if(Player1.contains(7) && Player1.contains(8) && Player1.contains(9)){ winner = 1; }

        if(Player1.contains(1) && Player1.contains(4) && Player1.contains(7)){ winner = 1; }
        if(Player1.contains(2) && Player1.contains(5) && Player1.contains(8)){ winner = 1; }
        if(Player1.contains(3) && Player1.contains(6) && Player1.contains(9)){ winner = 1; }

        if(Player1.contains(1) && Player1.contains(5) && Player1.contains(9)){ winner = 1; }
        if(Player1.contains(3) && Player1.contains(5) && Player1.contains(7)){ winner = 1; }


        /********* for Player 2 *********/
        if(Player2.contains(1) && Player2.contains(2) && Player2.contains(3)){ winner = 2; }
        if(Player2.contains(4) && Player2.contains(5) && Player2.contains(6)){ winner = 2; }
        if(Player2.contains(7) && Player2.contains(8) && Player2.contains(9)){ winner = 2; }

        if(Player2.contains(1) && Player2.contains(4) && Player2.contains(7)){ winner = 2; }
        if(Player2.contains(2) && Player2.contains(5) && Player2.contains(8)){ winner = 2; }
        if(Player2.contains(3) && Player2.contains(6) && Player2.contains(9)){ winner = 2; }

        if(Player2.contains(1) && Player2.contains(5) && Player2.contains(9)){ winner = 2; }
        if(Player2.contains(3) && Player2.contains(5) && Player2.contains(7)){ winner = 2; }



        if(winner != 0 && gameState == 1){
            if(winner == 1){
                ShowAlert("Player 1 is winner");
            }else if(winner == 2){
                ShowAlert("Player 2 is winner");
            }
            gameState = 2;
        }
    }


    void AutoPlay(){
        ArrayList<Integer> emptyBlocks = new ArrayList<Integer>();

        for(int i=1; i<=9; i++){
            if(!(Player1.contains(i) || Player2.contains(i))){
                emptyBlocks.add(i);
            }
        }

        if(emptyBlocks.size() == 0) {
            CheckWinner();
            if(gameState == 1) {
//                AlertDialog.Builder b = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                ShowAlert("Draw");
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
            }
            gameState = 3;
        } else {
            Random r = new Random();
            int randomIndex = r.nextInt(emptyBlocks.size());
            int selectedBock = emptyBlocks.get(randomIndex);


            ImageView selectedImage = (ImageView) findViewById(R.id.iv_11);
            switch (selectedBock) {
                case 1: selectedImage = (ImageView) findViewById(R.id.iv_11); break;
                case 2: selectedImage = (ImageView) findViewById(R.id.iv_12); break;
                case 3: selectedImage = (ImageView) findViewById(R.id.iv_13); break;

                case 4: selectedImage = (ImageView) findViewById(R.id.iv_21); break;
                case 5: selectedImage = (ImageView) findViewById(R.id.iv_22); break;
                case 6: selectedImage = (ImageView) findViewById(R.id.iv_23); break;

                case 7: selectedImage = (ImageView) findViewById(R.id.iv_31); break;
                case 8: selectedImage = (ImageView) findViewById(R.id.iv_32); break;
                case 9: selectedImage = (ImageView) findViewById(R.id.iv_33); break;
            }
            PlayGame(selectedBock, selectedImage);
        }
    }


    void ResetGame(){
        gameState = 1;
        activePlayer = 1;
        Player1 = new ArrayList<Integer>();
        Player2 = new ArrayList<Integer>();

        ImageView iv;
        iv = (ImageView) findViewById(R.id.iv_11); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.iv_12); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.iv_13); iv.setImageResource(0); iv.setEnabled(true);

        iv = (ImageView) findViewById(R.id.iv_21); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.iv_22); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.iv_23); iv.setImageResource(0); iv.setEnabled(true);

        iv = (ImageView) findViewById(R.id.iv_31); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.iv_32); iv.setImageResource(0); iv.setEnabled(true);
        iv = (ImageView) findViewById(R.id.iv_33); iv.setImageResource(0); iv.setEnabled(true);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn");

    }

    void ShowAlert(String Title){
        View view = LayoutInflater.from(GameActivity.this).inflate(R.layout.custom_alert, null);
//        AlertDialog.Builder b = new AlertDialog.Builder(GameActivity.this, R.style.TransparentDialog);
        AlertDialog.Builder b = new AlertDialog.Builder(GameActivity.this);
        b.setTitle(Title)
                .setMessage("Start a new game?")
                .setView(view)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ResetGame();
                    }
                })
                .setNegativeButton("Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}