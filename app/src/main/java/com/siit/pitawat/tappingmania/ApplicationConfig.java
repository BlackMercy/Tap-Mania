package com.siit.pitawat.tappingmania;

/**
 * Created by student on 2/18/15 AD.
 */
public class ApplicationConfig {

    private static ApplicationConfig appConfig;
    private String player1Name;
    private String player2Name;
    private int time;


    private ApplicationConfig() {

    }

    public static ApplicationConfig getInstance() {
        if (appConfig == null) {
            appConfig = new ApplicationConfig();
        }
        return appConfig;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


}
