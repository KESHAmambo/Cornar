package org.test.customcomponents.menupage.profilepage.coursespage;

import com.vaadin.ui.Window;
import org.test.controllers.menupage.profilepage.coursespage.AddCoursePanelController;
import org.test.tamplets.menupage.profilepage.coursespage.AddCoursePanel;

/**
 * Created by abara on 20.11.2016.
 */
public class AddCoursePanelImpl extends AddCoursePanel {
    private AddCoursePanelController controller;

    public AddCoursePanelImpl(Window addCourseWindow) {
        controller = new AddCoursePanelController(this, addCourseWindow);

        controller.createListenerForAddCourseButton();
    }
}
