package org.test.tamplets;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;

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
public class MenuPage extends HorizontalLayout {
    protected HorizontalLayout majorHorizontalLayout;
    protected Button profileButton;
    protected Button friendsButton;
    protected Button searchButton;
    protected Button classButton;
    protected Button tasksButton;
    protected Button logOutButton;
    protected Panel mainPanel;

    public MenuPage() {
        Design.read(this);
    }
}
