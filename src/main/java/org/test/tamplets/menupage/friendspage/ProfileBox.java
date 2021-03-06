package org.test.tamplets.menupage.friendspage;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;

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
public class ProfileBox extends VerticalLayout {
    protected Image image;
    protected Label nameLabel;
    protected Button writeMessageButton;
    protected Label birthdayLabel;
    protected Label emailLabel;
    protected Label educationLabel;

    public ProfileBox() {
        Design.read(this);
    }

    public Image getImage() {
        return image;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public Label getBirthdayLabel() {
        return birthdayLabel;
    }

    public Label getEmailLabel() {
        return emailLabel;
    }

    public Label getEducationLabel() {
        return educationLabel;
    }

    public Button getWriteMessageButton() {
        return writeMessageButton;
    }
}
