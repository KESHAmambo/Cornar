package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.test.controllers.MyUIController;
import org.test.customcomponents.MainPageImpl;
import org.test.customcomponents.MenuPageImpl;

import static org.test.logic.PageName.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

@Push
@PreserveOnRefresh
@Theme("mytheme")
public class MyUI extends UI {
    private Navigator navigator;
    private MyUIController controller;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        controller = new MyUIController(this);
        navigator = createNavigator();
        navigator.navigateTo("");

        getPage().setTitle("Cornar");
    }

    private Navigator createNavigator() {
        Navigator navigator = new Navigator(this, this);

        navigator.addView("", new MainPageImpl(navigator));
        navigator.addView(MENU.toString(), new MenuPageImpl(navigator));

        return navigator;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
