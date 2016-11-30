package org.test.controllers.menupage.friendspage;

import com.vaadin.ui.VerticalLayout;
import org.test.customcomponents.menupage.FriendsPageImpl;
import org.test.customcomponents.menupage.friendspage.ProfileBoxImpl;
import org.test.logic.Profile;

import java.util.List;

/**
 * Created by abara on 30.11.2016.
 */
public class FriendsPageController {
    private final FriendsPageImpl friendsPage;

    public FriendsPageController(FriendsPageImpl friendsPage) {
        this.friendsPage = friendsPage;
    }

    public void fulfillPage() {
        List<Profile> friends = Profile.getCurrentProfile().getFriends();
        VerticalLayout mainLayout = friendsPage.getMainLayout();
        for (Profile profile: friends) {
            mainLayout.addComponent(new ProfileBoxImpl(profile));
        }
    }
}
