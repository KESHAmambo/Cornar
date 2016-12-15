package org.test.controllers.menupage.classpage;

import com.vaadin.ui.RichTextArea;
import org.test.customcomponents.menupage.classpage.TutorClassPageImpl;
import org.test.lessonservice.LessonConnection;
import org.test.lessonservice.LessonsManager;
import org.test.logic.Lesson;

/**
 * Created by abara on 15.12.2016.
 */
public class TutorClassPageController {
    private final TutorClassPageImpl tutorClassPage;
    private LessonConnection connection;

    public TutorClassPageController(TutorClassPageImpl tutorClassPage) {
        this.tutorClassPage = tutorClassPage;
    }

    public void connectToLesson(Lesson lesson) {
        connection = LessonsManager.connectToLessonAsTutor(lesson);
    }

    public void createListenerForSendMessageButton() {
        RichTextArea richTextArea = tutorClassPage.getRichTextArea();
        tutorClassPage.getSendMessageButton().addClickListener(e -> {
            String text = richTextArea.getValue();
            if(text != null && !text.equals("")) {
                richTextArea.setValue("");
                connection.sendBoardMessage(text);
            }
        });
    }
}
