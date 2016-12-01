package org.test.controllers.menupage.friendspage;

import com.vaadin.server.FileResource;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.customcomponents.menupage.friendspage.ProfileBoxImpl;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxComposePanelImpl;
import org.test.logic.Profile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by abara on 30.11.2016.
 */
public class ProfileBoxController {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);

    private final ProfileBoxImpl profileBox;

    public ProfileBoxController(ProfileBoxImpl profileBox) {
        this.profileBox = profileBox;
    }

    public void fulfillPage() {
        Profile profile = profileBox.getProfile();
        setProfileImage(profile);
        profileBox.getNameLabel().setValue(profile.getName() + " " + profile.getSurname());
        profileBox.getBirthdayLabel().setValue(dateFormat.format(profile.getBirthDate()));
        profileBox.getEmailLabel().setValue(profile.getEmail());
        profileBox.getEducationLabel().setValue(profile.getEducation());
    }

    private void setProfileImage(Profile profile) {
        File profileImage = profile.getImage();
        if(profileImage != null) {
            FileResource resource = new FileResource(profile.getImage());
            profileBox.getImage().setSource(resource);
        }
    }

    public void addListenerForWriteMessageButton() {
        profileBox.getWriteMessageButton().addClickListener(e -> {
            Window composeWindow = createComposeWindow();
            UI.getCurrent().addWindow(composeWindow);
        });
    }

    private Window createComposeWindow() {
        Window window = new Window();
        InboxComposePanelImpl inboxComposePanel = new InboxComposePanelImpl(window);
        inboxComposePanel.fulfillReceiverEmailField(profileBox.getProfile().getEmail());
        window.setContent(inboxComposePanel);
        window.center();
        window.setModal(true);
        window.setWidth("50%");
        window.setHeight("90%");
        return window;
    }
}
