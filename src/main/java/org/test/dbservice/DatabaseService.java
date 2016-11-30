package org.test.dbservice;

import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.entity.UsersEntity;
import org.test.logic.Course;
import org.test.logic.InboxMessage;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by abara on 10.11.2016.
 */
public interface DatabaseService {
    int signUpUser(String firstName, String lastName,
                   String email, Date birthDate,
                   String password, String education);

    UsersEntity getUser(String email, String password);

    boolean doesUserExist(String email, String password);

    Profile fulfillProfile(String userEmail);

    List<DocumentBoxImpl> pullDocuments();

    Collection<Course> pullCourses();

    Course addNewCourse(Course course);

    Lesson addNewLesson(Lesson lesson);

    List<InboxMessage> pullInboxMessages(Profile profile);

    Profile getProfile(String email);

    void storeInboxMessage(InboxMessage message);

    List<Lesson> pullAllUserLessons(Profile currentProfile);

    void assignProfileToLesson(Lesson lesson, Profile profile);
}
