package com.siit.pitawat.tappingmania;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Pitawat on 14/2/2015.
 */
public class Game extends ActionBarActivity {
    ApplicationConfig appConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Log.d("Player1Name", appConfig.getInstance().getPlayer1Name());
        Log.d("Player2Name", appConfig.getInstance().getPlayer2Name());
        Log.d("Time", String.valueOf(appConfig.getInstance().getTime()));
    }

    public void tap (View v){
//        TextView scoreP1 = (TextView) findViewById(R.id.scoreP1);
//        int sP1 = Integer.parseInt(scoreP1.toString());
//
//        int id = v.getId();
//        switch (id) {
//            case R.id.buttonP1:
//                sP1++;
//                scoreP1.setText(sP1);
//        }
        TextView scoreP1 = (TextView)findViewById(R.id.scoreP1);
        TextView scoreP2 = (TextView)findViewById(R.id.scoreP2);
        int sP1 = Integer.parseInt(scoreP1.getText().toString());
        int sP2 = Integer.parseInt(scoreP2.getText().toString());

        int id = v.getId();
        switch (id) {
            case R.id.buttonP1:
                sP1++;
                scoreP1.setText(Integer.toString(sP1));
                break;

            case R.id.buttonP2:
                sP2++;
                scoreP2.setText(Integer.toString(sP2));
                break;
        }
    }
}
