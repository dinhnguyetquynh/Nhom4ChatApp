package com.example.nhom4_chatappandroid.model;

import com.google.firebase.Timestamp;

public class UserModel {
    private String hoTen;
    private String email;
    private String matKhau;
    private String gioiTinh;
    private Timestamp createTimestamp;
    private String userId;
    private String fcmToken;

    public UserModel() {
    }

    public UserModel(String hoTen, String email, String matKhau, String gioiTinh, Timestamp createTimestamp, String userId) {
        this.hoTen = hoTen;
        this.email = email;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.createTimestamp = createTimestamp;
        this.userId = userId;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
