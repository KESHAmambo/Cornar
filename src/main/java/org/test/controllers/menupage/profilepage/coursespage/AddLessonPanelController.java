package org.test.controllers.menupage.profilepage.coursespage;

import com.vaadin.ui.*;
import org.test.customcomponents.menupage.profilepage.coursespage.AddLessonPanelImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Course;
import org.test.utils.UIHelper;

import java.util.Date;

/**
 * Created by abara on 20.11.2016.
 */
public class AddLessonPanelController {
    private final AddLessonPanelImpl addLessonPanel;
    private final Course course;
    private final Window addLessonWindow;

    public AddLessonPanelController(
            AddLessonPanelImpl addLessonPanel, Course course, Window addLessonWindow) {
        this.addLessonPanel = addLessonPanel;
        this.course = course;
        this.addLessonWindow = addLessonWindow;
    }

    public void createListenerForAddLessonButton() {
        Button addLessonButton = addLessonPanel.getAddLessonButton();
        TextField lessonNameTextField = addLessonPanel.getLessonNameTextField();
        TextField costTextField = addLessonPanel.getCostTextField();
        DateField dateField = addLessonPanel.getDateField();

        addLessonButton.addClickListener(event -> {
            String lessonName = lessonNameTextField.getValue();
            String costStr = costTextField.getValue();
            Date date = dateField.getValue();

            if("".equals(lessonName)) {
                Notification.show("Please, enter lesson name.", Notification.Type.WARNING_MESSAGE);
            } else if(!isCostValid(costStr)) {
                Notification.show("Cost format must be '0.00'.", Notification.Type.WARNING_MESSAGE);
            } else if (date == null) {
                Notification.show("Please, set lesson date and time.", Notification.Type.WARNING_MESSAGE);
            } else if(date.compareTo(new Date()) < 0) {
                Notification.show("Please, set valid date.", Notification.Type.WARNING_MESSAGE);
            } else {
                DatabaseManager.addNewLesson(course, lessonName, Double.parseDouble(costStr), date);
                cleanPanelElements(lessonNameTextField, costTextField);
                UIHelper.showSuccessNotification(
                        "New lesson was successfully added!",
                        "addedCourseNotification");
            }
        });
    }

    private void cleanPanelElements(TextField lessonNameTextField, TextField costTextField) {
        lessonNameTextField.setValue("");
        costTextField.setValue("");
        UI.getCurrent().removeWindow(addLessonWindow);
    }

    private boolean isCostValid(String costStr) {
        try {
            Double.parseDouble(costStr);
            return true;
        } catch (NumberFormatException ignore) {}
        return false;
    }
}

