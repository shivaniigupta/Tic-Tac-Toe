package com.example.sricomputers.tic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Welcome extends AppCompatActivity {



private RelativeLayout mainlayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.mainlayout);
        rlayout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                Intent i = new Intent(Welcome.this, Main.class);
                startActivity(i);
                finish();


            }
        });
    }

}
