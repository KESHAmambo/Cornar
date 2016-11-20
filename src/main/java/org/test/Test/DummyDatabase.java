package org.test.Test;

import org.test.logic.Course;
import org.test.logic.Profile;

import java.util.*;

/**
 * Created by abara on 10.11.2016.
 */
public class DummyDatabase {
    private static Set<DummyUser> store = new HashSet<DummyUser>();
    private static List<Course> courses = new ArrayList<>();

    private static Profile arkadyProfile;
    private static Profile tarasProfile;

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
        arkadyProfile = new Profile();
        arkadyProfile.setId(13);
        arkadyProfile.setName("Arkady");
        arkadyProfile.setSurname("Baranok");
        arkadyProfile.setEmail("arkady@mail.ru");
        arkadyProfile.setBirthDate(new Date());
        arkadyProfile.setEducation("MIPT");
        tarasFriends.add(arkadyProfile);

        List<Profile> arkadyFriends = new ArrayList<>();
        tarasProfile = new Profile();
        tarasProfile.setId(11);
        tarasProfile.setName("Taras");
        tarasProfile.setSurname("Khakhulin");
        tarasProfile.setEmail("ataras@mail.ru");
        tarasProfile.setBirthDate(new Date());
        tarasProfile.setEducation("Caucasus mountains");
        arkadyFriends.add(tarasProfile);

        store.add(new DummyUser(
                11, "Taras", "Khakhulin", "taras@mail.ru", "taras", "Caucasus mountains", new Date(), tarasFriends));
        store.add(new DummyUser(
                12, "Master", "Yoda", "", "", "Jedi temple", new Date(), friends));
        store.add(new DummyUser(
                13, "Arkady", "Baranok", "arkady@mail.ru", "arkady", "MIPT", new Date(), arkadyFriends));

        courses.add(
                new Course(1, tarasProfile, new ArrayList<>(), "Basic Mathematics",
                        "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math "));
        courses.add(
                new Course(2, tarasProfile, new ArrayList<>(), "Advanced Philosophy",
                        "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math "));
        courses.add(
                new Course(3, arkadyProfile, new ArrayList<>(), "Web-design technologies",
                        "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math "));
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

    static Collection<Course> pullCourses() {
        return courses;
    }

    static void addNewCourse(Course course) {
        courses.add(course);
    }
}
