package com.example.sricomputers.tic;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Twoplayer extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    int player1Points;
    int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    String s2,s3;



    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twoplayer);
        textViewPlayer1 = (TextView) findViewById(R.id.text_view_p1);
        textViewPlayer2 = (TextView) findViewById(R.id.text_view_p2);
       b = (Button) findViewById(R.id.button12);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Intent intent2=getIntent();
        Bundle bundle=intent2.getExtras();
        final String Player1 = bundle.getString("player1");
        final String Player2 = bundle.getString("player2");

        final String s2 = Integer.toString(player1Points);
        final String s3 = Integer.toString(player2Points);

   b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                    /*AlertDialog.Builder builder = new AlertDialog.Builder(Twoplayer.this)
                            .setMessage("Want to add score and exit?")
                            .setCancelable(false)

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                      */ //             Toast.makeText(Twoplayer.this, "enteres", Toast.LENGTH_SHORT).show();




                                   SQLiteDatabase data = openOrCreateDatabase("Net", MODE_PRIVATE, null);
                                    data.execSQL("create table if not exists final(name varchar, handler varchar, email varchar, password varchar,win integer,loss integer)");


                                    String s1 = "select * from final where handler='" + Player1 + "'";
                                    Cursor cursor = data.rawQuery(s1, null);
                                    if (cursor.getCount() > 0) {
                                        data.execSQL("update final set win = win + '" + player1Points + "' and loss = loss + '" + player2Points + "'");
                                        Toast.makeText(Twoplayer.this, "Updated", Toast.LENGTH_SHORT).show();
                                    }
                                  String s4 = "select * from final where handler='" + Player2 + "'";
                                    Cursor cursor2 = data.rawQuery(s4, null);
                                    if (cursor2.getCount() > 0) {
                                        data.execSQL("update final set win= win+ '" + player2Points + "' and loss= loss+'" + player1Points + "'");
                                        Toast.makeText(Twoplayer.this, "Updated", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Twoplayer.this, Main.class);
                                     //   i.putExtra("player1", s1);
                                        startActivity(i);
                                        finish();
                                    }


                                }
                            });

                   // AlertDialog alert = builder.create();
                  //  alert.show();

                }




   // @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount== 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }



    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i<3; i++) {
            for (int j = 0; j <3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i<3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i<3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();
         final String Player1 = bundle.getString("player1");
        Toast.makeText(this, Player1.toString()+"  wins!", Toast.LENGTH_SHORT).show();

        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();
        final String Player2 =bundle.getString("player2");
        Toast.makeText(this, Player2.toString()+"  wins!", Toast.LENGTH_SHORT).show();

        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();
        final String Player1 = bundle.getString("player1");
        final  String Player2 =bundle.getString("player2");
        textViewPlayer1.setText(Player1.toString()+":  " + player1Points);
        textViewPlayer2.setText(Player2.toString()+":  " + player2Points);

           }

    private void resetBoard() {
        for (int i = 0; i<3; i++) {
            for (int j = 0; j <3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount= 0;
        player1Turn = true;
    }
}




