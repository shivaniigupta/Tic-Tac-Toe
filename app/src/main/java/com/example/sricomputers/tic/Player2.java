package com.example.sricomputers.tic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Player2 extends AppCompatActivity {

    Button b1,b2;
    EditText e1,e2;
    String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);
        b1=(Button)findViewById(R.id.button13);
        b2=(Button)findViewById(R.id.button14);
     e1=(EditText)findViewById(R.id.editText8);
     e2=(EditText)findViewById(R.id.editText12);
        final Intent intent2 = getIntent();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Bundle bundle = intent2.getExtras();
              String player1 = bundle.getString("player1");

                Intent i=new Intent(Player2.this,Newplayer2.class);
            i.putExtra("player1",player1);
                startActivity(i);
                finish();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = intent2.getExtras();
                String player1 = bundle.getString("player1");


                s1 = e1.getText().toString();
                String s3 = e2.getText().toString();
                if (s1.equals("") && s3.equals("")) {

                    Toast.makeText(Player2.this, "Select an option", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase data = openOrCreateDatabase("Net", MODE_PRIVATE, null);
                    data.execSQL("create table if not exists final(name varchar, handler varchar, email varchar, password varchar, win integer, loss integer)");
                    String s2 = "select * from final where handler='" + s1 + "' and password='" + s3 + "'";
                    Cursor cursor = data.rawQuery(s2, null);
                    if (cursor.getCount() >0) {
                        Toast.makeText(Player2.this, "Lets start", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Player2.this, Twoplayer.class);
                        i.putExtra("player2",s1);
                        i.putExtra("player1",player1);
                        startActivity(i);
                        finish();
                         }
                    else
                    {
                        Toast.makeText(Player2.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                    }
                }    }});





}
}