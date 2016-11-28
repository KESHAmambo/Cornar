package org.test.dbservice;

import com.vaadin.ui.Notification;
import org.test.controllers.MainPageController;
import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.dao.FilesDao;
import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.FilesEntity;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.FilesDaoImpl;
import org.test.dbservice.impl.UserDaoImpl;
import org.test.dbservice.utils.PasswordUtils;
import org.test.logic.Course;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseServiceImpl implements DatabaseService {
    Logger loggerDB = Logger.getLogger(DatabaseService.class.getName());
    @Override
    public int signUpUser(String firstName, String surname, String email, Date birthDate, String password, String education) {
        int unsuccessOfAddingUser = 1;
        UserDao userDao = new UserDaoImpl();
        if (doesUserExist(email, password) == true)
            return unsuccessOfAddingUser;
        UsersEntity user = new UsersEntity();
        user.setFirstName(firstName);
        user.setLastName(surname);
        user.setEmail(email);
        user.setPassword(password);
        if (birthDate != null)
            user.setBirthDate(new java.sql.Date(birthDate.getTime()));
        user.setPersonDescription(education);
        unsuccessOfAddingUser = userDao.create(user);
        return unsuccessOfAddingUser;
    }

    @Override
    public UsersEntity getUser(String email, String password) {
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = userDao.getByEmailAndPassword(email, password);
        loggerDB.log(Level.INFO, user.getEmail());
        if (user.getUserId() != 0) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean doesUserExist(String email, String password) {
        UsersEntity user = getUser(email, password);
        return user != null;
    }

    @Override
    public void fulfillProfile(Profile profile, String userEmail) {
        UsersEntity user = new UserDaoImpl().getUserByEmail(userEmail);
        profile = fillProfile(profile, user);
    }

    private Profile fillProfile(Profile profile, UsersEntity user) {
        profile.setName(user.getFirstName());
        profile.setSurname(user.getLastName());
        profile.setEmail(user.getEmail());
        profile.setEducation(user.getPersonDescription());
        profile.setBirthDate(user.getBirthDate());
        profile.setId(user.getUserId());
        return profile;
    }

    @Override
    public List<Profile> getAllUsersWithNameLike(String firstName) {
        UserDao userDao = new UserDaoImpl();
        List<UsersEntity> usersList = userDao.searchUserByName(firstName);
        return createListOfProfileFromUsers(usersList);
    }

    private List<Profile> createListOfProfileFromUsers(List<UsersEntity> usersList) {
        List<Profile> usersProfileList = new ArrayList<>();
        for (UsersEntity user :
                usersList) {
            Profile profileOfUser = new Profile();
            usersProfileList.add(fillProfile(profileOfUser, user));
        }
        return usersProfileList;
    }

    @Override
    public List<Profile> getAllUsersWithSurnameLike(String surnameForSearch) {
        UserDao userDao = new UserDaoImpl();
        List<UsersEntity> usersList = userDao.searchUserBySurname(surnameForSearch);
        return createListOfProfileFromUsers(usersList);
    }

    @Override
    public List<DocumentBoxImpl> pullDocuments(int ownerId) {
        List<DocumentBoxImpl> documents = new ArrayList<>();
        FilesDao fileDao = new FilesDaoImpl();
        List<FilesEntity> files = fileDao.getAllFiles(ownerId);
        for (FilesEntity file : files){
            DocumentBoxImpl document = new DocumentBoxImpl(file.getFileName(),
                                        new Date(file.getCreation_date().getTime()));
            documents.add(document);
        }
        return documents;
    }

    @Override
    public Collection<Course> pullCourses() {
        return null; //TODO
    }

    @Override
    public void addNewCourse(Course course) {
        //TODO
    }

    @Override
    public void addNewLesson(Lesson lesson) {
        //TODO
    }

    public void saveFile(String filename, int ownerId) {
        FilesDao filesDao = new FilesDaoImpl();
        File fileToUpload = new File(filename);
        byte[] dataForSaving = new byte[(int)fileToUpload.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(fileToUpload);
            fileInputStream.read(dataForSaving);
            fileInputStream.close();
        }catch (FileNotFoundException e){
            loggerDB.log(Level.SEVERE, null, e);
        }catch (IOException e) {
            loggerDB.log(Level.SEVERE, null, e.getStackTrace());
        }
        if (dataForSaving != null) {
            filesDao.saveFile(filename, dataForSaving, ownerId);
        }
        else
           loggerDB.log(Level.SEVERE, null, "Your data is cracked");
    }
}