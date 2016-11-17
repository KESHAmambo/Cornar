package org.test.controllers.menupage.chatpage;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import org.test.customcomponents.menupage.chatpage.DialogPanelImpl;
import org.test.logic.ChatMessage;
import org.test.logic.MessageManager;
import org.test.logic.Profile;

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
                tryToSendMessage(messageTextArea, messageText, message);
            }
        });
    }

    private void tryToSendMessage(
            TextArea messageTextArea, String messageText, ChatMessage message) {
        try {
            MessageManager.sendChatMessage(message);
            dialogPanel.addSentMessage(messageText);
            messageTextArea.setValue("");
        } catch (NullPointerException e) {
            Notification notification = new Notification(
                    friendProfile.getName() + " is offline",
                    Notification.Type.WARNING_MESSAGE);
            notification.setPosition(Position.BOTTOM_RIGHT);
            notification.show(Page.getCurrent());
        }
    }
}
