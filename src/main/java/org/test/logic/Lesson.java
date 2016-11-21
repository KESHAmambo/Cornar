package org.test.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abara on 18.11.2016.
 */
public class Lesson {
    private int id;
    private String lessonName;
    private Course course;
    private double cost;
    private Date date;
    private List<Profile> assignedStudents;

    public Lesson(int id, String lessonName, Course course, double cost, Date date, List<Profile> assignedStudents) {
        this.id = id;
        this.lessonName = lessonName;
        this.course = course;
        this.cost = cost;
        this.date = date;
        this.assignedStudents = assignedStudents;
    }

    public Lesson(String lessonName, Course course, double cost, Date date) {
        this.lessonName = lessonName;
        this.course = course;
        this.cost = cost;
        this.date = date;
        assignedStudents = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public List<Profile> getAssignedStudents() {
        return assignedStudents;
    }

    public void setAssignedStudents(List<Profile> assignedStudents) {
        this.assignedStudents = assignedStudents;
    }
}
