package org.test.customcomponents.menupage.profilepage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.MyUI;
import org.test.controllers.menupage.profilepage.InboxPageController;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxComposePanelImpl;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxMessageBoxImpl;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxReadPanelImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.InboxMessage;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.InboxPage;

import java.util.List;

/**
 * Created by abara on 10.11.2016.
 */
public class InboxPageImpl extends InboxPage implements View {
    private final InboxPageController controller;
    private final List<InboxMessage> messages;

    public InboxPageImpl(MyUI myUI) {
        controller = new InboxPageController(this);
        messages = DatabaseManager.pullInboxMessages(Profile.getCurrentProfile().getId());

        fulfillMessagesLayout();
        createListenerForComposeButton();

        myUI.setInboxPage(this);
    }

    private void fulfillMessagesLayout() {
        controller.sortMessages(messages);
        for(InboxMessage message: messages) {
            messagesLayout.addComponent(new InboxMessageBoxImpl(message));
        }
    }

    private void createListenerForComposeButton() {
        composeButton.addClickListener(e -> {
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
        messagesLayout.addComponentAsFirst(new InboxMessageBoxImpl(message));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }


}
