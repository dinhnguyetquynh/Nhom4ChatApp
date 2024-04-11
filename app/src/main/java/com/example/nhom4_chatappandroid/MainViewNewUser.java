package com.example.nhom4_chatappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainViewNewUser extends AppCompatActivity {
    TextView mainview_timKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_new_user);
        mainview_timKiem = findViewById(R.id.mainview_timKiem);
        mainview_timKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainViewNewUser.this,SearchUser.class));
                finish();
            }
        });
    }
}