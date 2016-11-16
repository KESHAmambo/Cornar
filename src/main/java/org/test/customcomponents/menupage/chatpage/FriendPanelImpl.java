package org.test.customcomponents.menupage.chatpage;

import org.test.logic.Profile;
import org.test.tamplets.menupage.chatpage.FriendPanel;

/**
 * Created by abara on 16.11.2016.
 */
public class FriendPanelImpl extends FriendPanel {
    private Profile profile;

    public FriendPanelImpl(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
