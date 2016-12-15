package org.test.dbservice.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taras on 30.11.2016.
 */
@Entity
@Table(name = "lessons", schema = "project_cornar", catalog = "cornar")
public class LessonsEntity {

    private int lessonId;
    private Timestamp startDate;
    private String lessonName;
    private Long price;
    private Timestamp finalDate;
    private CoursesEntity course;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "lesson_id", nullable = false)
    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "lesson_name", nullable = true, length = 100)
    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


    @Basic
    @Column(name = "final_date", nullable = true)
    public Timestamp getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Timestamp finalDate) {
        this.finalDate = finalDate;
    }

    @JoinColumn(name = "course_id",insertable=false, updatable=false)
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public CoursesEntity getCourse() {
        return course;
    }

    public void setCourse(CoursesEntity course) {
        this.course = course;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonsEntity that = (LessonsEntity) o;

        if (lessonId != that.lessonId) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (lessonName != null ? !lessonName.equals(that.lessonName) : that.lessonName != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (finalDate != null ? !finalDate.equals(that.finalDate) : that.finalDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lessonId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (lessonName != null ? lessonName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (finalDate != null ? finalDate.hashCode() : 0);
        return result;
    }
}
