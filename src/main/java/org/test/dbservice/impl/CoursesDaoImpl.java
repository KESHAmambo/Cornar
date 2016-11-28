package org.test.dbservice.impl;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.CoursesDao;
import org.test.dbservice.entity.CoursesEntity;
import org.test.dbservice.entity.UsersEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taras on 27.10.2016.
 */
public class CoursesDaoImpl extends AbstractServiceSession implements CoursesDao {

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



    public CoursesEntity getUserByEmail(String email){
        Session session = openCurrentSessionWithTransaction();
        CoursesEntity user = (CoursesEntity) session.createCriteria(CoursesEntity.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
        shutdownCurrentSession();
        return user;
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

    public void saveCourse(String nameOfCourse, String courseDescription, int userId){
        CoursesEntity course = new CoursesEntity();
        course.setCourseName(nameOfCourse);
        course.setCourseDescription(courseDescription);
        UsersEntity userTutor = new UsersEntity();
        userTutor.setUserId(userId);
        course.setUser(userTutor);
        create(course);
    }

    //TODO may be problem with others course
    @Override
    public List<CoursesEntity> getAllCourses(int id) {
        Session session = openCurrentSessionWithTransaction();
        List<CoursesEntity> courses = new ArrayList<>();
        UsersEntity user = new UsersEntity();
        user.setUserId(id);
        Criteria crtForAll = session.createCriteria(CoursesEntity.class);
        crtForAll.setMaxResults(10);
        courses = crtForAll.list();
        shutdownCurrentSession();
        return courses;
    }
}
