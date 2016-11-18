package org.test.customcomponents.menupage.profilepage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.test.controllers.menupage.profilepage.CoursesPageController;
import org.test.customcomponents.menupage.profilepage.coursespage.CourseBox;
import org.test.tamplets.menupage.profilepage.CoursesPage;

import java.util.Collection;

/**
 * Created by abara on 16.11.2016.
 */
public class CoursesPageImpl extends CoursesPage implements View {
    private final CoursesPageController controller;

    public CoursesPageImpl() {
        controller = new CoursesPageController(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        System.out.println("Entered Courses");
        mainLayout.removeAllComponents();
        Collection<CourseBox> courseBoxes = controller.getCourseBoxes();
        System.out.println(courseBoxes.size());
        for(CourseBox courseBox: courseBoxes) {
            mainLayout.addComponent(courseBox);
        }
    }
}
