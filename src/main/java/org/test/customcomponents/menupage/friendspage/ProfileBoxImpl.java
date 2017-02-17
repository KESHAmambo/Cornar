package org.test.customcomponents.menupage.friendspage;

import com.vaadin.server.FileResource;
import org.test.controllers.menupage.friendspage.ProfileBoxController;
import org.test.logic.Profile;
import org.test.tamplets.menupage.friendspage.ProfileBox;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by abara on 27.11.2016.
 */
public class ProfileBoxImpl extends ProfileBox {
    private final ProfileBoxController controller;
    private final Profile profile;

    public ProfileBoxImpl(Profile profile) {
        controller = new ProfileBoxController(this);
        this.profile = profile;

        controller.fulfillPage();
        controller.addListenerForWriteMessageButton();
    }

    public Profile getProfile() {
        return profile;
    }
}
