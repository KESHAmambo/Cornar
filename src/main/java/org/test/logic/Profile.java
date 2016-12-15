package org.test.logic;

import com.vaadin.server.VaadinSession;
import org.test.dbservice.DatabaseManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Аркадий on 28.10.2016.
 */
public class Profile {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String education;
    private Date birthDate;
    private File imageResource;
    private List<Profile> friends = new ArrayList<>();

    public Profile() {

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

    public File getImageResource() {
        return imageResource;
    }

    public void setImageResource(File imageResource) {
        this.imageResource = imageResource;
    }

    public List<Profile> getFriends() {
        return friends;
    }

    public void setFriends(List<Profile> friends) {
        this.friends = friends;
    }

    public static Profile getCurrentProfile() {
        Profile profile = (Profile) VaadinSession.getCurrent().getAttribute("profile");
        if(profile == null) {
            return new Profile();
        } else {
            return profile;
        }
    }

    public static Profile fulfillProfile(String userEmail) {
            return  DatabaseManager.fulfillProfile(userEmail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return id == profile.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
