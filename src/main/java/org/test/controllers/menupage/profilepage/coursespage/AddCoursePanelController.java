package org.test.controllers.menupage.profilepage.coursespage;

import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.test.customcomponents.menupage.profilepage.coursespage.AddCoursePanelImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Course;
import org.test.utils.UIHelper;

/**
 * Created by abara on 20.11.2016.
 */
public class AddCoursePanelController {
    private final AddCoursePanelImpl addCoursePanel;
    private final Window addCourseWindow;

    public AddCoursePanelController(
            AddCoursePanelImpl addCoursePanel, Window addCourseWindow) {
        this.addCoursePanel = addCoursePanel;
        this.addCourseWindow = addCourseWindow;
    }

    public void createListenerForAddCourseButton() {
        Button addCourseButton = addCoursePanel.getAddCourseButton();
        TextField courseNameTextField = addCoursePanel.getCourseNameTextField();
        TextArea descriptionTextArea = addCoursePanel.getDescriptionTextArea();

        addCourseButton.addClickListener(e -> {
            String courseName = courseNameTextField.getValue();
            String description = descriptionTextArea.getValue();

            if("".equals(courseName) || "".equals(description)) {
                Notification.show("Fields must not be empty!", Notification.Type.WARNING_MESSAGE);
            } else {
                DatabaseManager.addNewCourse(courseName, description);
                courseNameTextField.setValue("");
                descriptionTextArea.setValue("");
                UI.getCurrent().removeWindow(addCourseWindow);
                UIHelper.showSuccessNotification(
                        "New course was successfully added!", "addedCourseNotification");
            }
        });
    }
}
