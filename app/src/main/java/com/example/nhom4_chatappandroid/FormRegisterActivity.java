package com.example.nhom4_chatappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nhom4_chatappandroid.model.UserModel;
import com.example.nhom4_chatappandroid.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;

import java.text.Normalizer;

public class FormRegisterActivity extends AppCompatActivity {

    EditText edtHoTen;
    EditText edtEmail;
    EditText edtMatKhau;
    RadioButton rdoNu;
    RadioButton rdoNam;
    Button btnDangKyTK;
    UserModel userModel;
    private String gioiTinh;
    private String hoTen;
    private String email;
    private String matkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);

        edtHoTen = findViewById(R.id.edtHoTen);
        edtEmail = findViewById(R.id.edtEmail);
        edtMatKhau=findViewById(R.id.edtMatKhau);
        rdoNu = findViewById(R.id.rdoNu);
        rdoNam = findViewById(R.id.rdoNam);
        btnDangKyTK = findViewById(R.id.btnDangKyTK);

        // dùng firebaseAuth xác thực email và thêm người dùng vào firebase
        btnDangKyTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 hoTen = edtHoTen.getText().toString();
                 email = edtEmail.getText().toString();
                 matkhau = edtMatKhau.getText().toString();

                if(rdoNu.isChecked()){
                   gioiTinh="Nu";
                }else if(rdoNam.isChecked()){
                    gioiTinh="Nam";
                }

                final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(email,matkhau).addOnCompleteListener((task)->{
                   if(task.isSuccessful()){
                       firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   Toast.makeText(FormRegisterActivity.this,"Đã đăng kí thành công. Vui lòng check mail",Toast.LENGTH_SHORT).show();
                                   //Them nguoi dung vao firebase store
                                    userModel = new UserModel(hoTen,email,matkhau,gioiTinh,Timestamp.now(),FirebaseUtil.currentUserId());
                                    FirebaseUtil.currentUserDetail().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            startActivity(new Intent(FormRegisterActivity.this,MainViewNewUser.class));
                                            finish();

                                        }
                                    });

                               }else {
                                   Toast.makeText(FormRegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                               }
                           }
                       });
                   }else{
                       Toast.makeText(FormRegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                   }
                });
            }
        });


    }
}