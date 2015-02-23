package com.siit.pitawat.tappingmania;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Pitawat on 14/2/2015.
 */
public class Game extends ActionBarActivity {
    ApplicationConfig appConfig;
    TextView time_p1, time_p2, result_p1, result_p2;
    int sP1;
    int sP2;
    HStoDB HSDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        HSDB = new HStoDB(this);


        Log.d("Player1Name", appConfig.getInstance().getPlayer1Name());
        Log.d("Player2Name", appConfig.getInstance().getPlayer2Name());
        Log.d("Time", String.valueOf(appConfig.getInstance().getTime()));

        int mins = appConfig.getInstance().getTime();

        time_p1 = (TextView) findViewById(R.id.time_p1);
        time_p2 = (TextView) findViewById(R.id.time_p2);
        result_p1 = (TextView) findViewById(R.id.textViewP1);
        result_p2 = (TextView) findViewById(R.id.textViewP2);


        timer counter = new timer(mins * 1000, 1000);
        counter.start();


    }

    public void tap(View v) {
//        TextView scoreP1 = (TextView) findViewById(R.id.scoreP1);
//        int sP1 = Integer.parseInt(scoreP1.toString());
//
//        int id = v.getId();
//        switch (id) {
//            case R.id.buttonP1:
//                sP1++;
//                scoreP1.setText(sP1);
//        }
        TextView scoreP1 = (TextView) findViewById(R.id.scoreP1);
        TextView scoreP2 = (TextView) findViewById(R.id.scoreP2);
        sP1 = Integer.parseInt(scoreP1.getText().toString());
        sP2 = Integer.parseInt(scoreP2.getText().toString());

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

    public class timer extends CountDownTimer {

        public timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {

            time_p1.setText("END GAME!");
            time_p2.setText("END GAME!");

            Button p1 = (Button) findViewById(R.id.buttonP1);
            Button p2 = (Button) findViewById(R.id.buttonP2);
            p1.setEnabled(false);
            p2.setEnabled(false);
            String winnername = "";
            int winnerscore = 65534;
            if (sP1 > sP2) {
                result_p1.setText("YOU WIN!!");
                result_p2.setText("YOU LOSE!!");
                winnername = appConfig.getInstance().getPlayer1Name();
                winnerscore = sP1;
            } else if (sP2 > sP1) {
                result_p1.setText("YOU LOSE!!");
                result_p2.setText("YOU WIN!!");
                winnername = appConfig.getInstance().getPlayer2Name();
                winnerscore = sP2;
            } else {
                result_p1.setText("DRAW!!");
                result_p2.setText("DRAW!!");
            }

            SQLiteDatabase db = HSDB.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", winnername);
            cv.put("score", winnerscore);

            //Intent j = new Intent(CountDown.this, Game.class);
//            j.putExtra("player1", player1);
//            j.putExtra("player2", player2);
            //startActivityForResult(j, 88);
            // advance to Game class
//            this.cancel();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.d("tick", "" + millisUntilFinished);
            time_p1.setText("" + millisUntilFinished / 1000);
            time_p2.setText("" + millisUntilFinished / 1000);
        }
    }


}
