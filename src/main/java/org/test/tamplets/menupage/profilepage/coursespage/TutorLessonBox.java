package org.test.tamplets.menupage.profilepage.coursespage;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class TutorLessonBox extends CssLayout {
    protected VerticalLayout mainLayout;
    protected Label lessonNameLabel;
    protected Label dateLabel;
    protected Label timeLabel;
    protected Label costLabel;
    protected Button assignsButton;

    public TutorLessonBox() {
        Design.read(this);
    }
}
