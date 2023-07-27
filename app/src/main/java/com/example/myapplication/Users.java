package com.example.myapplication;

public class Users {

    String userName;
    String userPhone;
    String userWeight;
    String userAge;
    String usergender;

    public Users(){

    }

    public Users( String userName, String userPhone, String userWeight, String userAge) {

        this.userName = userName;
        this.userPhone = userPhone;
        this.userWeight = userWeight;
        this.userAge = userAge;
        this.usergender = usergender;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public String getUserAge() {
        return userAge;
    }
    public String getGender() {return usergender;}
}
