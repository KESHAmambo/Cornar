package org.test.dbservice.dao;

import org.test.dbservice.entity.LessonsEntity;
import org.test.logic.Course;
import org.test.logic.Lesson;

import java.util.Date;

/**
 * Created by Taras on 14.11.2016.
 */
public interface LessonsDao extends DAO<LessonsEntity> {
    void saveLesson(Course course, String nameOfLesson, Date startDate, Date finalDate, Long price);
    Lesson fillLessonInfoById(int id);
}
