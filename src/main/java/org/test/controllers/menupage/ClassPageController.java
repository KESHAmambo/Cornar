package org.test.controllers.menupage;

import org.test.customcomponents.menupage.ClassPageImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.util.Date;
import java.util.List;

/**
 * Created by abara on 15.12.2016.
 */
public class ClassPageController {
    private final ClassPageImpl classPage;

    public ClassPageController(ClassPageImpl classPage) {
        this.classPage = classPage;
    }

    public void enterLesson() {
        Profile profile = Profile.getCurrentProfile();
        Lesson lesson = getCurrentLesson(profile);
        if (lesson == null) {
            classPage.getTempLabel().setValue("Nothing to show now :(");
        } else {
            classPage.getTempLabel().setValue(lesson.getName());
        }
    }

    private Lesson getCurrentLesson(Profile profile) {
        List<Lesson> lessons = DatabaseManager.pullAllUserLessons(profile);
        Date currentDate = new Date();
        for (Lesson lesson: lessons) {
            if(currentDate.compareTo(lesson.getStartDate()) >= 0
                    && currentDate.compareTo(lesson.getEndDate()) <= 0) {
                return lesson;
            }
        }
        return null;
    }
}
