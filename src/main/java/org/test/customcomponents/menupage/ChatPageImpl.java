package org.test.customcomponents.menupage;

import org.test.MyUI;
import org.test.customcomponents.menupage.chatpage.DialogPanelImpl;
import org.test.customcomponents.menupage.chatpage.FriendDialogPanelImpl;
import org.test.msgservice.ChatMessage;
import org.test.logic.Profile;
import org.test.tamplets.menupage.ChatPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abara on 16.11.2016.
 */
public class ChatPageImpl extends ChatPage {
    private Map<Integer, DialogPanelImpl> dialogs = new HashMap<>();

    public ChatPageImpl(MyUI myUI) {
        createFriendPanels(Profile.getCurrentProfile().getFriends());
        myUI.setChatPage(this);
    }

    private void createFriendPanels(List<Profile> friends) {
        for (Profile friendProfile: friends) {
            FriendDialogPanelImpl friendPanel = new FriendDialogPanelImpl(friendProfile);
            DialogPanelImpl dialogPanel = createAndRegisterDialogPanel(friendProfile);

            friendPanel.addLayoutClickListener(e -> {
                layoutForDialogPanel.removeAllComponents();
                layoutForDialogPanel.addComponent(dialogPanel);
            });

            gridLayout.addComponent(friendPanel);
        }
    }

    private DialogPanelImpl createAndRegisterDialogPanel(Profile friendProfile) {
        DialogPanelImpl dialogPanel = new DialogPanelImpl(friendProfile);
        dialogs.put(friendProfile.getId(), dialogPanel);
        return dialogPanel;
    }

    public void receiveMessage(ChatMessage message) {
        DialogPanelImpl dialogPanel = dialogs.get(message.getSenderId());
        dialogPanel.addReceivedMessage(message.getMessageText());
    }

    public void showSentMessage(ChatMessage message) {
        DialogPanelImpl dialogPanel = dialogs.get(message.getReceiverId());
        dialogPanel.addSentMessage(message.getMessageText());
    }
}
