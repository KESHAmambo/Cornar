package org.test.customcomponents.menupage.profilepage.coursespage;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.logic.Course;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.coursespage.TutorCourseBox;

/**
 * Created by abara on 18.11.2016.
 */
public class TutorCourseBoxImpl extends TutorCourseBox implements CourseBox {
    private final Course course;
    private final Window addLessonWindow;
    private final AddLessonPanelImpl addLessonPanel;

    public TutorCourseBoxImpl(Course course) {
        this.course = course;

        addLessonWindow = new Window();
        addLessonPanel = new AddLessonPanelImpl(course, addLessonWindow);

        fulfillLabels();
        customizeAddLessonWindow();
        createListenerForAddLessonButton();
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
}
