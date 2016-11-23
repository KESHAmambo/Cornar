package org.test.customcomponents.menupage.profilepage.coursespage;

import com.vaadin.server.FontAwesome;
import org.test.logic.Lesson;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.coursespage.StudentLessonBox;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by abara on 21.11.2016.
 */
public class StudentLessonBoxImpl extends StudentLessonBox {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy HH:mm", Locale.ENGLISH);

    private final Lesson lesson;

    StudentLessonBoxImpl(Lesson lesson) {
        this.lesson = lesson;

        fulfillLabels();
        createListenerForPayButton();
    }

    private void fulfillLabels() {
        lessonNameLabel.setValue(lesson.getLessonName());
        dateLabel.setValue(dateFormat.format(lesson.getDate()));
        costLabel.setValue(String.format("%.2f$", lesson.getCost()));
        customizePayButton();
    }

    private void customizePayButton() {
        boolean alreadyAssigned = checkAssignment();
        if(alreadyAssigned) {
            payButton.setIcon(FontAwesome.CHECK);
            payButton.setReadOnly(true);
        } else {
            payButton.setCaption("Pay");
        }
    }

    private boolean checkAssignment() {
        Profile profile = Profile.getCurrentProfile();
        List<Profile> assignedStudents = lesson.getAssignedStudents();
        return assignedStudents.contains(profile);
    }

    private void createListenerForPayButton() {
        //TODO
    }
}
