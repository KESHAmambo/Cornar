package org.test.logic;

import com.vaadin.ui.Layout;
import org.test.dbservice.DatabaseManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Аркадий on 28.10.2016.
 */
public class Profile {
    private static Profile currentProfile = new Profile();
    private static final List<Cleanable> cleanables = new ArrayList<>();

    private int id;
    private String name;
    private String surname;
    private String email;
    private String education;
    private Date birthDate;

    public static Profile getCurrentProfile() {
        return currentProfile;
    }

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

    public static void fulfillProfile(Profile profile, String userEmail) {
        DatabaseManager.fulfillProfile(profile, userEmail);
    }

    public static void clearCurrentProfile() {
        currentProfile = new Profile();
        cleanables.forEach(Cleanable::cleanInformation);
    }

    public static void registerCleanable(Cleanable cleanable) {
        cleanables.add(cleanable);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", education='" + education + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
