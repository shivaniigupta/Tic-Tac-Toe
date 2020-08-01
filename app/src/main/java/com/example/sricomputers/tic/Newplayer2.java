package com.example.sricomputers.tic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Newplayer2 extends AppCompatActivity {
    EditText e1, e2, e3,e4;
    Button b1;
;    String s1, s2, s3, s4,s5;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newplayer2);
//        setContentView(R.layout.activity_newplayer1);

        e1 = (EditText) findViewById(R.id.editText4);
        e2 = (EditText) findViewById(R.id.editText5);
        e3 = (EditText) findViewById(R.id.editText6);
        e4 = (EditText) findViewById(R.id.editText10);
        b1 = (Button) findViewById(R.id.button15);
        //b2 = (Button) findViewById(R.id.button12);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);


        b1.setOnClickListener(new View.OnClickListener()

                              {


                                  public void onClick(View view) {
                                      int selectedId = radioSexGroup.getCheckedRadioButtonId();

                                      // find the radiobutton by returned id
                                      radioSexButton = (RadioButton) findViewById(selectedId);
                                      /*if(selectedId==1)
                                      {s4="Male";}
                                      else
                                      {s4="Female";};*/

                                      //s4= radioSexButton.getText();

                                      Intent intent2 = getIntent();
                                      Bundle bundle = intent2.getExtras();
                                      String player1 = bundle.getString("player1");
                                          s1 = e1.getText().toString();
                                      s2 = e2.getText().toString();
                                      s3 = e3.getText().toString();
                                      s5 = e4.getText().toString();

                                      if (s1.equals("") || s2.equals("") || s3.equals("") || s5.equals("")) {

                                          Toast.makeText(Newplayer2.this, "Fill all details", Toast.LENGTH_SHORT).show();
                                      } else {
                                          SQLiteDatabase data = openOrCreateDatabase("Net", MODE_PRIVATE, null);
                                          data.execSQL("create table if not exists final(name varchar, handler varchar, email varchar, password varchar, win integer, loss integer)");
                                          String s4 = "select * from final where handler='" + s2 + "'";
                                          Cursor cursor = data.rawQuery(s4, null);
                                          if (cursor.getCount() > 0) {
                                              Toast.makeText(Newplayer2.this, "User exists", Toast.LENGTH_SHORT).show();
                                          } else
                                          {
                                              data.execSQL("insert into final values('" + s1 + "','" + s2 + "','" + s3 + "','" + s5 + "','0','0')");
                                              Toast.makeText(Newplayer2.this, "lets start", Toast.LENGTH_SHORT).show();
                                              Intent i = new Intent(Newplayer2.this, Twoplayer.class);
                                              i.putExtra("player1",player1);
                                              i.putExtra("player2",s2);
                                              startActivity(i);
                                              finish();
                                          }
                                      }
                                  }
                              }

        );
    }
}