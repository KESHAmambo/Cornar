package org.test.customcomponents.menupage.classpage;

import org.test.MyUI;
import org.test.controllers.menupage.classpage.TutorClassPageController;
import org.test.logic.Lesson;
import org.test.tamplets.menupage.classpage.TutorClassPage;

/**
 * Created by abara on 15.12.2016.
 */
public class TutorClassPageImpl extends TutorClassPage {
    private final TutorClassPageController controller;

    public TutorClassPageImpl(Lesson lesson, MyUI myUI) {
        controller = new TutorClassPageController(this);

        controller.connectToLesson(lesson);
        controller.createListenerForSendMessageButton();

        myUI.setTutorClassPageController(controller);
    }
}
