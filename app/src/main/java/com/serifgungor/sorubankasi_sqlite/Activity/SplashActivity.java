package com.serifgungor.sorubankasi_sqlite.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.serifgungor.sorubankasi_sqlite.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        this.getSupportActionBar().hide(); // Toolbar'ı gizler

        new Bekle().start();
    }

    private class Bekle extends Thread{
        @Override
        public void run() {
            super.run();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startActivity(new Intent(getApplicationContext(),DersKategorileriActivity.class));
            finish();
        }
    }
}
