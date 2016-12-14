package org.test.dbservice;

import com.vaadin.ui.Notification;
import org.test.Test.DummyDatabaseService;
import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.UserDaoImpl;
import org.test.dbservice.utils.PasswordUtils;
import org.test.logic.Course;
import org.test.logic.InboxMessage;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    private static DatabaseService service = new DatabaseServiceImpl();

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
    public static List<DocumentBoxImpl> pullDocumentsBy(int ownerId){
        return service.pullDocuments(ownerId);
    }

    public static Collection<Course> pullCourses() {
        return service.pullCourses();
    }

    public static Course addNewCourse(String courseName, String description) {
        return service.addNewCourse(
                new Course(Profile.getCurrentProfile(), courseName, description));
    }

    public static Lesson addNewLesson(Course course, String lessonName, Long cost, Date startDate, Date endDate) {
        return service.addNewLesson(
                new Lesson(lessonName, course, cost, startDate, endDate));
    }

    public static List<InboxMessage> pullInboxMessages(Profile profile) {
        return service.pullInboxMessages(profile);
    }

    public static Profile getProfile(String email) {
        return service.getProfile(email);
    }

    public static void storeInboxMessage(InboxMessage message) {
        service.storeInboxMessage(message);
    }


    public static List<Lesson> pullAllUserLessons(Profile profile) {
        return service.pullAllUserLessons(profile);
    }

    public static void assignProfileToLesson(Lesson lesson, Profile profile) {
        service.assignProfileToLesson(lesson, profile);
    }

    public static void saveFile(String filename, int ownerId) {
        service.saveFile(filename, ownerId);
    }

    public static void addToFriends(int userId, String friendEmail) {
        service.addToFriends(userId, friendEmail);
    }
    public static List<Profile> getAllFriendOfUser(int user_id){
        return service.getAllFriendOfUser(user_id);
    }
}
