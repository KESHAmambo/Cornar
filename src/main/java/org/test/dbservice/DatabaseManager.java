package org.test.dbservice;

import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.UserDaoImpl;
import org.test.logic.Profile;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private static Logger loggerDataBase = Logger.getLogger(DatabaseManager.class.getName());

    private DatabaseManager() {

    }

    /** TO FIX: must return a result-code depending on result of signUp-operation
     * @see org.test.controllers.MainPageController
     */
    public static int signUpUser(String firstName, String lastName,
                          String email, Date birthDate,
                          String password, String education) {
        //TODO verification password
        int successOfAddingUser = 0;
        UserDao userDao = new UserDaoImpl();
        if (doesUserExist(email,password) == true)
            return successOfAddingUser;
        UsersEntity user = new UsersEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDate(new java.sql.Date(birthDate.getTime()));
        user.setPersonDescription(education);
        successOfAddingUser  = userDao.create(user);
        loggerDataBase.log(Level.INFO, "success in creating user");
        return successOfAddingUser;
    }

    public static UsersEntity getUser(String email, String password) {
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = userDao.getByEmailAndPassword(email,password);
        if (user != null){
            loggerDataBase.log(Level.INFO, "user:" + user.getEmail() + " exists");
            return user;
        }
        return null;
    }

    public static boolean doesUserExist(String email, String password){
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = userDao.getByEmailAndPassword(email, password);
        return user != null;
    }

    public static void fulfillProfile(Profile profile, String userEmail) {
        //TODO
    }
}
