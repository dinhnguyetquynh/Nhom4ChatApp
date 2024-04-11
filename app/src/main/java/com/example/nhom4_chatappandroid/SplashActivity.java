package com.example.nhom4_chatappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    //startActivity(new Intent(SplashActivity.this,LoginPhoneNumberActivity.class));
                    startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                    finish();

            }
        },3000);
    }
}