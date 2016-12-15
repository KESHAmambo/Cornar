package org.test.lessonservice;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.test.customcomponents.menupage.classpage.ClassBoardMessageImpl;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by abara on 15.12.2016.
 */
public class LessonConnection {
    private Lesson lesson;
    private Listener tutorListener;
    private List<Listener> studentListeners = new LinkedList<>();

    LessonConnection(Lesson lesson) {
        this.lesson = lesson;
    }

    public void setTutorListener(Profile profile, VaadinSession session) {
        this.tutorListener = new Listener(profile, session);
    }

    void addStudentListener(Profile profile, VaadinSession session) {
        studentListeners.add(new Listener(profile, session));
    }

    public void sendBoardMessage(String text) {
        ClassBoardMessageImpl classBoardMessage = new ClassBoardMessageImpl(text);
        for(Listener listener: studentListeners) {
            for(UI ui: listener.getSession().getUIs()) {
                ((LessonConnectionListener) ui).receiveClassBoardMessage(classBoardMessage);
            }
        }
    }

    private class Listener {
        Profile profile;
        VaadinSession session;

        Listener(Profile profile, VaadinSession session) {
            this.profile = profile;
            this.session = session;
        }

        public Profile getProfile() {
            return profile;
        }

        public VaadinSession getSession() {
            return session;
        }
    }

    public interface LessonConnectionListener {
        void receiveClassBoardMessage(ClassBoardMessageImpl classBoardMessage);
    }
}
