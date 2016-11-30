package org.test.dbservice;

import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.UserDaoImpl;
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
public class DatabaseServiceImpl implements DatabaseService {

    /** TO FIX: must return a result-code depending on result of signUp-operation
     * @see org.test.controllers.MainPageController
     */
    @Override
    public int signUpUser(String firstName, String surname, String email, Date birthDate, String password, String education) {
        //TODO verification password
        int successOfAddingUser = 0;
        UserDao userDao = new UserDaoImpl();
        if (doesUserExist(email,password) == true)
            return successOfAddingUser;
        UsersEntity user = new UsersEntity();
        user.setFirstName(firstName);
        user.setLastName(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDate(new java.sql.Date(birthDate.getTime()));
        user.setPersonDescription(education);
        successOfAddingUser  = userDao.create(user);
        return successOfAddingUser;
    }

    @Override
    public UsersEntity getUser(String email, String password) {
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = userDao.getByEmailAndPassword(email,password);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean doesUserExist(String email, String password) {
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = userDao.getByEmailAndPassword(email, password);
        return user != null;
    }

    @Override
    public void fulfillProfile(Profile profile, String userEmail) {
        //TODO
    }

    @Override
    public List<DocumentBoxImpl> pullDocuments() {
        return null;//TODO
    }


    @Override
    public Collection<Course> pullCourses() {
        return null; //TODO
    }

    @Override
    public Course addNewCourse(Course course) {
        return null;
        //TODO
    }

    @Override
    public Lesson addNewLesson(Lesson lesson) {
        //TODO
        return null;
    }

    @Override
    public List<InboxMessage> pullInboxMessages(Profile profile) {
        //TODO
        return null;
    }

    @Override
    public Profile getProfile(String email) {
        //TODO
        return null;
    }

    @Override
    public void storeInboxMessage(InboxMessage message) {
        //TODO
    }

    @Override
    public List<Lesson> pullAllUserLessons(Profile currentProfile) {
        //TODO
        return null;
    }

    @Override
    public void assignProfileToLesson(Lesson lesson, Profile profile) {
        //TODO
    }
}
