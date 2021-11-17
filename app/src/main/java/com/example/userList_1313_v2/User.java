package com.example.userList_1313_v2;

import java.util.UUID;

public class User {
    private String userName;
    private String userLastName;
    private String phone;
    private UUID uuid;

    public User(UUID uuid){
        this.uuid = uuid;
    }

    public User() {
        this.uuid = UUID.randomUUID();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UUID getUuid() {
        return uuid;
    }

}

