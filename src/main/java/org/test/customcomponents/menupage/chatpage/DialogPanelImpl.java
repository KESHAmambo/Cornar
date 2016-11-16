package org.test.customcomponents.menupage.chatpage;

import org.test.logic.Profile;
import org.test.tamplets.menupage.chatpage.DialogPanel;

/**
 * Created by abara on 16.11.2016.
 */
public class DialogPanelImpl extends DialogPanel {
    private final Profile friendProfile;

    public DialogPanelImpl(Profile friendProfile) {
        this.friendProfile = friendProfile;
        friendNameLabel.setValue(
                friendProfile.getName() + " " + friendProfile.getSurname());
        //TODO: add ClickListener for sendButton
    }
}
