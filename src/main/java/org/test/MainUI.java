package org.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MainUI {

//    @Override
//    protected void init(VaadinRequest vaadinRequest) {
//        VerticalLayout basicLayout = new VerticalLayout();
//        basicLayout.setSizeFull();
//
//        MenuPage menuPage = new  MenuPageImpl();
//        basicLayout.addComponent(menuPage);
//
//        setContent(basicLayout);
//    }
//
//    @WebServlet(urlPatterns = {"/login/", "/VAADIN/login/"}, name = "MainUIServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
//    public static class MainUIServlet extends VaadinServlet {
//    }
}