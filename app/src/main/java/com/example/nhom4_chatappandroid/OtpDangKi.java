package com.example.nhom4_chatappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OtpDangKi extends AppCompatActivity {

    EditText edtOTPDangKi;
    Button btnDangKi;
    String email;
    String hoTen;
    String gioiTinh;
    String matKhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_dang_ki);

        edtOTPDangKi = findViewById(R.id.edtOTPDangki);
        btnDangKi = findViewById(R.id.btnDangki);


        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterOTP = edtOTPDangKi.getText().toString();
                

            }
        });










    }
}