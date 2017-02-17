package org.test.dbservice.dao;

import org.test.dbservice.entity.CoursesEntity;

import java.util.List;

/**
 * Created by Taras on 14.11.2016.
 */
public interface CoursesDao extends DAO<CoursesEntity> {
    void saveCourse(String nameOfCourse, String courseDescription, int userId);

    List<CoursesEntity> getAllCourses(int id);
}
