package org.test.dbservice;

import org.test.dbservice.entity.UsersEntity;

import java.util.Date;

/**
 * Created by Аркадий on 27.10.2016.
 */
public interface DatabaseService {
    public int signUpUser(String name, String surname,
                          String email, Date birthDate,
                          String password, String education);

    public UsersEntity getUser(String email, String password);

    public boolean isUserExist(String email, String password);
}
