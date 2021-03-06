package org.test.Test;

import org.test.logic.Course;
import org.test.logic.InboxMessage;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.io.File;
import java.util.*;

/**
 * Created by abara on 10.11.2016.
 */
public class DummyDatabase {
    private static Set<DummyUser> loginStore = new HashSet<DummyUser>();
    private static Set<Profile> profiles = new HashSet<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<InboxMessage> inboxMessages = new ArrayList<>();

    private static Profile yodaProfile;
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

        String tarasImagePath = "C:\\Users\\abara\\Desktop\\Projects\\Cornar\\src\\main\\webapp\\VAADIN\\themes\\mytheme\\images\\tarasImage.jpg";
//        String arkadyImagePath = "C:\\Users\\abara\\Desktop\\Projects\\Cornar\\src\\main\\webapp\\VAADIN\\themes\\mytheme\\images\\arkadyImage.jpg";
        String yodaImagePath = "C:\\Users\\abara\\Desktop\\Projects\\Cornar\\src\\main\\webapp\\VAADIN\\themes\\mytheme\\images\\yodaImage.jpg";

        List<Profile> tarasFriends = new ArrayList<>();
        List<Profile> yodaFriends = new ArrayList<>();
        List<Profile> arkadyFriends = new ArrayList<>();

        arkadyProfile = new Profile();
        arkadyProfile.setId(11);
        arkadyProfile.setName("Arkady");
        arkadyProfile.setSurname("Baranok");
        arkadyProfile.setEmail("arkady@mail.ru");
        arkadyProfile.setBirthDate(new Date());
        arkadyProfile.setEducation("MIPT");
//        arkadyProfile.setImageResource(new File(arkadyImagePath));
        arkadyProfile.setFriends(arkadyFriends);

        yodaProfile = new Profile();
        yodaProfile.setId(12);
        yodaProfile.setName("Master");
        yodaProfile.setSurname("Yoda");
        yodaProfile.setEmail("");
        yodaProfile.setBirthDate(new Date());
        yodaProfile.setEducation("Caucasus mountains");
        yodaProfile.setImageResource(new File(yodaImagePath));
        yodaProfile.setFriends(yodaFriends);

        tarasProfile = new Profile();
        tarasProfile.setId(13);
        tarasProfile.setName("Taras");
        tarasProfile.setSurname("Khakhulin");
        tarasProfile.setEmail("taras@mail.ru");
        tarasProfile.setBirthDate(new Date());
        tarasProfile.setEducation("Caucasus mountains");
        tarasProfile.setImageResource(new File(tarasImagePath));
        tarasProfile.setFriends(tarasFriends);

//        arkadyFriends.add(tarasProfile);
        arkadyFriends.add(yodaProfile);
//        tarasFriends.add(arkadyProfile);
        tarasFriends.add(yodaProfile);
        yodaFriends.add(arkadyProfile);
        yodaFriends.add(tarasProfile);
        yodaFriends.addAll(friends);

        profiles.add(arkadyProfile);
        profiles.add(tarasProfile);
        profiles.add(yodaProfile);

        loginStore.add(new DummyUser("arkady@mail.ru", "arkady"));
        loginStore.add(new DummyUser("taras@mail.ru", "taras"));
        loginStore.add(new DummyUser("", ""));  //Master Yoda


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
        Lesson lesson1 = new Lesson("First lesson", course1, (long) 4.5,
                new Date(calendar.getTimeInMillis()),
                new Date(calendar1.getTimeInMillis()));
        lesson1.setId(getNextLessonId());
        lessons.add(lesson1);
        calendar.set(2016, 11, 10, 18, 0);
        calendar1.set(2016, 11, 10, 20, 0);
        Lesson lesson2 = new Lesson(
                "Second lesson", course1, (long) 6.7,
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

    private static int nextLessonId = 2;
    private static int nextCourseId = 3;

    private static int getNextLessonId() {
        return nextLessonId++;
    }

    private static int getNextCourseId() {
        return nextCourseId++;
    }

    static boolean doesUserExist(String email, String password) {
        DummyUser user = new DummyUser(email, password);
        return loginStore.contains(user);
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
        lesson.getCourse().getLessons().add(lesson);
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
        for(Profile profile: profiles) {
            if(profile.getEmail().equals(email)) {
                return profile;
            }
        }
        return null;
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
//        lesson.getAssignedStudents().add(profile);
    }

    public static List<Profile> getAllUsersWithNameLike(String firstName) {
        List<Profile> result = new ArrayList<>();
        for(Profile profile: profiles) {
            if(profile.getName().contains(firstName)) {
                result.add(profile);
            }
        }
        return result;
    }

    public static void addFriend(int userId, String friendEmail) {
        Profile newFriend = null;
        for(Profile profile: profiles) {
            if(profile.getEmail().equals(friendEmail)) {
                newFriend = profile;
                break;
            }
        }
        for(Profile profile: profiles) {
            if(profile.getId() == userId) {
                profile.getFriends().add(newFriend);
            }
        }
    }
}
