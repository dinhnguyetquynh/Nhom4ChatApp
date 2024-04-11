package com.example.nhom4_chatappandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.nhom4_chatappandroid.model.UserModel;

public class AndroidUtil {
    public static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void passUserModelAsIntent(Intent intent, UserModel model){
        intent.putExtra("hoTen",model.getHoTen());
        intent.putExtra("email",model.getEmail());
        intent.putExtra("userId",model.getUserId());
    }
    public static UserModel getUserModelFromIntent(Intent intent){
        UserModel userModel = new UserModel();
        userModel.setHoTen(intent.getStringExtra("hoTen"));
        userModel.setEmail(intent.getStringExtra("email"));
        userModel.setUserId(intent.getStringExtra("userId"));
        userModel.setFcmToken(intent.getStringExtra("fcmToken"));
        return userModel;
    }
}
