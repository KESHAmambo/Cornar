package org.test.Test;

import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.DatabaseService;
import org.test.dbservice.entity.UsersEntity;
import org.test.logic.Course;
import org.test.logic.InboxMessage;
import org.test.logic.Lesson;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.materialspage.DocumentBox;

import java.io.File;
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
    public boolean doesUserExist(String email) {
        return false;
    }

    @Override
    public Profile fulfillProfile(String userEmail) {
        return DummyDatabase.getProfile(userEmail);
        /*return DummyDatabase.getProfile(userEmail);
        DummyUser user = DummyDatabase.getUserByEmail(userEmail);
        if(user != null) {
            profile.setId(user.getId());
            profile.setName(user.getName());
            profile.setSurname(user.getSurname());
            profile.setBirthDate(user.getBirthDate());
            profile.setEducation(user.getEducation());
            profile.setEmail(user.getEmail());
            profile.setImage(user.getImage());
            profile.setFriends(user.getFriends());
        }*/
    }

    @Override
    public List<Profile> getAllUsersWithNameLike(String firstName) {
        return DummyDatabase.getAllUsersWithNameLike(firstName);
    }

    @Override
    public List<Profile> getAllUsersWithSurnameLike(String surnameForSearch) {
        return null;
    }

    public List<DocumentBoxImpl> pullDocuments(int ownerId) {
        List<DocumentBoxImpl> documents = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.set(2014, 4, 5);
        documents.add(new DocumentBoxImpl(
                "File_with_a_long_name",
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
    public Course addNewCourse(Course course) {
        return DummyDatabase.addNewCourse(course);
    }

    @Override
    public Lesson addNewLesson(Lesson lesson) {
        return DummyDatabase.addNewLesson(lesson);
        //TODO
    }

    @Override
    public List<InboxMessage> pullInboxMessages(Profile profile) {
        return DummyDatabase.pullInboxMessages(profile);
    }

    @Override
    public Profile getProfile(String email) {
        return DummyDatabase.getProfile(email);
    }

    @Override
    public void storeInboxMessage(InboxMessage message) {
        DummyDatabase.storeInboxMessage(message);
    }

    @Override
    public List<Lesson> pullAllUserLessons(Profile currentProfile) {
        return DummyDatabase.pullAllLessons(currentProfile);
    }

    @Override
    public void assignProfileToLesson(Lesson lesson, Profile profile) {
        DummyDatabase.assignProfileToLesson(lesson, profile);
    }

    @Override
    public void saveFile(String filename, int ownerId) {

    }

    @Override
    public void addToFriends(int userId, String friendEmail) {
        DummyDatabase.addFriend(userId, friendEmail);
    }

    @Override
    public List<Profile> getAllFriendOfUser(int user_id) {
        return null;
    }

    @Override
    public byte[] getFileByName(String docName, int ownerId) {
        return new byte[0];
    }

    @Override
    public void saveUserImage(int userId, File image) {

    }


}
