package com.siit.pitawat.tappingmania;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ActionMode;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 * Created by Jatupat on 23-Feb-15.
 */
public class HighScore extends ActionBarActivity {
    HStoDB helper;
    SimpleCursorAdapter adapter,adapter2,adapter3,adapter4;
    ActionMode actionMode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);

        helper = new HStoDB(this.getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HighScore WHERE time = 15 ORDER BY score DESC LIMIT 0,3;",null);
        Cursor cursor2 = db.rawQuery("SELECT * FROM HighScore WHERE time = 30 ORDER BY score DESC LIMIT 0,3;",null);
        Cursor cursor3 = db.rawQuery("SELECT * FROM HighScore WHERE time = 45 ORDER BY score DESC LIMIT 0,3;",null);
        Cursor cursor4 = db.rawQuery("SELECT * FROM HighScore WHERE time = 60 ORDER BY score DESC LIMIT 0,3;",null);

        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[] {"name","score"},
                new int[] {android.R.id.text1,android.R.id.text2},
                0);
        adapter2 = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor2,
                new String[] {"name","score"},
                new int[] {android.R.id.text1,android.R.id.text2},
                0);
        adapter3 = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor3,
                new String[] {"name","score"},
                new int[] {android.R.id.text1,android.R.id.text2},
                0);
        adapter4 = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor4,
                new String[] {"name","score"},
                new int[] {android.R.id.text1,android.R.id.text2},
                0);

        ListView lv2 = (ListView)findViewById(R.id.listView2);
        lv2.setAdapter(adapter);
        ListView lv3 = (ListView)findViewById(R.id.listView3);
        lv3.setAdapter(adapter2);
        ListView lv4 = (ListView)findViewById(R.id.listView4);
        lv4.setAdapter(adapter3);
        ListView lv5 = (ListView)findViewById(R.id.listView5);
        lv5.setAdapter(adapter4);

    }

   protected void onResume()throws SQLException{
       super.onResume();
       SQLiteDatabase db1 = helper.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM HighScore ORDER BY score DESC;",null);
//        cursor.moveToFirst();

       db1.execSQL("DROP TABLE" +"HighScore");
       db1.close();
       this.helper.onCreate(this.db);



       //ListView lv2 = (ListView)findViewById(R.id.listView2);
//       ListView lv3 = (ListView)findViewById(R.id.listView3);
//       ListView lv4 = (ListView)findViewById(R.id.listView4);
//       ListView lv5 = (ListView)findViewById(R.id.listView5);
//       lv2.setAdapter(null);
//       lv3.setAdapter(null);
//       lv4.setAdapter(null);
//       lv5.setAdapter(null);


   }

    public void buttonClicked (View v){

       int id = v.getId();

        switch(id)
        {
            case R.id.buttonReset:
                SQLiteDatabase db = helper.getWritableDatabase();
                db.delete("HighScore",null,null);
                this.onResume();
                break;
        }
    }
}
