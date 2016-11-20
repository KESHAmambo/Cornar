package org.test.customcomponents.menupage.profilepage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.controllers.menupage.profilepage.CoursesPageController;
import org.test.customcomponents.menupage.profilepage.coursespage.AddCoursePanelImpl;
import org.test.customcomponents.menupage.profilepage.coursespage.CourseBox;
import org.test.tamplets.menupage.profilepage.CoursesPage;
import org.test.tamplets.menupage.profilepage.coursespage.AddCoursePanel;

import java.util.Collection;

/**
 * Created by abara on 16.11.2016.
 */
public class CoursesPageImpl extends CoursesPage implements View {
    private final CoursesPageController controller;
    private final Window addCourseWindow;
    private final AddCoursePanelImpl addCoursePanel;

    public CoursesPageImpl() {
        controller = new CoursesPageController(this);

        addCourseWindow = new Window();
        addCoursePanel = new AddCoursePanelImpl(addCourseWindow);

        customizeAddCourseWindow();
        createListenerForAddCourseButton();
    }

    private void customizeAddCourseWindow() {
        addCourseWindow.setContent(addCoursePanel);
        addCourseWindow.center();
        addCourseWindow.setModal(true);
        addCourseWindow.setWidth("30%");
        addCourseWindow.setHeight("70%");
    }

    private void createListenerForAddCourseButton() {
        addCourseButton.addClickListener(e -> {
            UI.getCurrent().addWindow(addCourseWindow);
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        forBoxesLayout.removeAllComponents();
        Collection<CourseBox> courseBoxes = controller.getCourseBoxes();
        System.out.println(courseBoxes.size());
        for(CourseBox courseBox: courseBoxes) {
            forBoxesLayout.addComponent(courseBox);
        }
    }
}
