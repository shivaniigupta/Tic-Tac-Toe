package com.example.sricomputers.tic;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Scoreboard extends AppCompatActivity {
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> win=new ArrayList<>();
    ArrayList<String> loss=new ArrayList<>();
    int j=0,i;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        Button b=(Button)findViewById(R.id.button16);

       /* String from[]={"name","win","loss"};
        int to[]={R.id.,R.id.,R.id.iv1};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.activty_scoreboard, from, to);
        assign.setAdapter(simpleAdapter);
        assign.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int pp=position+1;
                getContentResolver().delete(CProviderA.CONTENT_URI,"_id="+pp,null);
                finish();
                return true;

*/

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



       SQLiteDatabase data = openOrCreateDatabase("Net", MODE_PRIVATE, null);
        data.execSQL("select * from final order by win desc");
        String s1 = "select name,win,loss from final";
        Cursor cu = data.rawQuery(s1, null);
        if (cu!=null) {
            if (cu.getCount() != 0) {
                if (cu.moveToFirst()) {
                    do {
                        name.add(0,cu.getString(cu.getColumnIndex("name")));
                        win.add(0,cu.getString(cu.getColumnIndex("win")));
                        loss.add(0,cu.getString(cu.getColumnIndex("loss")));
                        j++;
                    } while (cu.moveToNext());
                }
            }
        }
        ListView assign = (ListView) findViewById(R.id.assign);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for(i=0;i<j;i++)
        {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("NAME", name.get(i));
            hashMap.put("Matches Won", win.get(i));
            hashMap.put("Matches Lost",loss.get(i));
            arrayList.add(hashMap);
        }
            }
        });}


}
