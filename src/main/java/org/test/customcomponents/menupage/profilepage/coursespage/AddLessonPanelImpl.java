package org.test.customcomponents.menupage.profilepage.coursespage;

import com.vaadin.ui.Window;
import org.test.controllers.menupage.profilepage.coursespage.AddLessonPanelController;
import org.test.logic.Course;
import org.test.tamplets.menupage.profilepage.coursespage.AddLessonPanel;

/**
 * Created by abara on 20.11.2016.
 */
public class AddLessonPanelImpl extends AddLessonPanel {
    private final AddLessonPanelController controller;

    public AddLessonPanelImpl(Course course, Window addLessonWindow) {
        this.controller = new AddLessonPanelController(this, course, addLessonWindow);

        controller.createListenerForAddLessonButton();
    }
}
