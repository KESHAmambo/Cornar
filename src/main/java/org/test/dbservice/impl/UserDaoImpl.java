package org.test.dbservice.impl;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.FilesEntity;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.utils.PasswordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Taras on 27.10.2016.
 */
public class UserDaoImpl extends AbstractServiceSession implements UserDao {

    @Override
    public int create(UsersEntity entity) {
        Session session;
        int count = 0;
        session = openCurrentSessionWithTransaction();
        session.save(entity);
        shutdownCurrentSession();
        return count;
    }

    @Override
    public void delete(UsersEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        session.delete(entity);
        shutdownCurrentSession();
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
        UsersEntity user = getUserByEmail(email);
        char[] charPassword = password.toCharArray();
        if (user !=null) {
            if (PasswordUtils.isExpectedPassword(charPassword, user.getPassword().getBytes()))
                return user;
        }
        return null;
    }

    public UsersEntity getUserByEmail(String email){
        Session session = openCurrentSessionWithTransaction();
        UsersEntity user = (UsersEntity) session.createCriteria(UsersEntity.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
        shutdownCurrentSession();
        return user;
    }

    public List<UsersEntity> getAllUsers() {
        Session session = openCurrentSessionWithTransaction();
        List<UsersEntity> listOfUsers = new ArrayList<>();
        Criteria crtForAll = session.createCriteria(UsersEntity.class);
        listOfUsers = crtForAll.list();
        shutdownCurrentSession();
        return listOfUsers;
    }

    @Override
    public void update(UsersEntity entity) {
        Session session = openCurrentSessionWithTransaction();
        session.update(entity);
        shutdownCurrentSession();
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
