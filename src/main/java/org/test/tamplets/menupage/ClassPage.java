package org.test.tamplets.menupage;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Label;

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
public class ClassPage extends VerticalLayout {
    protected VerticalLayout mainLayout;
    protected Label noLessonLabel;
    protected Label nextLessonTimeLabel;

    public ClassPage() {
        Design.read(this);
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
    }

    public Label getNextLessonTimeLabel() {
        return nextLessonTimeLabel;
    }

    public Label getNoLessonLabel() {
        return noLessonLabel;
    }
}
