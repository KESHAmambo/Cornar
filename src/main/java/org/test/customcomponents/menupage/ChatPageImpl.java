package org.test.customcomponents.menupage;

import com.vaadin.ui.Button;
import org.test.customcomponents.menupage.chatpage.DialogPanelImpl;
import org.test.customcomponents.menupage.chatpage.FriendPanelImpl;
import org.test.logic.Profile;
import org.test.tamplets.menupage.ChatPage;

import java.util.List;

/**
 * Created by abara on 16.11.2016.
 */
public class ChatPageImpl extends ChatPage {
    public ChatPageImpl() {
        Profile profile = Profile.getCurrentProfile();
        addFriendPanels(profile.getFriends());
    }

    private void addFriendPanels(List<Profile> friends) {
        System.out.println(friends.size());
        for (Profile friendProfile: friends) {
            FriendPanelImpl friendPanel = new FriendPanelImpl(friendProfile);
            friendPanel.addLayoutClickListener(e -> {
                DialogPanelImpl dialogPanel = new DialogPanelImpl(friendProfile);
                layoutForDialogPanel.removeAllComponents();
                layoutForDialogPanel.addComponent(dialogPanel);
            });

            gridLayout.addComponent(friendPanel);
        }
    }
}
