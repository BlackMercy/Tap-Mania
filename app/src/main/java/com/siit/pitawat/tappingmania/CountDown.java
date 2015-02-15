package com.siit.pitawat.tappingmania;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Pitawat on 14/2/2015.
 */
public class CountDown extends ActionBarActivity {

    TextView num = (TextView)findViewById(R.id.countdown);

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown);
//        num = new TextView(this);
//        this.setContentView(num);
        int n = Integer.parseInt(num.toString());
        timer counter = new timer (n*1000,1000);
        counter.start();
    }

    public class timer extends CountDownTimer {

        public timer(long millisInFuture, long countDownInterval){
            super(millisInFuture,countDownInterval);
        }

        @Override
        public void onFinish(){
            num.setText("START!");
//            Intent j = new Intent (this,Game.class);
//            startActivity(j);
            // advance to Game class
        }

        @Override
        public void onTick(long millisUntilFinished){
            num.setText(""+millisUntilFinished/1000);
        }
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
