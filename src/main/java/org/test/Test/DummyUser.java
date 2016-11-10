package org.test.Test;

import java.util.Date;

/**
 * Created by abara on 10.11.2016.
 */
class DummyUser {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String education;
    private Date birthDate;

    public DummyUser(int id, String name, String surname, String email, String password, String education, Date birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.education = education;
        this.birthDate = birthDate;
    }

    public DummyUser(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DummyUser dummyUser = (DummyUser) o;

        if (email != null ? !email.equals(dummyUser.email) : dummyUser.email != null) return false;
        return password != null ? password.equals(dummyUser.password) : dummyUser.password == null;

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
