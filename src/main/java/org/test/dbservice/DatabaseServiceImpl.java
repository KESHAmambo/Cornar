package org.test.dbservice;

import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.UserDaoImpl;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

class DatabaseServiceImpl implements DatabaseService {
    Logger loggerDataBase = Logger.getLogger(DatabaseServiceImpl.class.getName());
    @Override
    public int signUpUser(String firstName, String lastName,
                          String email, Date birthDate,
                          String password, String education) {
        //TODO verification password
        int succsessOfAddingUser = 0;
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = new UsersEntity();
        if (isUserExist(email,password) == true)
            return succsessOfAddingUser;
        user = new UsersEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDate(new java.sql.Date(birthDate.getTime()));
        user.setPersonDescription(education);
        succsessOfAddingUser  = userDao.create(user);
        loggerDataBase.log(Level.INFO, "success in creating user");
        return succsessOfAddingUser;
    }

    @Override
    public UsersEntity getUser(String email, String password) {
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = userDao.getByEmailAndPassword(email,password);
        if (user != null){
            loggerDataBase.log(Level.INFO, "user:" + user.getEmail() + " exists");
            return user;
        }
        return null;
    }

    public boolean isUserExist(String email, String password){
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = new UsersEntity();
        user = userDao.getByEmailAndPassword(email, password);
        if (user != null)
            return true;
        return false;
    }


}
