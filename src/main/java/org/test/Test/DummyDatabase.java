package org.test.Test;

import org.test.logic.Profile;

import java.util.*;

/**
 * Created by abara on 10.11.2016.
 */
public class DummyDatabase {
    private static Set<DummyUser> store = new HashSet<DummyUser>();

    static {
        List<Profile> friends = new ArrayList<>();
        for(int i = 0; i < 11; i++) {
            Profile friendProfile = new Profile();
            friendProfile.setName("Name" + i);
            friendProfile.setSurname("Surname" + i);
            friendProfile.setEmail("email" + i + "@mail.ru");
            friendProfile.setEducation("School" + i);
            friendProfile.setBirthDate(new Date());
            friends.add(friendProfile);
        }

        List<Profile> tarasFriends = new ArrayList<>();
        Profile arkady = new Profile();
        arkady.setId(13);
        arkady.setName("Arkady");
        arkady.setSurname("Baranok");
        arkady.setEmail("arkady@mail.ru");
        arkady.setBirthDate(new Date());
        arkady.setEducation("MIPT");
        tarasFriends.add(arkady);

        List<Profile> arkadyFriends = new ArrayList<>();
        Profile taras = new Profile();
        taras.setId(11);
        taras.setName("Taras");
        taras.setSurname("Khakhulin");
        taras.setEmail("ataras@mail.ru");
        taras.setBirthDate(new Date());
        taras.setEducation("Caucasus mountains");
        arkadyFriends.add(taras);

        store.add(new DummyUser(
                11, "Taras", "Khakhulin", "taras@mail.ru", "taras", "Caucasus mountains", new Date(), tarasFriends));
        store.add(new DummyUser(
                12, "Master", "Yoda", "", "", "Jedi temple", new Date(), friends));
        store.add(new DummyUser(
                13, "Arkady", "Baranok", "arkady@mail.ru", "arkady", "MIPT", new Date(), arkadyFriends));

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
