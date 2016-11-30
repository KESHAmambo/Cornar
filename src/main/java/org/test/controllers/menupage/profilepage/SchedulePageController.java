package org.test.controllers.menupage.profilepage;

import com.vaadin.ui.Calendar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import org.test.customcomponents.menupage.profilepage.SchedulePageImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Lesson;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.SchedulePage;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by abara on 27.11.2016.
 */
public class SchedulePageController {
    private final SchedulePageImpl schedulePage;

    public SchedulePageController(SchedulePageImpl schedulePage) {
        this.schedulePage = schedulePage;
    }

    public void updateSchedule() {
        VerticalLayout mainLayout = schedulePage.getMainLayout();
        mainLayout.removeAllComponents();
        Calendar calendar = createCalendar();
        fulfillCalendar(calendar);
        mainLayout.addComponent(calendar);
    }

    private Calendar createCalendar() {
        Calendar calendar = new Calendar();
        calendar.setLocale(Locale.ENGLISH);
        calendar.setHeight("600px");
        calendar.setWidth("100%");
        return calendar;
    }

    private void fulfillCalendar(Calendar calendar) {
        List<Lesson> lessons = DatabaseManager.pullAllUserLessons(Profile.getCurrentProfile());
        for(Lesson lesson: lessons) {
            BasicEvent lessonEvent = createLessonEvent(lesson);
            calendar.addEvent(lessonEvent);
        }
    }

    private BasicEvent createLessonEvent(Lesson lesson) {
        Date startDate = lesson.getStartDate();
        Date endDate = lesson.getEndDate();
        int duration = (int) (endDate.getTime() - startDate.getTime()) / 60000;

        String lessonDescription = String.format(
                "%s / %s (%d minutes)",
                lesson.getCourse().getName(),
                lesson.getName(),
                duration);

        BasicEvent lessonEvent = new BasicEvent(
                lessonDescription, "",
                startDate, endDate);
        addStyleForLessonEvent(lessonEvent, lesson);

        return lessonEvent;
    }

    private void addStyleForLessonEvent(BasicEvent lessonEvent, Lesson lesson) {
        if(lesson.getCourse().getTutorProfile().equals(Profile.getCurrentProfile())) {
            lessonEvent.setStyleName("tutorLesson");
        } else {
            lessonEvent.setStyleName("studentLesson");
        }
    }
}
