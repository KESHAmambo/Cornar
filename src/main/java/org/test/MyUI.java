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
import org.test.customcomponents.menupage.ChatPageImpl;
import org.test.logic.ChatMessage;
import org.test.logic.MessageManager;
import org.test.logic.Profile;

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
public class MyUI extends UI implements MessageManager.MessageListener {
    private Navigator navigator;
    private MyUIController controller;
    private ChatPageImpl chatPage;

    public void setChatPage(ChatPageImpl chatPage) {
        this.chatPage = chatPage;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        controller = new MyUIController(this);
        navigator = createNavigator();
        navigator.navigateTo("");

        getPage().setTitle("Cornar");
    }

    @Override
    public void detach() {
        MessageManager.detachMessageListener(Profile.getCurrentProfile().getId());
        super.detach();
    }

    private Navigator createNavigator() {
        Navigator navigator = new Navigator(this, this);

        navigator.addView("", new MainPageImpl(navigator));
//        navigator.addView(MENU.toString(), new MenuPageImpl(navigator));

        return navigator;
    }

    @Override
    public void receiveChatMessage(ChatMessage message) {
        access(() -> {
            chatPage.receiveMessage(message);
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
