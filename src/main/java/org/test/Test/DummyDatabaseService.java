package org.test.Test;

import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.DatabaseManager;
import org.test.dbservice.DatabaseService;
import org.test.dbservice.entity.UsersEntity;
import org.test.logic.Course;
import org.test.logic.Lesson;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.materialspage.DocumentBox;

import java.util.*;

/**
 * Created by abara on 10.11.2016.
 */
public class DummyDatabaseService implements DatabaseService {
    @Override
    public int signUpUser(String firstName, String lastName, String email, Date birthDate, String password, String education) {
        return 0;
    }

    @Override
    public UsersEntity getUser(String email, String password) {
        return null;
    }

    @Override
    public boolean doesUserExist(String email, String password) {
        return DummyDatabase.doesUserExist(email, password);
    }

    @Override
    public void fulfillProfile(Profile profile, String userEmail) {
        DummyUser user = DummyDatabase.getUserByEmail(userEmail);
        if(user != null) {
            profile.setId(user.getId());
            profile.setName(user.getName());
            profile.setSurname(user.getSurname());
            profile.setBirthDate(user.getBirthDate());
            profile.setEducation(user.getEducation());
            profile.setEmail(user.getEmail());
            profile.setFriends(user.getFriends());
        }
    }

    @Override
    public List<DocumentBoxImpl> pullDocuments() {
        List<DocumentBoxImpl> documents = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.set(2014, 4, 5);
        documents.add(new DocumentBoxImpl(
                "File with a very long name such as that: adkjfladj adsf adjffakjsdf asdffa fdjf" +
                        "File with a very long name such as that: adkjfladj adsf adjffakjsdf asdffa fdjf",
                new Date(calendar.getTimeInMillis())));
        calendar.set(2016, 8, 6);
        documents.add(new DocumentBoxImpl(
                "File 1_20" + Profile.getCurrentProfile().getName(),
                new Date(calendar.getTimeInMillis())));
        calendar.set(2016, 11, 8);
        documents.add(new DocumentBoxImpl(
                "Interesting idea",
                new Date(calendar.getTimeInMillis())));
        calendar.set(2016, 11, 11);
        documents.add(new DocumentBoxImpl(
                "Homework for 20.10.2016",
                new Date(calendar.getTimeInMillis())));
        return documents;
    }

    @Override
    public Collection<Course> pullCourses() {
        return DummyDatabase.pullCourses();
    }

    @Override
    public void addNewCourse(Course course) {
        DummyDatabase.addNewCourse(course);
    }

    @Override
    public void addNewLesson(Lesson lesson) {
        //TODO
    }
}
