package org.test.dbservice;

import java.util.Date;

/**
 * Created by Аркадий on 27.10.2016.
 */
public interface DatabaseService {
    boolean signUpUser(String name, String surname,
                       String email, Date birthDate,
                       String password, String education);

}
