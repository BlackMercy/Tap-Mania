package com.siit.pitawat.tappingmania;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ActionMode;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jatupat on 23-Feb-15.
 */

public class HighScore extends ActionBarActivity {
    SimpleAdapter adapter2, adapter3, adapter4,adapter5;
    ActionMode actionMode;
    ArrayList<Map<String, String>> data2,data3,data4,data5;

    JSONParser jp = new JSONParser();


    //List<NameValuePair> parameters = new ArrayList<NameValuePair>();

    class LoadScore extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            BufferedReader reader;
            StringBuilder buffer = new StringBuilder();
            String line;

            try {

                URL u = new URL("http://ict.siit.tu.ac.th/~u5522791169/ITS333/testfetch.php");
                HttpURLConnection h = (HttpURLConnection) u.openConnection();
                h.setRequestMethod("GET");
                h.setDoInput(true);
                h.connect();

                int response = h.getResponseCode();
                if (response == 200) {
                    reader = new BufferedReader(new InputStreamReader(h.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    Log.e("LoadMessageTask", buffer.toString());

                    JSONObject jo = new JSONObject(buffer.toString());
                    JSONArray msg = jo.getJSONArray("msg");
                    for (int i = 0; i < msg.length(); i++) {

                        String username = msg.getJSONObject(i).getString("uname");
                        String score = msg.getJSONObject(i).getString("score");
                        String scoretype = msg.getJSONObject(i).getString("time");
                        String score_combine = "Score: " + score + "  ScoreType: " + scoretype;
                        Map<String, String> item = new HashMap<String, String>();
                        item.put("uname", username);
                        item.put("score", score_combine);

                        switch (Integer.parseInt(scoretype)){
                            case 15:
                                data2.add(0, item);
                                break;
                            case 30:
                                data3.add(0, item);
                                break;
                            case 45:
                                data4.add(0,item);
                                break;
                            case 60:
                                data5.add(0,item);
                                break;
                        }
                    }
                    return true;
                }
            } catch (MalformedURLException e) {
                Log.e("LoadMessageTask", "Invalid URL");
            } catch (IOException e) {
                Log.e("LoadMessageTask", "I/O Exception");
            } catch (JSONException e) {
                Log.e("LoadMessageTask", "Invalid JSON");
            }
            return false;
        }
        /*
        @Override
        protected Boolean doInBackground(String... params) {
            BufferedReader reader;
            StringBuilder buffer = new StringBuilder();
            String line;

            try {
                JSONObject jo = jp.getJSONFromUrl("http://ict.siit.tu.ac.th/~u5522791169/ITS333/testfetch.php");
                JSONArray msg = jo.getJSONArray("msg");
                for (int i = 0; i < msg.length(); i++) {

                    String username = msg.getJSONObject(i).getString("uname");
                    String score = msg.getJSONObject(i).getString("score");
                    String scoretype = msg.getJSONObject(i).getString("time");
                    String score_combine = "Score: " + score + "  ScoreType: " + scoretype;
                    Map<String, String> item = new HashMap<String, String>();
                    item.put("uname", username);
                    item.put("score", score_combine);

                    if(scoretype=="15"){
                        data2.add(0,item);
                    } else if(scoretype=="30"){
                        data3.add(0,item);
                    } else if(scoretype=="45"){
                        data4.add(0,item);
                    } else {
                        data5.add(0,item);
                    }
                }
                return true;
            } catch (JSONException e) {
                Log.e("LoadMessageTask", "Invalid JSON");
            }
            return false;
        }
        */

    }

//    class LoadScore_cancel extends AsyncTask<String, Void, Boolean>{
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//            BufferedReader reader;
//            StringBuilder buffer = new StringBuilder();
//            String line;
//
//            try {
//                Log.e("LoadScore", "");
//                URL u = new URL("http://ict.siit.tu.ac.th/~u5522791169/ITS333/testfetch.php");
//                HttpURLConnection h = (HttpURLConnection) u.openConnection();
//                h.setRequestMethod("GET");
//                h.setDoInput(true);
//                h.connect();
//
//                int response = h.getResponseCode();
//                if (response == 200) {
//                    reader = new BufferedReader(new InputStreamReader(h.getInputStream()));
//                    while ((line = reader.readLine()) != null) {
//                        buffer.append(line);
//                    }
//
//                    Log.e("LoadScore", buffer.toString());
//                    JSONObject json = new JSONObject(buffer.toString());
//                    //JSONObject timestamp_json = json.getJSONObject("timestamp");
//                    //timestamp = timestamp_json.getInt("")
//                    //timestamp = json.getInt("timestamp");
//                    //Log.d("xxx", timestamp + "----lll");
//                    for (int i = 0; i < json.getJSONArray("msg").length(); i++) {
//                        String user = json.getJSONArray("msg").getJSONObject(i).getString("uname");
//                        String score = json.getJSONArray("msg").getJSONObject(i).getString("score");
//
//                        Map<String, String> item = new HashMap<String, String>();
//                        item.put("user", user);
//                        item.put("score", score);
//                        data.add(0, item);
//                    }
//                    return true;
//                }
//            } catch (MalformedURLException e) {
//                Log.e("LoadMessageTask", "Invalid URL");
//            } catch (IOException e) {
//                Log.e("LoadMessageTask", "I/O Exception");
//            } catch (JSONException e) {
//                Log.e("LoadMessageTask", "Invalid JSON");
//            }
//            return false;
//        }
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);

        data2 = new ArrayList<Map<String, String>>();
        data3 = new ArrayList<Map<String, String>>();
        data4 = new ArrayList<Map<String, String>>();
        data5 = new ArrayList<Map<String, String>>();
        adapter2 = new SimpleAdapter(this,
                data2,
                android.R.layout.simple_list_item_2,
                new String[]{"uname", "score"},
                new int[]{android.R.id.text1, android.R.id.text2});
        adapter3 = new SimpleAdapter(this,
                data3,
                android.R.layout.simple_list_item_2,
                new String[]{"uname", "score"},
                new int[]{android.R.id.text1, android.R.id.text2});
        adapter4 = new SimpleAdapter(this,
                data4,
                android.R.layout.simple_list_item_2,
                new String[]{"uname", "score"},
                new int[]{android.R.id.text1, android.R.id.text2});
        adapter5 = new SimpleAdapter(this,
                data5,
                android.R.layout.simple_list_item_2,
                new String[]{"uname", "score"},
                new int[]{android.R.id.text1, android.R.id.text2});
        ListView l2 = (ListView) findViewById(R.id.listView2);
        l2.setAdapter(adapter2);
        ListView l3 = (ListView) findViewById(R.id.listView3);
        l3.setAdapter(adapter3);
        ListView l4 = (ListView) findViewById(R.id.listView4);
        l4.setAdapter(adapter4);
        ListView l5 = (ListView) findViewById(R.id.listView5);
        l5.setAdapter(adapter5);
        LoadScore task = new LoadScore();
        task.execute();
        Intent i = this.getIntent();
        //user = i.getStringExtra("user");



