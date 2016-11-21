package org.test.controllers;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import org.test.customcomponents.MenuPageImpl;
import org.test.logic.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abara on 09.11.2016.
 */
public class MenuPageController {
    private final MenuPageImpl menuPage;
    private List<Button> menuButtons = new ArrayList<>();

    public MenuPageController(MenuPageImpl menuPage) {
        this.menuPage = menuPage;
    }

    public void createListenerForLogOutButton(Button logOutButton, Navigator navigator) {
        logOutButton.addClickListener(e -> {
            navigator.navigateTo("");
        });
    }

    public void createListenerForMenuButton(
            Button button, Navigator navigator, String navigationState) {
        menuButtons.add(button);
        button.addClickListener(e -> {
            navigator.navigateTo(navigationState);
            focusCurrentButton(button);
        });
    }

    private void focusCurrentButton(Button button) {
        for(Button menuButton: menuButtons) {
            menuButton.removeStyleName("menuButtonFocused");
        }
        button.addStyleName("menuButtonFocused");
    }
}
