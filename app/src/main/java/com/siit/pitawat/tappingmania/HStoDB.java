package com.siit.pitawat.tappingmania;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 2/23/15 AD.
 */
public class HStoDB extends SQLiteOpenHelper {
    public static final String name = "HighScore.sqlite3";
    public static final int version = 1;

    public HStoDB(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql = "CREATE TABLE HighScore (" +
                "_id integer primary key autoincrement, " +
                "name text not null," +
                "score int not null," +
                "time int not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS HighScore;";
        db.execSQL(sql);
        this.onCreate(db);
    }
}