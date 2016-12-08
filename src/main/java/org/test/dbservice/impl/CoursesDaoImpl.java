package org.test.dbservice.impl;

import org.hibernate.*;
import org.test.dbservice.dao.CoursesDao;
import org.test.dbservice.entity.CoursesEntity;
import org.test.dbservice.entity.LessonsEntity;
import org.test.dbservice.entity.UsersEntity;
import org.test.logic.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Taras on 27.10.2016.
 */
public class CoursesDaoImpl extends AbstractServiceSession implements CoursesDao {
    static Logger loggerCourse = Logger.getLogger(CoursesDaoImpl.class.getName());
    @Override
    public int create(CoursesEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        int count = (int) session.save(entity);
        shutdownCurrentSession();
        return count;
    }

    @Override
    public void delete(CoursesEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        session.delete(entity);
        shutdownCurrentSession();
    }

    @Override
    public CoursesEntity getById(int id) {
        CoursesEntity entityById = null;
        Session session;
        session = openCurrentSession();
        entityById = (CoursesEntity) session.get(CoursesEntity.class, id);
        shutdownCurrentSession();
        return entityById;
    }





    @Override
    public void update(CoursesEntity entity) {
        Session session = openCurrentSessionWithTransaction();
        session.update(entity);
        shutdownCurrentSession();
    }

    @Override
    public void deleteAll() {
//        List<CoursesEntity> allUsers = getAllCourses();
//        for (CoursesEntity user : allUsers) {
//            delete(user);
//        }
    }
    public Set<LessonsEntity> getAllLessonByCourse(Course course){
        Session session = openCurrentSessionWithTransaction();
        CoursesEntity coursesEntity = new CoursesEntity();
        loggerCourse.log(Level.INFO,"CourseId" + String.valueOf(course.getId()));
        coursesEntity.setCourseId(course.getId());
        coursesEntity.setCourseName(course.getName());
        coursesEntity.setCourseDescription(course.getDescription());
        Query query = session.createQuery("from CoursesEntity as c where c.courseId=:Course");
        query.setParameter("Course", course.getId());
        CoursesEntity courses= (CoursesEntity) query.uniqueResult();
        for (LessonsEntity entity : courses.getLessons())
            System.err.println(entity.getLessonId());
        Set<LessonsEntity> lessonsEntities = courses.getLessons();
        shutdownCurrentSession();
        return lessonsEntities;
    }

    public void saveCourse(String nameOfCourse, String courseDescription, int userId){
        UsersEntity userTutor = new UsersEntity();
        Session session = openCurrentSessionWithTransaction();
        Query query = session.createQuery("from UsersEntity as u where u.userId=:ID");
        query.setInteger("ID", userId);
        userTutor = (UsersEntity) query.uniqueResult();
        shutdownCurrentSession();
        CoursesEntity course = new CoursesEntity();
        course.setCourseName(nameOfCourse);
        course.setCourseDescription(courseDescription);
        if (userTutor == null)
            System.out.println("user is NULL");
        course.setUser(userTutor);
        create(course);
    }

    //TODO may be problem with others course
    @Override
    public List<CoursesEntity> getAllCourses(int id) {
        Session session = openCurrentSessionWithTransaction();
        List<CoursesEntity> courses = new ArrayList<>();
        Criteria crtForAll = session.createCriteria(CoursesEntity.class);
        crtForAll.setMaxResults(10);
        courses = crtForAll.list();
        shutdownCurrentSession();
        return courses;
    }


}
