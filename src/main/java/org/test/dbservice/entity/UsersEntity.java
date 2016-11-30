package org.test.dbservice.entity;


import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Taras on 29.10.2016.
 */
//TODO checking password
@Entity
@Table(name = "users", schema = "project_cornar", catalog = "cornar")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int userId;
    private String first_name;
    private String last_name;
    private String email;
    private String person_description;
    private Integer person_rating;
    private String password;
    private Date birth_date;

    @Column(name = "\"userid\"", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 50)
    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "birth_date", nullable = true)
    public Date getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(Date birth_date) {
        this.birth_date = birth_date;
    }

    @Basic
    @Column(name = "person_description", nullable = true, length = -1)
    public String getPersonDescription() {
        return person_description;
    }

    public void setPersonDescription(String person_description) {
        this.person_description = person_description;
    }

    @Basic
    @Column(name = "person_rating", nullable = true)
    public Integer getPersonRating() {
        return person_rating;
    }

    public void setPersonRating(Integer person_rating) {
        this.person_rating = person_rating;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userId != that.userId) return false;
        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        if (last_name != null ? !last_name.equals(that.last_name) : that.last_name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (birth_date != null ? !birth_date.equals(that.birth_date) : that.birth_date != null) return false;
        if (person_description != null ? !person_description.equals(that.person_description) : that.person_description != null)
            return false;
        if (person_rating != null ? !person_rating.equals(that.person_rating) : that.person_rating != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birth_date != null ? birth_date.hashCode() : 0);
        result = 31 * result + (person_description != null ? person_description.hashCode() : 0);
        result = 31 * result + (person_rating != null ? person_rating.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
