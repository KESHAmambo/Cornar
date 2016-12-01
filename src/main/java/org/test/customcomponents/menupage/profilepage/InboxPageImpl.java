package org.test.customcomponents.menupage.profilepage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.MyUI;
import org.test.controllers.menupage.profilepage.InboxPageController;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxComposePanelImpl;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxMessageBoxImpl;
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

    public InboxPageImpl(MyUI myUI) {
        controller = new InboxPageController(this);

        //controller.fulfillMessagesLayout();
        controller.createListenerForComposeButton();

        myUI.setInboxPage(this);
    }

    public void addMessageBox(InboxMessage message) {
        controller.addMessageBox(message);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }


}
