package org.test.dbservice.impl;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taras on 27.10.2016.
 */
public class UserDaoImpl extends AbstractServiceSession implements UserDao {

    @Override
    public int create(UsersEntity entity) {
        Session session;
        int count = 1;
        try {
            session = openCurrentSessionWithTransaction();
            session.save(entity);
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

    public UsersEntity getUserByEmail(String email){
        Session session = openCurrentSessionWithTransaction();
        UsersEntity user = (UsersEntity) session.createCriteria(UsersEntity.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
        shutdownCurrentSessionWithTransaction();
        return user;
    }
    public List<UsersEntity> getAllUsers() {
        Session session = openCurrentSessionWithTransaction();
        List<UsersEntity> listOfUsers = new ArrayList<>();
        //Query query = session.createQuery("select * from project_cornar.users");
        //listOfUsers = (List<UsersEntity>)query.list();
        shutdownCurrentSessionWithTransaction();
        return listOfUsers;
    }

    @Override
    public void update(UsersEntity entity) {
        //TODO test
        Session session = openCurrentSessionWithTransaction();
        session.update(entity);
        shutdownCurrentSessionWithTransaction();
    }

    @Override
    public void deleteAll() {
        List<UsersEntity> allUsers = getAllUsers();
        for (UsersEntity user : allUsers) {
            delete(user);
        }
    }
    @Override
    public List<UsersEntity> searchUserByName(String firstName){
        Session session = openCurrentSessionWithTransaction();
        Query queryForSearch = session.createQuery("from UsersEntity as user where lower(user.first_name) like :searchFirstName");
        queryForSearch.setString("searchFirstName", "%" + firstName + "%");
        List<UsersEntity> allFindUsers = queryForSearch.list();
        shutdownCurrentSession();
        return  allFindUsers;
    }

    @Override
    public List<UsersEntity> searchUserBySurname(String surname) {
        Session session = openCurrentSessionWithTransaction();
        Query queryForSearch = session.createQuery("from UsersEntity as user where lower(user.last_name) like :searchFirstName");
        queryForSearch.setString("searchFirstName", "%" + surname + "%");
        List<UsersEntity> allFindUsers = queryForSearch.list();
        shutdownCurrentSession();
        return  allFindUsers;
    }
}
