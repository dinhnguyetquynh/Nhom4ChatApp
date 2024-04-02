package com.example.nhom4_chatappandroid;

import java.time.LocalDateTime;

public class User {
    private int image;
    private String nameUser;
    private String message;
    private LocalDateTime time;

    public User(int image, String nameUser, String message, LocalDateTime time) {
        this.image = image;
        this.nameUser = nameUser;
        this.message = message;
        this.time = time;
    }

    public User() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    


}
