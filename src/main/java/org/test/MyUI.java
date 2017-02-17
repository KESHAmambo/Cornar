package org.test;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.test.controllers.MyUIController;
import org.test.controllers.menupage.classpage.StudentClassPageController;
import org.test.controllers.menupage.classpage.TutorClassPageController;
import org.test.customcomponents.MainPageImpl;
import org.test.customcomponents.MenuPageImpl;
import org.test.customcomponents.menupage.ChatPageImpl;
import org.test.customcomponents.menupage.FriendsPageImpl;
import org.test.customcomponents.menupage.classpage.ClassBoardMessageImpl;
import org.test.customcomponents.menupage.profilepage.InboxPageImpl;
import org.test.lessonservice.LessonConnection;
import org.test.logic.InboxMessage;
import org.test.logic.Profile;
import org.test.msgservice.ChatMessage;
import org.test.msgservice.UIAlterationManager;

import javax.servlet.annotation.WebServlet;
import java.util.Locale;

import static org.test.logic.PageName.MENU;

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
public class MyUI extends UI implements UIAlterationManager.UIAlterationListener, LessonConnection.LessonConnectionListener {
    private Navigator navigator;
    private MyUIController controller;
    private ChatPageImpl chatPage;
    private InboxPageImpl inboxPage;
    private FriendsPageImpl friendsPage;
    private TutorClassPageController tutorClassPageController;
    private StudentClassPageController studentClassPageController;

    @Override
    public Navigator getNavigator() {
        return navigator;
    }

    public void setChatPage(ChatPageImpl chatPage) {
        this.chatPage = chatPage;
    }

    public void setInboxPage(InboxPageImpl inboxPage) {
        this.inboxPage = inboxPage;
    }

    public void setFriendsPage(FriendsPageImpl friendsPage) {
        this.friendsPage = friendsPage;
    }

    public void setTutorClassPageController(TutorClassPageController tutorClassPageController) {
        this.tutorClassPageController = tutorClassPageController;
    }

    public void setStudentClassPageController(StudentClassPageController studentClassPageController) {
        this.studentClassPageController = studentClassPageController;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Locale.setDefault(Locale.ENGLISH);
        
        controller = new MyUIController(this);
        navigator = createNavigator();

        navigateToView();

        getPage().setTitle("Cornar");
    }

    private Navigator createNavigator() {
        Navigator navigator = new Navigator(this, this);
        navigator.addView("", new MainPageImpl());
        return navigator;
    }

    private void navigateToView() {
        if(!wasSessionAlreadyOpened()) {
            navigator.navigateTo("");
        } else {
            navigator.addView(MENU.toString(), new MenuPageImpl(this, navigator));
            navigator.navigateTo(MENU.toString());
        }
    }

    private boolean wasSessionAlreadyOpened() {
        Object profile = VaadinSession.getCurrent().getAttribute("profile");
        return profile != null;
    }

    @Override
    public void receiveChatMessage(ChatMessage message) {
        access(() -> {
            chatPage.receiveMessage(message);
        });
    }

    @Override
    public void showSentChatMessage(ChatMessage message) {
        chatPage.showSentMessage(message);
    }

    @Override
    public void receiveInboxMessage(InboxMessage message) {
        access(() -> {
            inboxPage.addMessageBox(message);
        });
    }

    @Override
    public void showAddedFriend(Profile profile) {
        friendsPage.addFriendBox(profile);
        chatPage.addFriendDialogPanel(profile);
    }

    @Override
    public void showSentInboxMessage(InboxMessage message) {
        inboxPage.addMessageBox(message);
    }

    @Override
    public void receiveClassBoardMessage(ClassBoardMessageImpl classBoardMessage) {
        access(() -> {
            studentClassPageController.addClassBoardMessage(classBoardMessage);
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
