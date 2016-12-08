package org.test.dbservice.entity;

import javax.persistence.*;

/**
 * Created by Taras on 30.11.2016.
 */
@Entity
@Table(name = "transaction", schema = "project_cornar", catalog = "cornar")
public class TransactionEntity {

    private int number;
    private Integer lessonsId;
    private Integer userId;
    private Integer courseId;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "lessons_id", nullable = true)
    public Integer getLessonsId() {
        return lessonsId;
    }

    public void setLessonsId(Integer lessonsId) {
        this.lessonsId = lessonsId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "course_id", nullable = true)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionEntity that = (TransactionEntity) o;

        if (number != that.number) return false;
        if (lessonsId != null ? !lessonsId.equals(that.lessonsId) : that.lessonsId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (lessonsId != null ? lessonsId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        return result;
    }
}
