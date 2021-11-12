package com.example.rozgarproject;

public class JobPost {
    String JobTitle,JobDate,NumberofWorkers,JobID;
    public JobPost(){

    }
    public JobPost(String JobTitle,String JobDate,String NumberofWorkers, String JobID){
        this.JobTitle = JobTitle;
        this.JobDate = JobDate;
        this.NumberofWorkers = NumberofWorkers;
        this.JobID = JobID;
    }
    public String getJobTitle(){
        return JobTitle;
    }
    public String getJobDate(){
        return JobDate;
    }
    public String getNumberofWorkers(){
        return NumberofWorkers;
    }

    public String getJobID() {
        return JobID;
    }
}
