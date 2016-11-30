package org.test.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abara on 18.11.2016.
 */
public class Course {
    private int id;
    private Profile tutorProfile;
    private List<Lesson> lessons;
    private String name;
    private String description;

    public Course(int id, Profile tutorProfile, List<Lesson> lessons, String name, String description) {
        this.id = id;
        this.tutorProfile = tutorProfile;
        this.lessons = lessons;
        this.name = name;
        this.description = description;
    }

    public Course(Profile tutorProfile, String name, String description) {
        this.tutorProfile = tutorProfile;
        this.name = name;
        this.description = description;
        lessons = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
