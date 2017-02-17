package org.test.customcomponents.menupage.profilepage.coursespage;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxComposePanelImpl;
import org.test.logic.Lesson;
import org.test.tamplets.menupage.profilepage.coursespage.TutorLessonBox;
import org.test.utils.UIHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by abara on 21.11.2016.
 */
public class TutorLessonBoxImpl extends TutorLessonBox {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    private final Lesson lesson;

    public TutorLessonBoxImpl(Lesson lesson) {
        this.lesson = lesson;

        fulfillLabels();
        createListenerForAssignsButton();
    }

    private void fulfillLabels() {
        lessonNameLabel.setValue(lesson.getName());
        dateLabel.setValue(dateFormat.format(
                lesson.getStartDate()));
        timeLabel.setValue(timeFormat.format(
                lesson.getStartDate()) + " - " + timeFormat.format(lesson.getEndDate()));
        costLabel.setValue(lesson.getCost().toString());
    }

    private void createListenerForAssignsButton() {
        assignsButton.addClickListener(e -> {
            if(lesson.getAssignedStudents().size() > 0) {
                Window composeWindow = createComposeWindow();
                UI.getCurrent().addWindow(composeWindow);
            } else {
                UIHelper.showWarningNotification("No assigns have been added yet.");
            }
        });
    }

    private Window createComposeWindow() {
        Window window = new Window();
        window.setContent(new AssignmentsPanelImpl(lesson.getAssignedStudents()));
        window.center();
        window.setModal(true);
        window.setWidth("60%");
        window.setHeight("90%");
        return window;
    }
}
