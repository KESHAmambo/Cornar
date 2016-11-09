package org.test.dbservice;

import org.test.dbservice.entity.UsersEntity;
import org.test.logic.Profile;

import java.util.Date;

/**
 * Created by abara on 10.11.2016.
 */
public interface DatabaseService {
    int signUpUser(String firstName, String lastName,
                   String email, Date birthDate,
                   String password, String education);

    UsersEntity getUser(String email, String password);

    boolean doesUserExist(String email, String password);

    void fulfillProfile(Profile profile, String userEmail);
}
