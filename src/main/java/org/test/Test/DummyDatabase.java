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



        Set<Lesson> lessons = new HashSet<>();
        Course course1 = new Course(1, tarasProfile, new HashSet<>(), "Basic Mathematics",
                "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math " +
                        "something about math something about math something about math ");
        Calendar calendar = new GregorianCalendar();
        Calendar calendar1 = new GregorianCalendar();
        calendar.set(2016, 11, 5, 13, 30);
        calendar1.set(2016, 11, 5, 15, 0);
        Lesson lesson1 = new Lesson("First lesson", course1, 4.5,
                new Date(calendar.getTimeInMillis()),
                new Date(calendar1.getTimeInMillis()));
        lesson1.setId(getNextLessonId());
        lessons.add(lesson1);
        calendar.set(2016, 11, 10, 18, 0);
        calendar1.set(2016, 11, 10, 20, 0);
        Lesson lesson2 = new Lesson(
                "Second lesson", course1, 6.7,
                new Date(calendar.getTimeInMillis()),
                new Date(calendar1.getTimeInMillis()));
        lesson2.setId(getNextLessonId());
        lesson2.getAssignedStudents().add(arkadyProfile);
        lessons.add(lesson2);
        course1.setLessons(lessons);
        courses.add(course1);


        courses.add(
                new Course(2, tarasProfile, new HashSet<>(), "Advanced Philosophy",
                        "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math " +
                                "something about math something about math something about math "));
        courses.add(
                new Course(3, arkadyProfile, new HashSet<>(), "Web-design technologies",
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

    private static int nextLessonId = 0;
    private static int nextCourseId = 0;

    private static int getNextLessonId() {
        return nextLessonId++;
    }

    private static int getNextCourseId() {
        return nextCourseId++;
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

    static Course addNewCourse(Course course) {
        course.setId(getNextCourseId());
        courses.add(course);
        return course;
    }

    static Lesson addNewLesson(Lesson lesson) {
        lesson.setId(getNextLessonId());
        return lesson;
    }

    static List<InboxMessage> pullInboxMessages(Profile profile) {
        List<InboxMessage> messages = new ArrayList<>();
        for (InboxMessage message: inboxMessages) {
            if(profile.equals(message.getSenderProfile())
                    || profile.equals(message.getReceiverProfile())) {
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

    static List<Lesson> pullAllLessons(Profile profile) {
        List<Lesson> lessons = new ArrayList<>();
        for(Course course: courses) {
            if(course.getTutorProfile().equals(profile)) {
                lessons.addAll(course.getLessons());
            } else {
                for (Lesson lesson : course.getLessons()) {
                    if (lesson.getAssignedStudents().contains(profile)) {
                        lessons.add(lesson);
                    }
                }
            }
        }
        return lessons;
    }

    static void assignProfileToLesson(Lesson lesson, Profile profile) {
        lesson.getAssignedStudents().add(profile);
    }
}
