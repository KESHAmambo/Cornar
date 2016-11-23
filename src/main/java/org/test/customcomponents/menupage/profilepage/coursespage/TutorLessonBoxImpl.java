package org.test.customcomponents.menupage.profilepage.coursespage;

import org.test.logic.Lesson;
import org.test.tamplets.menupage.profilepage.coursespage.TutorLessonBox;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by abara on 21.11.2016.
 */
public class TutorLessonBoxImpl extends TutorLessonBox {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy HH:mm", Locale.ENGLISH);

    private final Lesson lesson;

    public TutorLessonBoxImpl(Lesson lesson) {
        this.lesson = lesson;

        fulfillLabels();
        createListenerForAssignsButton();
    }

    private void fulfillLabels() {
        lessonNameLabel.setValue(lesson.getLessonName());
        dateLabel.setValue(dateFormat.format(lesson.getDate()));
        costLabel.setValue(String.format("%.2f$", lesson.getCost()));
    }

    private void createListenerForAssignsButton() {
        //TODO
    }
}
