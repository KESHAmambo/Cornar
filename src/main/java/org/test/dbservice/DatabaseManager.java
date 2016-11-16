package org.test.dbservice;

import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.UserDaoImpl;
import org.test.logic.Profile;

import java.util.Date;
import java.util.List;
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

        return service.signUpUser(firstName, lastName,
                            email, birthDate,
                            password, education);
    }

    public static UsersEntity getUser(String email, String password) {
        return service.getUser(email, password);
    }

    public static boolean doesUserExist(String email, String password) {
        return service.doesUserExist(email, password);
    }

    public static void fulfillProfile(Profile profile, String userEmail) {
        service.fulfillProfile(profile, userEmail);

    }
    public static List<Profile>  getAllUsersWithNameLike(String firstName) {
        return service.getAllUsersWithNameLike(firstName);
    }

    public static List<Profile> getAllUsersWithSurnameLike(String surnameForSearch) {
        return service.getAllUsersWithSurnameLike(surnameForSearch);
    }
}
