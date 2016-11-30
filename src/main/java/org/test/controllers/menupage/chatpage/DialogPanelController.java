package org.test.controllers.menupage.chatpage;

import com.vaadin.server.Page;
import com.vaadin.server.SessionExpiredException;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import org.test.MyUI;
import org.test.customcomponents.menupage.chatpage.DialogPanelImpl;
import org.test.msgservice.ChatMessage;
import org.test.msgservice.MessageManager;
import org.test.logic.Profile;

import java.util.Collection;

/**
 * Created by abara on 17.11.2016.
 */
public class DialogPanelController {
    private DialogPanelImpl dialogPanel;
    private Profile friendProfile;

    public DialogPanelController(DialogPanelImpl dialogPanel, Profile friendProfile) {
        this.dialogPanel = dialogPanel;
        this.friendProfile = friendProfile;
    }

    public void createListenerForSendMessageButton() {
        dialogPanel.getSendMessageButton().addClickListener(event -> {
            TextArea messageTextArea = dialogPanel.getMessageTextArea();
            String messageText = messageTextArea.getValue();

            if(messageText != null && !"".equals(messageText)) {
                ChatMessage message = new ChatMessage(
                        Profile.getCurrentProfile().getId(),
                        friendProfile.getId(),
                        messageText);
                sendMessage(messageTextArea, message);
            }
        });
    }

    private void sendMessage(TextArea messageTextArea, ChatMessage message) {
        try {
            MessageManager.sendChatMessage(message);
            messageTextArea.setValue("");
        } catch (MessageManager.NoSuchSessionException e) {
            showCanNotSendNotification();
        }
    }

    private void showCanNotSendNotification() {
        Notification notification = new Notification(
                friendProfile.getName() + " is offline",
                Notification.Type.WARNING_MESSAGE);
        notification.setPosition(Position.BOTTOM_RIGHT);
        notification.show(Page.getCurrent());
    }

}
