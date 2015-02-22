package com.siit.pitawat.tappingmania;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Pitawat on 14/2/2015.
 */
public class CountDown extends ActionBarActivity {

    ApplicationConfig appConfig;
    TextView num;
    //String player1, player2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown);
        num = (TextView) findViewById(R.id.countdown);
        Intent i = this.getIntent();
//        player1 = i.getStringExtra("player1");
//        player2 = i.getStringExtra("player2");
        int n = Integer.parseInt(num.getText().toString());
        Log.d("n", n + "");
        Log.d("TimeCountdown", String.valueOf(appConfig.getInstance().getTime()));
        timer counter = new timer(n * 1000, 1000);
        counter.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }



    public class timer extends CountDownTimer {

        public timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            num.setText("START!");
            Intent j = new Intent(CountDown.this, Game.class);
//            j.putExtra("player1", player1);
//            j.putExtra("player2", player2);
            startActivityForResult(j, 88);
            // advance to Game class
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.d("tick", "" + millisUntilFinished);
            num.setText("" + millisUntilFinished / 1000);
        }
    }


}
