package org.test.customcomponents.menupage.chatpage;

import org.test.tamplets.menupage.chatpage.ChatMessagePanel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by abara on 17.11.2016.
 */
class ChatMessagePanelImpl extends ChatMessagePanel {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);

    ChatMessagePanelImpl(
            ChatMessageType messageType, String senderName,
            String messageText, Date messageTime) {
        setStyle(messageType);
        senderNameLabel.setValue(senderName + ":");
        messageTextLabel.setValue(messageText);
        timeLabel.setValue(dateFormat.format(messageTime));
    }

    private void setStyle(ChatMessageType messageType) {
        switch (messageType) {
            case SENT:
                mainLayout.setStyleName("sentChatMessageLayout");
                break;
            case RECEIVED:
                mainLayout.setStyleName("receivedChatMessageLayout");
                break;
            default:
                break;
        }
    }

    void setStile(String layoutStyle) {
        mainLayout.setStyleName(layoutStyle);
    }

    enum ChatMessageType {
        SENT,
        RECEIVED
    }
}
