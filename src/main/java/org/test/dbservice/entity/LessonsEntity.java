package org.test.dbservice.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Taras on 14.11.2016.
 */
@Entity
@Table(name = "lessons", schema = "project_cornar", catalog = "cornar")
public class LessonsEntity {
    private int lessonId;
    private Date dateLesson;
    private String lessonName;
    private Long price;
    private CoursesEntity coursesByCourseId;

    @Id
    @Column(name = "lesson_id", nullable = false)
    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    @Basic
    @Column(name = "date_lesson", nullable = true)
    public Date getDateLesson() {
        return dateLesson;
    }

    public void setDateLesson(Date dateLesson) {
        this.dateLesson = dateLesson;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonsEntity that = (LessonsEntity) o;

        if (lessonId != that.lessonId) return false;
        if (dateLesson != null ? !dateLesson.equals(that.dateLesson) : that.dateLesson != null) return false;
        if (lessonName != null ? !lessonName.equals(that.lessonName) : that.lessonName != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lessonId;
        result = 31 * result + (dateLesson != null ? dateLesson.hashCode() : 0);
        result = 31 * result + (lessonName != null ? lessonName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    public CoursesEntity getCoursesByCourseId() {
        return coursesByCourseId;
    }

    public void setCoursesByCourseId(CoursesEntity coursesByCourseId) {
        this.coursesByCourseId = coursesByCourseId;
    }
}
