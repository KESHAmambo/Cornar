package org.test.dbservice.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Taras on 14.11.2016.
 */
@Entity
@Table(name = "courses", schema = "project_cornar", catalog = "cornar")
public class CoursesEntity {

    private int courseId;
    private String courseName;
    private String courseDescription;
    private UsersEntity user;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL )
    @JoinColumn(name = "user_id")
    public UsersEntity getUser(){
        return user;
    }
    public void setUser(UsersEntity user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "course_name", nullable = true, length = 100)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "course_description", nullable = true, length = 500)
    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoursesEntity that = (CoursesEntity) o;

        if (courseId != that.courseId) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) return false;
        if (courseDescription != null ? !courseDescription.equals(that.courseDescription) : that.courseDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseId;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (courseDescription != null ? courseDescription.hashCode() : 0);
        return result;
    }

    @JoinColumn(name = "course_id")
    private Set<LessonsEntity> lessons;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<LessonsEntity> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonsEntity> getLesson) {
        this.lessons = getLesson;
    }

    public void addToLessons(LessonsEntity lesson){
        lesson.setCourse(this);
        getLessons().add(lesson);
    }
    public void removeLesson(LessonsEntity lesson){
        getLessons().remove(lesson);
    }
}
