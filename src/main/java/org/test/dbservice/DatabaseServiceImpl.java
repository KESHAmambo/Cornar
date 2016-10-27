package org.test.dbservice;

import org.test.dbservice.dao.UserDao;
import org.test.dbservice.entity.UsersEntity;
import org.test.dbservice.impl.UserDaoImpl;
import java.util.Date;

class DatabaseServiceImpl implements DatabaseService {
    @Override
    public boolean signUpUser(String name, String surname,
                              String email, Date birthDate,
                              String password, String education) {
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = new UsersEntity();
        user.setUserName(email);
        user.setFirstName(name);
        user.setLastName(surname);
        user.setPassword(password);
        user.setBirthDate(new java.sql.Date(birthDate.getTime()));
        user.setPersonDescription(education);
        userDao.create(user);
        return true;
    }
}
