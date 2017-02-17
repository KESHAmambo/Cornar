package org.test.controllers.menupage;

import com.vaadin.ui.VerticalLayout;
import org.test.MyUI;
import org.test.customcomponents.menupage.ClassPageImpl;
import org.test.customcomponents.menupage.classpage.StudentClassPageImpl;
import org.test.customcomponents.menupage.classpage.TutorClassPageImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Lesson;
import org.test.logic.Profile;
import org.test.utils.UIHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by abara on 15.12.2016.
 */
public class ClassPageController {
    private final DateFormat dateFormat = new SimpleDateFormat("dd MMMM");
    private final DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private final ClassPageImpl classPage;

    public ClassPageController(ClassPageImpl classPage) {
        this.classPage = classPage;
    }

    public void tryEnterLesson(MyUI myUI) {
        Profile profile = Profile.getCurrentProfile();
        Date currentDate = new Date();
        Lesson nearestLesson = getNearestLesson(profile, currentDate);
        if(nearestLesson == null) {
            classPage.getNoLessonLabel().setValue("You have no appointed lessons");
        } else if (currentDate.compareTo(nearestLesson.getStartDate()) >= 0) {
            enterLesson(nearestLesson, myUI);
        } else {
            showNearestLessonDate(nearestLesson);
        }
    }

    private void enterLesson(Lesson lesson, MyUI myUI) {
        VerticalLayout mainLayout = classPage.getMainLayout();
        mainLayout.removeAllComponents();
        if(Profile.getCurrentProfile().equals(lesson.getCourse().getTutorProfile())) {
            mainLayout.addComponent(new TutorClassPageImpl(lesson, myUI));
        } else {
            mainLayout.addComponent(new StudentClassPageImpl(lesson, myUI));
        }
        UIHelper.showSuccessNotification("You've entered lesson '" +
                lesson.getName() + "'", "addedCourseNotification");
    }

    private void showNearestLessonDate(Lesson nearestLesson) {
        classPage.getNoLessonLabel().setValue("Relax! Your next lesson is on");
        Date nearestLessonStartDate = nearestLesson.getStartDate();
        classPage.getNextLessonTimeLabel().setValue(
                dateFormat.format(nearestLessonStartDate) +
                        " at " +
                        timeFormat.format(nearestLessonStartDate));
    }

    private Lesson getNearestLesson(Profile profile, Date currentDate) {
        List<Lesson> lessons = DatabaseManager.pullAllUserLessons(profile);
        Lesson nearestLesson = null;
        for (Lesson lesson: lessons) {
            if(currentDate.compareTo(lesson.getEndDate()) <= 0) {
                if (nearestLesson == null){
                    nearestLesson = lesson;
                }

                if (currentDate.compareTo(lesson.getStartDate()) >= 0
                        && currentDate.compareTo(lesson.getEndDate()) <= 0) {
                    return lesson;
                } else if (nearestLesson.getStartDate().compareTo(lesson.getStartDate()) >= 0) {
                    nearestLesson = lesson;
                }
            }
        }
        return nearestLesson;
    }
}
