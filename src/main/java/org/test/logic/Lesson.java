package org.test.logic;

import java.util.Date;
import java.util.List;

/**
 * Created by abara on 18.11.2016.
 */
public class Lesson {
    private Profile tutorProfile;
    private List<Profile> assignedStudents;
    private double cost;
    private Date date;
    private String subject;

    public Lesson(Profile tutorProfile, List<Profile> assignedStudents, double cost, Date date, String subject) {
        this.tutorProfile = tutorProfile;
        this.assignedStudents = assignedStudents;
        this.cost = cost;
        this.date = date;
        this.subject = subject;
    }

    public Profile getTutorProfile() {
        return tutorProfile;
    }

    public void setTutorProfile(Profile tutorProfile) {
        this.tutorProfile = tutorProfile;
    }

    public List<Profile> getAssignedStudents() {
        return assignedStudents;
    }

    public void setAssignedStudents(List<Profile> assignedStudents) {
        this.assignedStudents = assignedStudents;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
