package org.test.controllers.menupage.profilepage.inboxpage;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxComposePanelImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.InboxMessage;
import org.test.logic.Profile;
import org.test.msgservice.MessageManager;
import org.test.utils.UIHelper;

import java.util.Date;

/**
 * Created by abara on 22.11.2016.
 */
public class InboxComposePanelController {
    private final InboxComposePanelImpl inboxComposePanel;

    public InboxComposePanelController(InboxComposePanelImpl inboxComposePanel) {
        this.inboxComposePanel = inboxComposePanel;
    }

    public void createListenerForSendMessageButton(Window window) {
        inboxComposePanel.getSendMessageButton().addClickListener(e -> {
            String receiverEmail = inboxComposePanel.getReceiverEmailTextField().getValue();
            Profile receiveProfile = DatabaseManager.getProfile(receiverEmail);
            if(receiveProfile == null) {
                Notification.show(
                        "There is no user with such email.",
                        Notification.Type.ERROR_MESSAGE);
            } else if (receiveProfile.equals(Profile.getCurrentProfile())) {
                Notification.show(
                        "There is no use in messaging with yourself...",
                        Notification.Type.WARNING_MESSAGE);
            } else {
                InboxMessage message = formMessage(receiveProfile);
                MessageManager.sendInboxMessage(message);
                UI.getCurrent().removeWindow(window);
                UIHelper.showSuccessNotification(
                        "Message was successfully sent!",
                        "addedCourseNotification");
            }
        });
    }

    private InboxMessage formMessage(Profile receiveProfile) {
        String theme = inboxComposePanel.getThemeTextField().getValue();
        String text = inboxComposePanel.getRichTextArea().getValue();
        return new InboxMessage(
                Profile.getCurrentProfile(), receiveProfile,
                new Date(), theme, text, false);
    }
}
