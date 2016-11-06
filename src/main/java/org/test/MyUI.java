package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.test.customcomponents.MainPageImpl;

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

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout basicLayout = new VerticalLayout();
        basicLayout.setSizeFull();

        MainPageImpl mainPage = new MainPageImpl(basicLayout);
//        MenuPage menuPage = new  MenuPageImpl();
//        basicLayout.addComponent(menuPage);
//        mainPage.setWidth("100%");
//        mainPage.setHeight("100%")
        basicLayout.addComponent(mainPage);
        getSession().setAttribute("user", "42");


        setContent(basicLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
