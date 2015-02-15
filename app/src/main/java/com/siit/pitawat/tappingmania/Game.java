package com.siit.pitawat.tappingmania;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Pitawat on 14/2/2015.
 */
public class Game extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void tap (View v){
        TextView scoreP1 = (TextView) findViewById(R.id.scoreP1);
        int sP1 = Integer.parseInt(scoreP1.toString());

        int id = v.getId();
        switch (id) {
            case R.id.buttonP1:
                sP1++;
                scoreP1.setText(sP1);
        }
    }
}
