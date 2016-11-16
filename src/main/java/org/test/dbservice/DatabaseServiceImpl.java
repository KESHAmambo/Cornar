package org.test.dbservice;

import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.UserDaoImpl;
import org.test.logic.Profile;

import java.util.ArrayList;
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
        //TODO hashing password
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
        UserDaoImpl userDao = new UserDaoImpl();
        UsersEntity user = userDao.getByEmailAndPassword(email, password);
        return user != null;
    }

    @Override
    public void fulfillProfile(Profile profile, String userEmail) {
        UsersEntity user = new UserDaoImpl().getUserByEmail(userEmail);
        profile.setName(user.getFirstName());
        profile.setSurname(user.getLastName());
        profile.setEmail(userEmail);
        profile.setEducation(user.getPersonDescription());
        profile.setBirthDate(user.getBirthDate());
        profile.setId(user.getUserId());

    }

    private Profile fillProfile(UsersEntity user){
        Profile profile = new Profile();
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
        return  createListOfProfileFromUsers(usersList);
    }

    private List<Profile>  createListOfProfileFromUsers(List<UsersEntity> usersList){
        List<Profile> usersProfileList = new ArrayList<>();
        for (UsersEntity user:
                usersList) {
            usersProfileList.add(fillProfile(user));

        }
        return usersProfileList;
    }
    @Override
    public List<Profile> getAllUsersWithSurnameLike(String surnameForSearch) {
        UserDao userDao = new UserDaoImpl();
        List<UsersEntity> usersList = userDao.searchUserBySurname(surnameForSearch);
        return  createListOfProfileFromUsers(usersList);
    }

}
