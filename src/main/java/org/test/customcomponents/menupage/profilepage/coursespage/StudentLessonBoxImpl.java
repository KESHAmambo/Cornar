package org.test.customcomponents.menupage.profilepage.coursespage;

import com.vaadin.server.FontAwesome;
import org.test.logic.Lesson;
import org.test.logic.Profile;
import org.test.paymentservice.PaymentService;
import org.test.tamplets.menupage.profilepage.coursespage.StudentLessonBox;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by abara on 21.11.2016.
 */
public class StudentLessonBoxImpl extends StudentLessonBox {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    private final Lesson lesson;

    StudentLessonBoxImpl(Lesson lesson) {
        this.lesson = lesson;

        fulfillLabels();
        createListenerForPayButton();
    }

    private void fulfillLabels() {
        lessonNameLabel.setValue(lesson.getName());
        dateLabel.setValue(dateFormat.format(
                lesson.getStartDate()));
        timeLabel.setValue(timeFormat.format(
                lesson.getStartDate()) + " - " + timeFormat.format(lesson.getEndDate()));
        costLabel.setValue(lesson.getCost().toString());
        customizePayButton();
    }

    private void customizePayButton() {
        boolean alreadyAssigned = checkAssignment();
        System.out.println(checkAssignment() + "checking for paying");
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
        payButton.addClickListener(e -> {
            boolean wasPaymentAccepted = PaymentService.payForLesson(
                    Profile.getCurrentProfile(), lesson);
            if(wasPaymentAccepted) {
                payButton.setCaption("");
                payButton.setIcon(FontAwesome.CHECK);
                payButton.setReadOnly(true);
            }
        });
    }
}
