package org.test.Test;

import org.test.logic.Course;
import org.test.logic.InboxMessage;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.util.*;

/**
 * Created by abara on 10.11.2016.
 */
public class DummyDatabase {
    private static Set<DummyUser> store = new HashSet<DummyUser>();
    private static List<Course> courses = new ArrayList<>();
    private static List<InboxMessage> inboxMessages = new ArrayList<>();

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
        tarasProfile.setEmail("taras@mail.ru");
        tarasProfile.setBirthDate(new Date());
        tarasProfile.setEducation("Caucasus mountains");
        arkadyFriends.add(tarasProfile);

        store.add(new DummyUser(
                11, "Taras", "Khakhulin", "taras@mail.ru", "taras", "Caucasus mountains", new Date(), tarasFriends));
        store.add(new DummyUser(
                12, "Master", "Yoda", "", "", "Jedi temple", new Date(), friends));
        store.add(new DummyUser(
                13, "Arkady", "Baranok", "arkady@mail.ru", "arkady", "MIPT", new Date(), arkadyFriends));



        List<Lesson> lessons = new ArrayList<>();
        Course course1 = new Course(1, tarasProfile, new ArrayList<>(), "Basic Mathematics",
                "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math ");
        Calendar calendar = new GregorianCalendar();
        calendar.set(2016, 4, 5);
        Lesson lesson1 = new Lesson("First lesson", course1, 4.5, new Date(calendar.getTimeInMillis()));
        lessons.add(lesson1);
        calendar.set(2016, 4, 10);
        Lesson lesson2 = new Lesson("Second lesson", course1, 5.5, new Date(calendar.getTimeInMillis()));
        lesson2.getAssignedStudents().add(arkadyProfile);
        lessons.add(lesson2);
        course1.setLessons(lessons);
        courses.add(course1);


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

        calendar.set(2016, 3, 5);
        InboxMessage message1 = new InboxMessage(
                tarasProfile, arkadyProfile, new Date(calendar.getTimeInMillis()),
                "Hibernate problems in project", "Some problems with hibernate!", false);
        calendar.set(2016, 3, 21);
        InboxMessage message2 = new InboxMessage(
                tarasProfile, arkadyProfile, new Date(calendar.getTimeInMillis()),
                "Don't know how to do something", "Many unsolved problems!", false);
        calendar.set(2016, 3, 23);
        InboxMessage message3 = new InboxMessage(
                arkadyProfile, tarasProfile, new Date(calendar.getTimeInMillis()),
                "Solution suggestion", "Google please!", false);
        inboxMessages.add(message3);
        inboxMessages.add(message1);
        inboxMessages.add(message2);
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

    static void addNewLesson(Lesson lesson) {
        Course course = lesson.getCourse();
        course.getLessons().add(lesson);
    }

    static List<InboxMessage> pullInboxMessages(int id) {
        List<InboxMessage> messages = new ArrayList<>();
        for (InboxMessage message: inboxMessages) {
            if(id == message.getSenderProfile().getId() || id == message.getReceiverProfile().getId()) {
                messages.add(message);
            }
        }
        return messages;
    }

    static Profile getProfile(String email) {
        if (email.equals(arkadyProfile.getEmail())) {
            return arkadyProfile;
        } else if(email.equals((tarasProfile.getEmail()))) {
            return tarasProfile;
        } else {
            return null;
        }
    }

    static void storeInboxMessage(InboxMessage message) {
        inboxMessages.add(message);
    }
}
