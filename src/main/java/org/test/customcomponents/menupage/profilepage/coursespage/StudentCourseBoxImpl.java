package org.test.customcomponents.menupage.profilepage.coursespage;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import org.test.logic.Course;
import org.test.logic.Lesson;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.coursespage.StudentCourseBox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abara on 18.11.2016.
 */
public class StudentCourseBoxImpl extends StudentCourseBox implements CourseBox {
    private final Course course;
    private final List<StudentLessonBoxImpl> lessonBoxes = new ArrayList<>();

    public StudentCourseBoxImpl(Course course) {
        this.course = course;

        fulfillLabels();
        createListenerForShowLessonsButton();
    }

    public Course getCourse() {
        return course;
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
            StudentLessonBoxImpl lessonBox = new StudentLessonBoxImpl(lesson);
            lessonBoxes.add(lessonBox);
            mainLayout.addComponent(lessonBox);
        }
        showLessonsButton.setCaption("Hide Lessons");
    }

    private List<Lesson> sortLessons() {
        List<Lesson> lessons = new ArrayList<>(course.getLessons());
        lessons.sort((Lesson l1, Lesson l2) -> {
            Date lessonDate1 = l1.getStartDate();
            Date lessonDate2 = l2.getStartDate();
            return lessonDate1.compareTo(lessonDate2);
        });
        return lessons;
    }

    private void hideLessons() {
        for (StudentLessonBoxImpl lessonBox: lessonBoxes) {
            mainLayout.removeComponent(lessonBox);
        }
        lessonBoxes.clear();
        showLessonsButton.setCaption("Show Lessons");
    }

    private void fulfillLabels() {
        courseNameLabel.setValue(course.getName());
        Profile tutorProfile = course.getTutorProfile();
        tutorNameLabel.setValue(tutorProfile.getName() + " " + tutorProfile.getSurname());
        descriptionLabel.setValue(course.getDescription());
    }
}
