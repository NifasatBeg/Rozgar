package com.example.rozgarproject.Models;

public class Request {
    String name, age, contactNumber,userID;
//    boolean accept, reject;

    public Request(String name, String age, String contactNumber, String userID) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
