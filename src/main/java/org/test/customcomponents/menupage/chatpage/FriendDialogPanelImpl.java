package org.test.customcomponents.menupage.chatpage;

import com.vaadin.server.FileResource;
import org.test.logic.Profile;
import org.test.tamplets.menupage.chatpage.FriendDialogPanel;

import java.io.File;

/**
 * Created by abara on 16.11.2016.
 */
public class FriendDialogPanelImpl extends FriendDialogPanel {
    private Profile profile;

    public FriendDialogPanelImpl(Profile profile) {
        this.profile = profile;

        fulfillAvatarImage();
    }

    private void fulfillAvatarImage() {
        File profileImage = profile.getImageResource();
        if(profileImage != null) {
            FileResource resource = new FileResource(profile.getImageResource());
            avatarImage.setSource(resource);
        }
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