//        helper = new HStoDB(this.getApplicationContext());
//        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT _id, ('Name : ' || name) n,( 'Score : ' || score) s FROM HighScore WHERE time = 15 ORDER BY score DESC LIMIT 0,3;", null);
//        Cursor cursor2 = db.rawQuery("SELECT _id, ('Name : ' || name) n,( 'Score : ' || score) s FROM HighScore WHERE time = 30 ORDER BY score DESC LIMIT 0,3;", null);
//        Cursor cursor3 = db.rawQuery("SELECT _id, ('Name : ' || name) n,( 'Score : ' || score) s FROM HighScore WHERE time = 45 ORDER BY score DESC LIMIT 0,3;", null);
//        Cursor cursor4 = db.rawQuery("SELECT _id, ('Name : ' || name) n,( 'Score : ' || score) s FROM HighScore WHERE time = 60 ORDER BY score DESC LIMIT 0,3;", null);
//
//        adapter = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                cursor,
//                new String[]{"n", "s"},
//                new int[]{android.R.id.text1, android.R.id.text2},
//                0);
//        adapter2 = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                cursor2,
//                new String[]{"n", "s"},
//                new int[]{android.R.id.text1, android.R.id.text2},
//                0);
//        adapter3 = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                cursor3,
//                new String[]{"n", "s"},
//                new int[]{android.R.id.text1, android.R.id.text2},
//                0);
//        adapter4 = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                cursor4,
//                new String[]{"n", "s"},
//                new int[]{android.R.id.text1, android.R.id.text2},
//                0);
//
//        ListView lv2 = (ListView) findViewById(R.id.listView2);
//        lv2.setAdapter(adapter);
//        ListView lv3 = (ListView) findViewById(R.id.listView3);
//        lv3.setAdapter(adapter2);
//        ListView lv4 = (ListView) findViewById(R.id.listView4);
//        lv4.setAdapter(adapter3);
//        ListView lv5 = (ListView) findViewById(R.id.listView5);
//        lv5.setAdapter(adapter4);
//        LoadScore task = new LoadScore();
//        task.execute();
   }

    protected void onResume(){
        super.onResume();
        setContentView(R.layout.highscore);

//        helper = new HStoDB(this.getApplicationContext());
//        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT _id, ('Name : ' || name) n,( 'Score : ' || score) s FROM HighScore WHERE time = 15 ORDER BY score DESC LIMIT 0,3;", null);
//        Cursor cursor2 = db.rawQuery("SELECT _id, ('Name : ' || name) n,( 'Score : ' || score) s FROM HighScore WHERE time = 30 ORDER BY score DESC LIMIT 0,3;", null);
//        Cursor cursor3 = db.rawQuery("SELECT _id, ('Name : ' || name) n,( 'Score : ' || score) s FROM HighScore WHERE time = 45 ORDER BY score DESC LIMIT 0,3;", null);
//        Cursor cursor4 = db.rawQuery("SELECT _id, ('Name : ' || name) n,( 'Score : ' || score) s FROM HighScore WHERE time = 60 ORDER BY score DESC LIMIT 0,3;", null);
//
//        adapter = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                cursor,
//                new String[]{"n", "s"},
//                new int[]{android.R.id.text1, android.R.id.text2},
//                0);
//        adapter2 = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                cursor2,
//                new String[]{"n", "s"},
//                new int[]{android.R.id.text1, android.R.id.text2},
//                0);
//        adapter3 = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                cursor3,
//                new String[]{"n", "s"},
//                new int[]{android.R.id.text1, android.R.id.text2},
//                0);
//        adapter4 = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                cursor4,
//                new String[]{"n", "s"},
//                new int[]{android.R.id.text1, android.R.id.text2},
//                0);
//
//        ListView lv2 = (ListView) findViewById(R.id.listView2);
//        lv2.setAdapter(adapter);
//        ListView lv3 = (ListView) findViewById(R.id.listView3);
//        lv3.setAdapter(adapter2);
//        ListView lv4 = (ListView) findViewById(R.id.listView4);
//        lv4.setAdapter(adapter3);
//        ListView lv5 = (ListView) findViewById(R.id.listView5);
//        lv5.setAdapter(adapter4);
  }

//    public void buttonClicked(View v) {
//
//        Log.d("button is clicked", "buttonclicked");
//        int id = v.getId();
//        switch (id) {
//            case R.id.buttonReset:
//                SQLiteDatabase db = helper.getWritableDatabase();
//                db.delete("HighScore", "", null);
//                this.onResume();
//
//                Log.d("button is clicked", "buttonReset");
//
//                break;
//        }
//    }
}
