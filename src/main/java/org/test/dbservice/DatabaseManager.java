package org.test.dbservice;

import org.test.Test.DummyDatabaseService;
import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.UserDaoImpl;
import org.test.logic.Profile;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private static DatabaseService service = new DummyDatabaseService();

    private DatabaseManager() {

    }
    public static int signUpUser(String firstName, String surname,
                          String email, Date birthDate,
                          String password, String education) {
        return service.signUpUser(
                firstName, surname, email,
                birthDate, password, education);
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
    public static List<DocumentBoxImpl> pullDocuments(){
        return service.pullDocuments();
    }
}
