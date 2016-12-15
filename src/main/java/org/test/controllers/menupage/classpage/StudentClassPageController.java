package org.test.controllers.menupage.classpage;

import org.test.customcomponents.menupage.classpage.ClassBoardMessageImpl;
import org.test.customcomponents.menupage.classpage.StudentClassPageImpl;
import org.test.lessonservice.LessonConnection;
import org.test.lessonservice.LessonsManager;
import org.test.logic.Lesson;

/**
 * Created by abara on 15.12.2016.
 */
public class StudentClassPageController {
    private final StudentClassPageImpl classPage;
    private LessonConnection connection;

    public StudentClassPageController(StudentClassPageImpl classPage) {
        this.classPage = classPage;
    }

    public void connectToLesson(Lesson lesson) {
        connection = LessonsManager.connectToLessonAsStudent(lesson);
    }

    public void addClassBoardMessage(ClassBoardMessageImpl classBoardMessage) {
        classPage.getMainLayout().addComponent(classBoardMessage);
    }
}
