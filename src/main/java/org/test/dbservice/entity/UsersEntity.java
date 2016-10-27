package org.test.dbservice.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Taras on 27.10.2016.
 */
@Entity
@Table(name = "USERS", schema = "JAVA_PROJECT", catalog = "")
public class UsersEntity {
    private long userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String personDescription;
    private Long personRating;
    private Date birthDate;

    @Id
    @Column(name = "USER_ID", nullable = false, precision = 0)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 40)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "FIRST_NAME", nullable = true, length = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LAST_NAME", nullable = true, length = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "PERSON_DESCRIPTION", nullable = true, length = 120)
    public String getPersonDescription() {
        return personDescription;
    }

    public void setPersonDescription(String personDescription) {
        this.personDescription = personDescription;
    }

    @Basic
    @Column(name = "PERSON_RATING", nullable = true, precision = 0)
    public Long getPersonRating() {
        return personRating;
    }

    public void setPersonRating(Long personRating) {
        this.personRating = personRating;
    }

    @Basic
    @Column(name = "BIRTH_DATE", nullable = true)
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

        UsersEntity that = (UsersEntity) o;

        if (userId != that.userId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (personDescription != null ? !personDescription.equals(that.personDescription) : that.personDescription != null)
            return false;
        if (personRating != null ? !personRating.equals(that.personRating) : that.personRating != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (personDescription != null ? personDescription.hashCode() : 0);
        result = 31 * result + (personRating != null ? personRating.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }
}
