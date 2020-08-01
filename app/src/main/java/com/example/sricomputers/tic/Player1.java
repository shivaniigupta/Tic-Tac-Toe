package com.example.sricomputers.tic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Player1 extends AppCompatActivity{
    Button b1, b2;
    EditText e1,e2;

    String s1,s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player1);
        b1 = (Button) findViewById(R.id.button7);
        b2 = (Button) findViewById(R.id.button6);
        e1 = (EditText) findViewById(R.id.editText7);
        e2 = (EditText) findViewById(R.id.editText11);
        // Loading spinner data from database
        //loadSpinnerData();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Player1.this, Newplayer1.class);
                startActivity(i);
                finish();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s3 = e2.getText().toString();
                if (s1.equals("") && s3.equals("")) {

                    Toast.makeText(Player1.this, "Select an option", Toast.LENGTH_SHORT).show();
                } else {
                        SQLiteDatabase data = openOrCreateDatabase("Net", MODE_PRIVATE, null);
                    data.execSQL("create table if not exists final(name varchar, handler varchar, email varchar, password varchar, win integer, loss integer)");
                    String s2 = "select * from final where handler='" + s1 + "' and password='" + s3 + "'";
                    Cursor cursor = data.rawQuery(s2, null);
                    if(cursor.getCount()>0)
                    {


                        Toast.makeText(Player1.this, "Pass phone to second player", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(Player1.this, Player2.class);
                        i.putExtra("player1", s1);
                        startActivity(i);
                        finish();
                        //data.execSQL("insert into camp values('" + s1 + "','" + s2 + "','" + s3 + "')");
                    }

else
                        {
                            Toast.makeText(Player1.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }


                }
            }
        });

    }
}



