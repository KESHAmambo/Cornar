package org.test.logic;

import java.util.List;

/**
 * Created by abara on 18.11.2016.
 */
public class Course {
    private Profile tutorProfile;
    private List<Lesson> lessons;
    private String courseName;
    private String description;

    public Course(Profile tutorProfile, List<Lesson> lessons, String courseName, String description) {
        this.tutorProfile = tutorProfile;
        this.lessons = lessons;
        this.courseName = courseName;
        this.description = description;
    }

    public Profile getTutorProfile() {
        return tutorProfile;
    }

    public void setTutorProfile(Profile tutorProfile) {
        this.tutorProfile = tutorProfile;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
