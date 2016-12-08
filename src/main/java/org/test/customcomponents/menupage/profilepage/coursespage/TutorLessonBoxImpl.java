package org.test.customcomponents.menupage.profilepage.coursespage;

import org.test.logic.Lesson;
import org.test.tamplets.menupage.profilepage.coursespage.TutorLessonBox;

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
        //TODO
    }
}
