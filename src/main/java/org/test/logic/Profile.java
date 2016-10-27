package org.test.logic;

import java.util.Date;

/**
 * Created by Аркадий on 28.10.2016.
 */
public class Profile {
    private static Profile ourInstance = new Profile();

    private String name;
    private String surname;
    private String email;
    private String education;
    private Date birthDate;

    public static Profile getInstance() {
        return ourInstance;
    }

    private Profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
