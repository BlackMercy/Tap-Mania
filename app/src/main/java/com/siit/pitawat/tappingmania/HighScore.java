package com.siit.pitawat.tappingmania;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by student on 2/18/15 AD.
 */
public class HighScore extends ActionBarActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
    }

    public class HStoDB extends SQLiteOpenHelper {
        public static final String name = "todo.sqlite3";
        public static final int version = 1;

        public HStoDB(Context context) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE todo (" +
                    "_id integer primary key autoincrement, " +
                    "title text not null," +
                    "priority integer default 0);";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "DROP TABLE IF EXISTS todo;";
            db.execSQL(sql);
            this.onCreate(db);
        }
    }
}
