package org.test.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by abara on 10.11.2016.
 */
public class DummyDatabase {
    private static Set<DummyUser> store = new HashSet<DummyUser>();

    static {
        store.add(new DummyUser(1, "Taras", "Khakhulin", "taras@mail.ru", "taras", "Caucasus mountains", new Date()));
        store.add(new DummyUser(2, "Master", "Yoda", "", "", "Jedi temple", new Date()));
        store.add(new DummyUser(3, "Arkady", "Baranok", "arkady@mail.ru", "arkady", "MIPT", new Date()));
    }

    static boolean doesUserExist(String email, String password) {
        DummyUser tempUser = new DummyUser(email, password);
        return store.contains(tempUser);
    }

    static DummyUser getUserByEmail(String email) {
        for(DummyUser user: store) {
            if(user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
