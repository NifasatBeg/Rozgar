package com.example.rozgarproject.Models;

public class AppliedStatusObj {
    String jobID,jobStatus,Announcement,location,jobTitle;

    public AppliedStatusObj(String jobID, String jobStatus, String announcement, String location, String jobTitle) {
        this.jobID = jobID;
        this.jobStatus = jobStatus;
        Announcement = announcement;
        this.location = location;
        this.jobTitle = jobTitle;
    }

    public String getJobID() {
        return jobID;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public String getAnnouncement() {
        return Announcement;
    }

    public String getLocation() {
        return location;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public void setAnnouncement(String announcement) {
        Announcement = announcement;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
