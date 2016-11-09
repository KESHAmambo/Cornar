package org.test.controllers;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import org.test.customcomponents.MenuPageImpl;
import org.test.logic.Profile;

import static org.test.logic.PageName.*;

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

    public void createListenerForProfileButton(Button profileButton, Navigator navigator) {
        profileButton.addClickListener(e -> {
            navigator.navigateTo(MENU_PAGE + "/" + PROFILE_PAGE);
        });
    }

    public void createListenerForFriendsButton(Button friendsButton, Navigator navigator) {
        friendsButton.addClickListener(e -> {
            navigator.navigateTo(MENU_PAGE + "/" + FRIENDS);
        });
    }

    public void createListenerForSearchButton(Button searchButton, Navigator navigator) {
        searchButton.addClickListener(e -> {
            navigator.navigateTo(MENU_PAGE + "/" + SEARCH);
        });
    }

    public void createListenerForClassButton(Button classButton, Navigator navigator) {
        classButton.addClickListener(e -> {
            navigator.navigateTo(MENU_PAGE + "/" + CLASS);
        });
    }

    public void createListenerForTasksButton(Button tasksButton, Navigator navigator) {
        tasksButton.addClickListener(e -> {
            navigator.navigateTo(MENU_PAGE + "/" + TASKS);
        });
    }
}
