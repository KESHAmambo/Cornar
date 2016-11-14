package org.test.dbservice.dao;

import org.test.dbservice.entity.UsersEntity;

import java.util.List;

/**
 * Created by Taras on 27.10.2016.
 */
public interface UserDao extends DAO<UsersEntity> {
    //TODO get by specific parameter
    UsersEntity getByEmailAndPassword(String email, String password);
    UsersEntity getUserByEmail(String email);

    List<UsersEntity> searchUserByName(String firstName);
}
