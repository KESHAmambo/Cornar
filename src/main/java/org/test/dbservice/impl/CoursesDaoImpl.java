package org.test.dbservice.impl;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.CoursesDao;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.CoursesEntity;
import org.test.dbservice.utils.HibernateSessionFactory;

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
    public CoursesEntity getById(Long id) {
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

}
