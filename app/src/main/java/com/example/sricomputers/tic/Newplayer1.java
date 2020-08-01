package com.example.sricomputers.tic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Newplayer1 extends AppCompatActivity {
    EditText e1, e2, e3,e4;
    Button b1;
    String s1, s2, s3, s4,s5;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newplayer1);

        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText9);
        b1 = (Button) findViewById(R.id.button11);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);


        b1.setOnClickListener(new View.OnClickListener()

                              {


                                  public void onClick(View view) {
                                      int selectedId = radioSexGroup.getCheckedRadioButtonId();

                                     // find the radiobutton by returned id
                                      radioSexButton = (RadioButton) findViewById(selectedId);
                                      s1 = e1.getText().toString();
                                      s2 = e2.getText().toString();
                                      s3 = e3.getText().toString();
                                      s5= e4.getText().toString();

                                      if (s1.equals("") || s2.equals("") || s3.equals("") || s5.equals("")) {

                                          Toast.makeText(Newplayer1.this, "Fill all details", Toast.LENGTH_SHORT).show();
                                      }
                                      else
                                      {
                                          SQLiteDatabase data = openOrCreateDatabase("Net", MODE_PRIVATE, null);
                                          data.execSQL("create table if not exists final(name varchar, handler varchar, email varchar, password varchar, win integer, loss integer)");
                                          String s4 = "select * from final where handler='" + s2 + "'";
                                          Cursor cursor = data.rawQuery(s4, null);
                                          if (cursor.getCount() > 0) {
                                              Toast.makeText(Newplayer1.this, "User exists", Toast.LENGTH_SHORT).show();
                                          } else
                                          {
                                              data.execSQL("insert into final values('" + s1 + "','" + s2 + "','" + s3 + "','" + s5 + "','0','0')");
                                              Toast.makeText(Newplayer1.this, "Pass phone to second player", Toast.LENGTH_SHORT).show();
                                              Intent i = new Intent(Newplayer1.this, Player2.class);
                                            i.putExtra("player1", s2);
                                              startActivity(i);
                                              finish();
                                            //  startActivityForResult(i, 1);

                                          }
                                      }
                                  }
                              }
        );

    }

}
