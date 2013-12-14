package com.db.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by newUser on 10/19/13.
 */
public class Splash extends Activity {
    MediaPlayer ourSong;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ourSong = MediaPlayer.create(Splash.this, R.raw.splashsound);
        ourSong.start();
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //Intent openMainActivity = new Intent("com.db.myapplication.MAINACTIVITY");
                    //startActivity(openMainActivity);
                    Intent openMenu = new Intent("com.db.myapplication.MENU");
                    startActivity(openMenu);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }
}
