package com.itsamirrezah.goscore.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.itsamirrezah.goscore.R;


public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                Intent i = new Intent(Splash.this, MainActivity.class);

                startActivity(i);

                // close this activity

                finish();

            }

        }, 3*1000);
        setContentView(R.layout.activity_splash);
    }

}
