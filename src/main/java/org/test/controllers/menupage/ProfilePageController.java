package org.test.controllers.menupage;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import org.test.customcomponents.menupage.ProfilePageImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abara on 10.11.2016.
 */
public class ProfilePageController {
    private final ProfilePageImpl profilePage;
    private final List<Button> menuButtons = new ArrayList<>();

    public ProfilePageController(ProfilePageImpl profilePage) {
        this.profilePage = profilePage;
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
            menuButton.removeStyleName("profileButtonsFocused");
        }
        button.addStyleName("profileButtonsFocused");
    }
}
