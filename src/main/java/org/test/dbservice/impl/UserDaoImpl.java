package org.test.dbservice.impl;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.utils.HibernateSessionFactory;

/**
 * Created by Taras on 27.10.2016.
 */
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();;


    private Session currentSession;
    private Transaction currentTransaction;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public UserDaoImpl() {
    }

    public Session openCurrentSession(){
        currentSession = getSessionFactory().getCurrentSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction(){
        currentSession = openCurrentSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void shutdownCurrentSession(){
        currentSession.close();
    }

    public void shutdownCurrentSessionWithTransaction(){
        currentTransaction.commit();
//        currentSession.close();
    }



    @Override
    public int create(UsersEntity entity) {
        Session session;
        int count = 0;
        try {
            session = openCurrentSessionWithTransaction();
            count = (Integer) session.save(entity);
        }catch (HibernateException e){
            System.out.printf("Exist");
        }finally {
            shutdownCurrentSessionWithTransaction();
        }
        return count;
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
        entityById = (UsersEntity) session.get(UsersEntity.class,id);
        shutdownCurrentSession();
        return entityById;
    }

    @Override
    public void update(UsersEntity entity) {

    }

    public UsersEntity getByEmailAndPassword(String email, String password){
        UsersEntity user = new UsersEntity();
        Session session = openCurrentSessionWithTransaction();
        user = (UsersEntity) session.createCriteria(UsersEntity.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password)).uniqueResult();
        shutdownCurrentSessionWithTransaction();
        return user;

    }

    @Override
    public Integer deleteAll() {
        return null;
    }
}
