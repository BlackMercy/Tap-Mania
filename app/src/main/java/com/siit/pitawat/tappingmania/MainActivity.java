package com.siit.pitawat.tappingmania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
// This is Main Menu

public class MainActivity extends ActionBarActivity {

    ApplicationConfig appConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {

        Intent i = new Intent(this, CountDown.class);
        EditText player1 = (EditText)findViewById(R.id.p1name);
        EditText player2 = (EditText)findViewById(R.id.p2name);
        NumberPicker time = (NumberPicker)findViewById(R.id.numberPicker);
        // also send player1 and player2 to next activity
        appConfig.getInstance().setPlayer1Name(player1.getText().toString());
        appConfig.getInstance().setPlayer2Name(player2.getText().toString());
        appConfig.getInstance().setTime(time.getValue());
        //int time1 = time.getValue();
//        i.putExtra("player1", player1.getText().toString());
//
//
//        i.putExtra("player2", player2.getText().toString());
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
