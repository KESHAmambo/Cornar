package org.test.controllers;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import org.test.customcomponents.MenuPageImpl;
import org.test.logic.Profile;

/**
 * Created by abara on 09.11.2016.
 */
public class MenuPageController {
    private final MenuPageImpl menuPage;

    public MenuPageController(MenuPageImpl menuPage) {
        this.menuPage = menuPage;
    }

    public void createListenerForLogOutButton(Button logOutButton, Navigator navigator) {
        logOutButton.addClickListener(e -> {
            Profile.clearCurrentProfile();
            navigator.navigateTo("");
        });
    }
}
