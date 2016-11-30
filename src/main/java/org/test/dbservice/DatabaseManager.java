package org.test.dbservice;

import org.test.Test.DummyDatabaseService;
import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.logic.Course;
import org.test.logic.InboxMessage;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    public static boolean doesUserExist(String email, String password) {
        return service.doesUserExist(email, password);
    }

    public static void fulfillProfile(Profile profile, String userEmail) {
        service.fulfillProfile(profile, userEmail);
    }

    public static List<DocumentBoxImpl> pullDocuments() {
        return service.pullDocuments();
    }

    public static Collection<Course> pullCourses() {
        return service.pullCourses();
    }

    public static Course addNewCourse(String courseName, String description) {
        return service.addNewCourse(
                new Course(Profile.getCurrentProfile(), courseName, description));
    }

    public static Lesson addNewLesson(Course course, String lessonName, double cost, Date startDate, Date endDate) {
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
}
