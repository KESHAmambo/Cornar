package org.test.dbservice.impl;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

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

    public UsersEntity getByEmailAndPassword(String email, String password) {
        Session session = openCurrentSessionWithTransaction();
        UsersEntity user = (UsersEntity) session.createCriteria(UsersEntity.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password)).uniqueResult();
        shutdownCurrentSessionWithTransaction();
        return user;
    }

    public List<UsersEntity> getAllUsers() {
        Session session = openCurrentSessionWithTransaction();
        List<UsersEntity> listOfUsers = new ArrayList<>();
        Query query = session.createQuery("select  * from project_cornar.users");
        listOfUsers = (List<UsersEntity>)query.list();
        shutdownCurrentSessionWithTransaction();
        return listOfUsers;
    }

    @Override
    public void update(UsersEntity entity) {

    }

    @Override
    public Integer deleteAll() {
        return null;
    }
}
