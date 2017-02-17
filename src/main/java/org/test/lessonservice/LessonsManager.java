package org.test.lessonservice;

import com.vaadin.server.VaadinSession;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by abara on 15.12.2016.
 */
public class LessonsManager {
    private static Map<Integer, LessonConnection> lessonConnections = new TreeMap<>();

    private LessonsManager() {

    }

    public static LessonConnection connectToLessonAsStudent(Lesson lesson) {
        Integer lessonId = lesson.getId();
        System.out.println(" NOT IF " +lessonId);
        LessonConnection connection;
        if(lessonConnections.containsKey(lessonId)) {
            connection = lessonConnections.get(lessonId);
            System.out.println("IF: " + connection);
            connection.addStudentListener(
                    Profile.getCurrentProfile(), VaadinSession.getCurrent());
        } else {
            connection = new LessonConnection(lesson);
            System.out.println("ELSE: " + connection);
            connection.addStudentListener(
                    Profile.getCurrentProfile(), VaadinSession.getCurrent());
            lessonConnections.put(lessonId, connection);
        }
        return connection;
    }

    public static LessonConnection connectToLessonAsTutor(Lesson lesson) {
        Integer lessonId = lesson.getId();
        LessonConnection connection;
        if(lessonConnections.containsKey(lessonId)) {
            connection = lessonConnections.get(lessonId);
            connection.setTutorListener(Profile.getCurrentProfile(), VaadinSession.getCurrent());

        } else {
            connection = new LessonConnection(lesson);
            connection.setTutorListener(Profile.getCurrentProfile(), VaadinSession.getCurrent());
            lessonConnections.put(lessonId, connection);
        }
        return connection;
    }
}
