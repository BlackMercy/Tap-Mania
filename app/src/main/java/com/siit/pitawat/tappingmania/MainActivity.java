package com.siit.pitawat.tappingmania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
// This is Main Menu

public class MainActivity extends ActionBarActivity {

    ApplicationConfig appConfig;
    NumberPicker time;
    String[] timeLim;
    int j, k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = (NumberPicker) findViewById(R.id.numberPicker);
        k = 0;
        timeLim = new String[4];
        for (j = 0; j < timeLim.length; j++) {
            timeLim[j] = Integer.toString(k + 15);
            k = Integer.parseInt(timeLim[j]);
        }

        time.setMaxValue(timeLim.length - 1);
        time.setMinValue(0);
        time.setDisplayedValues(timeLim);
    }

    public void onClick(View v) {

        Intent i = new Intent(this, CountDown.class);
        EditText player1 = (EditText) findViewById(R.id.p1name);
        EditText player2 = (EditText) findViewById(R.id.p2name);

        // also send player1 and player2 to next activity

        appConfig.getInstance().setPlayer1Name(player1.getText().toString());
        appConfig.getInstance().setPlayer2Name(player2.getText().toString());
        appConfig.getInstance().setTime(Integer.parseInt(timeLim[time.getValue()]));

        Log.d("Test", time.getValue() + "");

        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.high_score) {
            // Add class here
            Intent hs = new Intent(this, HighScore.class);
            startActivity(hs);
        }

        return super.onOptionsItemSelected(item);
    }
}
