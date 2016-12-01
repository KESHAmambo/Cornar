package org.test.controllers.menupage.profilepage;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.test.customcomponents.menupage.profilepage.InboxPageImpl;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxComposePanelImpl;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxMessageBoxImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.InboxMessage;
import org.test.logic.Profile;
import org.test.utils.UIHelper;

import java.util.Date;
import java.util.List;

/**
 * Created by abara on 11.11.2016.
 */
public class InboxPageController {
    private final InboxPageImpl inboxPage;

    public InboxPageController(InboxPageImpl inboxPage) {
        this.inboxPage = inboxPage;
    }

    public void fulfillMessagesLayout() {
        List<InboxMessage> messages = DatabaseManager.pullInboxMessages(Profile.getCurrentProfile());
        sortMessages(messages);
        VerticalLayout messagesLayout = inboxPage.getMessagesLayout();
        for(InboxMessage message: messages) {
            messagesLayout.addComponent(new InboxMessageBoxImpl(message));
        }
    }

    private void sortMessages(List<InboxMessage> messages) {
        messages.sort((InboxMessage m1, InboxMessage m2) -> {
            Date date1 = new Date();
            Date date2 = new Date();
            return date2.compareTo(date1);
        });
    }

    public void createListenerForComposeButton() {
        inboxPage.getComposeButton().addClickListener(e -> {
            Window composeWindow = createComposeWindow();
            UI.getCurrent().addWindow(composeWindow);
        });
    }

    private Window createComposeWindow() {
        Window window = new Window();
        window.setContent(new InboxComposePanelImpl(window));
        window.center();
        window.setModal(true);
        window.setWidth("50%");
        window.setHeight("90%");
        return window;
    }

    public void addMessageBox(InboxMessage message) {
        inboxPage.getMessagesLayout().addComponentAsFirst(
                new InboxMessageBoxImpl(message));
    }
}
