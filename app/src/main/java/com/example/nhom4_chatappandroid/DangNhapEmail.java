package com.example.nhom4_chatappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DangNhapEmail extends AppCompatActivity {
    EditText dangnhap_email;
    EditText dangnhap_matkhau;
    Button dangnhap_btn;
    String email,matkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_email);
        dangnhap_email = findViewById(R.id.dangnhap_email);
        dangnhap_matkhau=findViewById(R.id.dangnhap_matkhau);
        dangnhap_btn=findViewById(R.id.dangnhap_btnDangNhap);

        dangnhap_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                 email = dangnhap_email.getText().toString();
                matkhau =dangnhap_matkhau.getText().toString();

                if(email.length()==0||matkhau.length()==0){
                    Toast.makeText(DangNhapEmail.this,"Vui lòng nhập đầy đủ",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,matkhau).addOnCompleteListener((task)->{
                    if(task.isSuccessful()){
                        if(firebaseAuth.getCurrentUser().isEmailVerified()){
                            startActivity(new Intent(DangNhapEmail.this,MainViewNewUser.class));
                            finish();
                        }else{
                            Toast.makeText(DangNhapEmail.this,"Vui lòng check email để xác thưc",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(DangNhapEmail.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}