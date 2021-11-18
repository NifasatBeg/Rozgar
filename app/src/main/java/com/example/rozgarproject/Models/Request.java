package com.example.rozgarproject.Models;

public class Request {
    String name, age, contactNumber,userID,jobID;
//    boolean accept, reject;

    public Request(String name, String age, String contactNumber, String userID,String jobID) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.userID = userID;
        this.jobID = jobID;
    }

    public String getName() {
        return name;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getJobID() {
        return jobID;
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
