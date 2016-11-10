package org.test.dbservice;

import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.UserDaoImpl;
import org.test.logic.Profile;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private static DatabaseService service = new DatabaseServiceImpl();

    private DatabaseManager() {

    }

    /** TO FIX: must return a result-code depending on result of signUp-operation
     * @see org.test.controllers.MainPageController
     */
    public static int signUpUser(String firstName, String lastName,
                          String email, Date birthDate,
                          String password, String education) {
        //TODO hash password
        int successOfAddingUser = 1;
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
        return successOfAddingUser;
    }

    public static UsersEntity getUser(String email, String password) {
        return service.getUser(email, password);
    }

    public static boolean doesUserExist(String email, String password) {
        return service.doesUserExist(email, password);
    }

    public static void fulfillProfile(Profile profile, String userEmail) {
        service.fulfillProfile(profile, userEmail);
        UsersEntity user = new UserDaoImpl().getUserByEmail(userEmail);
        profile.setName(user.getFirstName());
        profile.setSurname(user.getLastName());
        profile.setEmail(userEmail);
        profile.setEducation(user.getPersonDescription());
        profile.setBirthDate(user.getBirthDate());
        profile.setId(user.getUserId());
    }
}
