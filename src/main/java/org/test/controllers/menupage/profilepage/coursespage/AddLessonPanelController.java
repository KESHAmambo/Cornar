package org.test.controllers.menupage.profilepage.coursespage;

import com.vaadin.ui.*;
import org.test.customcomponents.menupage.profilepage.coursespage.AddLessonPanelImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Course;
import org.test.logic.Lesson;
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
        DateField startDateField = addLessonPanel.getStartDateField();
        DateField endDateField = addLessonPanel.getEndDateField();

        addLessonButton.addClickListener(event -> {
            String lessonName = lessonNameTextField.getValue();
            String costStr = costTextField.getValue();
            Date startDate = startDateField.getValue();
            Date endDate = endDateField.getValue();

            if("".equals(lessonName)) {
                Notification.show("Please, enter lesson name.", Notification.Type.WARNING_MESSAGE);
            } else if(!isCostValid(costStr)) {
                Notification.show("Cost format must be '0.00'.", Notification.Type.WARNING_MESSAGE);
            } else if (startDate == null || endDate == null) {
                Notification.show("Please, set lesson date and time.", Notification.Type.WARNING_MESSAGE);
            } else if(startDate.compareTo(new Date()) < 0
                    || endDate.compareTo(new Date()) < 0
                    || endDate.compareTo(startDate) < 0) {
                Notification.show("Please, set valid date.", Notification.Type.WARNING_MESSAGE);
            } else {
                Lesson newLesson = DatabaseManager.addNewLesson(
                        course, lessonName, Double.parseDouble(costStr), startDate, endDate);
                course.getLessons().add(newLesson);
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

