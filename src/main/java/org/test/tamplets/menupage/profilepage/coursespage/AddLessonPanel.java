package org.test.tamplets.menupage.profilepage.coursespage;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.CssLayout;

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
public class AddLessonPanel extends CssLayout {
    protected TextField lessonNameTextField;
    protected DateField dateField;
    protected TextField costTextField;
    protected Button addLessonButton;

    public AddLessonPanel() {
        Design.read(this);
    }

    public TextField getLessonNameTextField() {
        return lessonNameTextField;
    }

    public DateField getDateField() {
        return dateField;
    }

    public TextField getCostTextField() {
        return costTextField;
    }

    public Button getAddLessonButton() {
        return addLessonButton;
    }
}
