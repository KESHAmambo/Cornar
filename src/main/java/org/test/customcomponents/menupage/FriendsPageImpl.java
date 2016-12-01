package org.test.customcomponents.menupage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.test.controllers.menupage.friendspage.FriendsPageController;
import org.test.tamplets.menupage.FriendsPage;

/**
 * Created by abara on 09.11.2016.
 */
public class FriendsPageImpl extends FriendsPage implements View {
    private final FriendsPageController controller;

    public FriendsPageImpl() {
        controller = new FriendsPageController(this);

        controller.fulfillPage();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
