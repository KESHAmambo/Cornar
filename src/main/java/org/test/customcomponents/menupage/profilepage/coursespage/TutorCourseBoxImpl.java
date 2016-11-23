package org.test.customcomponents.menupage.profilepage.coursespage;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.logic.Course;
import org.test.logic.Lesson;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.coursespage.TutorCourseBox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abara on 18.11.2016.
 */
public class TutorCourseBoxImpl extends TutorCourseBox implements CourseBox {
    private final Course course;
    private final Window addLessonWindow;
    private final AddLessonPanelImpl addLessonPanel;
    private final List<TutorLessonBoxImpl> lessonBoxes = new ArrayList<>();

    public TutorCourseBoxImpl(Course course) {
        this.course = course;

        addLessonWindow = new Window();
        addLessonPanel = new AddLessonPanelImpl(course, addLessonWindow);

        fulfillLabels();
        customizeAddLessonWindow();
        createListenerForAddLessonButton();
        createListenerForShowLessonsButton();
    }

    private void customizeAddLessonWindow() {
        addLessonWindow.setContent(addLessonPanel);
        addLessonWindow.center();
        addLessonWindow.setModal(true);
        addLessonWindow.setWidth("30%");
        addLessonWindow.setHeight("40%");
    }

    public Course getCourse() {
        return course;
    }

    private void fulfillLabels() {
        courseNameLabel.setValue(course.getCourseName());
        Profile tutorProfile = course.getTutorProfile();
        tutorNameLabel.setValue(tutorProfile.getName() + " " + tutorProfile.getSurname());
        descriptionLabel.setValue(course.getDescription());
    }

    private void createListenerForAddLessonButton() {
        addLessonButton.addClickListener(e -> {
            UI.getCurrent().addWindow(addLessonWindow);
        });
    }

    private void createListenerForShowLessonsButton() {
        final boolean[] isOpening = {true};
        showLessonsButton.addClickListener(e -> {
            if(course.getLessons().size() == 0) {
                showNoLessonsNotification();
            } else if(isOpening[0]) {
                showLessons();
                isOpening[0] = false;
            } else {
                hideLessons();
                isOpening[0] = true;
            }
        });
    }

    private void showNoLessonsNotification() {
        Notification notification = new Notification(
                "No lessons have been added yet.", Notification.Type.WARNING_MESSAGE);
        notification.setDelayMsec(3000);
        notification.show(Page.getCurrent());
    }

    private void showLessons() {
        List<Lesson> lessons = sortLessons();
        for (Lesson lesson : lessons) {
            TutorLessonBoxImpl lessonBox = new TutorLessonBoxImpl(lesson);
            lessonBoxes.add(lessonBox);
            mainLayout.addComponent(lessonBox);
        }
        showLessonsButton.setCaption("Hide Lessons");
    }

    private List<Lesson> sortLessons() {
        List<Lesson> lessons = course.getLessons();
        lessons.sort((Lesson l1, Lesson l2) -> {
            Date lessonDate1 = l1.getDate();
            Date lessonDate2 = l2.getDate();
            return lessonDate1.compareTo(lessonDate2);
        });
        return lessons;
    }

    private void hideLessons() {
        for (TutorLessonBoxImpl lessonBox: lessonBoxes) {
            mainLayout.removeComponent(lessonBox);
        }
        lessonBoxes.clear();
        showLessonsButton.setCaption("Show Lessons");
    }
}
