package org.test.dbservice.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.utils.HibernateSessionFactory;

/**
 * Created by Taras on 27.10.2016.
 */
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;


    private Session currentSession;
    private Transaction currentTransaction;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public UserDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    public Session openCurrentSession(){
        currentSession = sessionFactory.openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction(){
        currentSession = sessionFactory.getCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void shutdownCurrentSession(){
        currentSession.close();
    }

    public void shutdownCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }



    @Override
    public void create(UsersEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        session.persist(entity);
        shutdownCurrentSessionWithTransaction();
    }

    @Override
    public void delete(UsersEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        session.delete(entity);
        shutdownCurrentSessionWithTransaction();
    }

    @Override
    public UsersEntity getById(Long id) {
        UsersEntity entityById = null;
        Session session;
        session = openCurrentSession();
        entityById = session.get(UsersEntity.class,id);
        shutdownCurrentSession();
        return entityById;
    }

    @Override
    public void update(UsersEntity entity) {

    }

    @Override
    public Integer deleteAll() {
        return null;
    }
}
