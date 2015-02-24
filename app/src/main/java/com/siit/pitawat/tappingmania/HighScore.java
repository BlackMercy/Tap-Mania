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

import java.sql.SQLException;

/**
 * Created by Jatupat on 23-Feb-15.
 */
public class HighScore extends ActionBarActivity {
    HStoDB helper;
    SimpleCursorAdapter adapter;
    ActionMode actionMode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);

        helper = new HStoDB(this.getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HighScore ORDER BY time ASC,score DESC;",null);

        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[] {"name","score","time"},
                new int[] {android.R.id.text1,android.R.id.text2},
                0);
        ListView lv = (ListView)findViewById(R.id.listView2);
        lv.setAdapter(adapter);

    }

    protected void onResume(){
        super.onResume();
        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM HighScore ORDER BY time ASC,score DESC;",null);
//        cursor.moveToFirst();
        ListView lv = (ListView)findViewById(R.id.listView2);
       // lv.setAdapter(null);
        db.close();
    }

    public void buttonClicked (View v){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE name, score, time FROM" + HighScore);
        db.close();
//        int id = v.getId();
//        Intent i;
//
//        switch(id)
//        {
//            case R.id.buttonReset:
//                SQLiteDatabase db = helper.getWritableDatabase();
//                db.delete("HighScore","",null);
//                this.onResume();
//                break;
//        }
    }
}
