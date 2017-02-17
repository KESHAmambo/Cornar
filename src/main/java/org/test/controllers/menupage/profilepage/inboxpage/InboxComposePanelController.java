package org.test.controllers.menupage.profilepage.inboxpage;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxComposePanelImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.InboxMessage;
import org.test.logic.Profile;
import org.test.msgservice.MailManager;
import org.test.msgservice.UIAlterationManager;
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
            Profile receiverProfile = DatabaseManager.getProfile(receiverEmail);
            if(receiverProfile == null) {
                Notification.show(
                        "There is no user with such email.",
                        Notification.Type.ERROR_MESSAGE);
            } else if (receiverProfile.equals(Profile.getCurrentProfile())) {
                Notification.show(
                        "There is no use in messaging with yourself...",
                        Notification.Type.WARNING_MESSAGE);
            } else {
                sendMessage(window, receiverProfile);
            }
        });
    }

    private void sendMessage(Window window, Profile receiverProfile) {
        InboxMessage message = formMessage(receiverProfile);
        UI.getCurrent().removeWindow(window);
        UIAlterationManager.sendInboxMessage(message);
        MailManager.sendMail(message);
        UIHelper.showSuccessNotification(
                "Message was successfully sent!",
                "addedCourseNotification");
    }

    private InboxMessage formMessage(Profile receiveProfile) {
        String theme = inboxComposePanel.getThemeTextField().getValue();
        String text = inboxComposePanel.getRichTextArea().getValue();
        return new InboxMessage(
                Profile.getCurrentProfile(), receiveProfile,
                new Date(), theme, text, false);
    }

    public void fulfillReceiverEmailField(String email) {
        inboxComposePanel.getReceiverEmailTextField().setValue(email);
    }
}
