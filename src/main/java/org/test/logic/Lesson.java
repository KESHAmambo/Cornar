package org.test.logic;

import java.util.*;

/**
 * Created by abara on 18.11.2016.
 */
public class Lesson {
    private int id;
    private String name;
    private Course course;
    private double cost;
    private Date startDate;
    private Date endDate;
    private Set<Profile> assignedStudents;

    public Lesson(
            int id, String name, Course course,
            double cost, Date startDate,
            Date endDate, Set<Profile> assignedStudents) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignedStudents = assignedStudents;
    }

    public Lesson(
            String name, Course course, double cost,
            Date startDate, Date endDate) {
        this.name = name;
        this.course = course;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
        assignedStudents = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Profile> getAssignedStudents() {
        return assignedStudents;
    }

    public void setAssignedStudents(Set<Profile> assignedStudents) {
        this.assignedStudents = assignedStudents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        return id == lesson.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
