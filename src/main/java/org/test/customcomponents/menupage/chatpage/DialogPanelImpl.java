package org.test.customcomponents.menupage.chatpage;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import org.test.controllers.menupage.chatpage.DialogPanelController;
import org.test.logic.Profile;
import org.test.tamplets.menupage.chatpage.DialogPanel;

import java.util.Date;

import static org.test.customcomponents.menupage.chatpage.ChatMessagePanelImpl.ChatMessageType.*;

/**
 * Created by abara on 16.11.2016.
 */
public class DialogPanelImpl extends DialogPanel {
    private DialogPanelController controller;
    private String friendName;

    public DialogPanelImpl(Profile friendProfile) {
        this.controller = new DialogPanelController(this, friendProfile);
        this.friendName = friendProfile.getName();

        friendNameLabel.setValue(friendProfile.getName() + " " + friendProfile.getSurname());

        controller.createListenerForSendMessageButton();
    }

    public void addSentMessage(String messageText) {
        Label label = new Label(
                "<h3>You:</h3><p>" + messageText + "</p>",
                ContentMode.HTML);
        label.setHeight("-1px");
        label.setWidth("100%");
        label.setStyleName("sentMessageLabel");
        messagesLayout.addComponent(label);

        /*ChatMessagePanelImpl chatMessagePanel = new ChatMessagePanelImpl(
                SENT, "You", messageText, new Date());
        messagesLayout.addComponent(chatMessagePanel);*/
    }

    public void addReceivedMessage(String messageText) {
        /*ChatMessagePanelImpl chatMessagePanel = new ChatMessagePanelImpl(
                RECEIVED, friendName, messageText, new Date());
        messagesLayout.addComponent(chatMessagePanel);*/

        Label label = new Label(
                "<h3>" + friendName + ":</h3><p>" + messageText + "</p>",
                ContentMode.HTML);
        label.setHeight("-1px");
        label.setWidth("100%");
        label.setStyleName("receivedMessageLabel");
        messagesLayout.addComponent(label);
    }
}
