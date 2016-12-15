package org.test.customcomponents.menupage.classpage;

import org.test.MyUI;
import org.test.controllers.menupage.classpage.StudentClassPageController;
import org.test.logic.Lesson;
import org.test.tamplets.menupage.classpage.StudentClassPage;

/**
 * Created by abara on 15.12.2016.
 */
public class StudentClassPageImpl extends StudentClassPage {
    private final StudentClassPageController controller;

    public StudentClassPageImpl(Lesson lesson, MyUI myUI) {
        controller = new StudentClassPageController(this);

        controller.connectToLesson(lesson);

        myUI.setStudentClassPageController(controller);
    }
}
