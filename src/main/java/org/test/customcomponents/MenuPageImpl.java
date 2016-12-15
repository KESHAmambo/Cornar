package org.test.customcomponents;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import org.test.MyUI;
import org.test.controllers.MenuPageController;
import org.test.customcomponents.menupage.*;
import org.test.logic.Profile;
import org.test.customcomponents.menupage.ClassPageImpl;
import org.test.customcomponents.menupage.FriendsPageImpl;
import org.test.customcomponents.menupage.SearchPageImpl;
import org.test.customcomponents.menupage.TasksPageImpl;
import org.test.logic.Profile;
import org.test.tamplets.MenuPage;
import org.test.tamplets.menupage.ProfilePage;
import org.vaadin.sliderpanel.SliderPanel;
import org.vaadin.sliderpanel.SliderPanelBuilder;
import org.vaadin.sliderpanel.client.SliderMode;
import org.vaadin.sliderpanel.client.SliderTabPosition;

import java.io.File;

import static org.test.logic.PageName.*;

/**
 * Created by Аркадий on 26.10.2016.
 */
public class MenuPageImpl extends MenuPage implements View {
    private final MenuPageController controller;
    private final MyUI myUI;
    private final Navigator menuButtonsNavigator;

    public MenuPageImpl(MyUI myUI, Navigator generalNavigator) {
        controller = new MenuPageController(this);
        this.myUI = myUI;

        majorHorizontalLayout.addComponent(createSlidePanelForChatPage());
        fulfillAvatarImage();

        menuButtonsNavigator = createNavigatorForMenuButtons();
        createListenersForMenuButtons();
        controller.createListenerForLogOutButton(generalNavigator);

        menuButtonsNavigator.navigateTo(MENU + "/" + PROFILE);
    }

    private void fulfillAvatarImage() {
        Profile profile = Profile.getCurrentProfile();
        File profileImage = profile.getImageResource();
        if(profileImage != null) {
            FileResource resource = new FileResource(profile.getImageResource());
            avatarImage.setSource(resource);
        }
    }

    private Navigator createNavigatorForMenuButtons() {
        Navigator menuButtonsNavigator = new Navigator(myUI, mainPanel);

        menuButtonsNavigator.addView("", (View) viewChangeEvent -> {});
        menuButtonsNavigator.addView(MENU.toString(), (View) viewChangeEvent -> {});

        menuButtonsNavigator.addView(MENU + "/" + PROFILE,
                new ProfilePageImpl(myUI));
        menuButtonsNavigator.addView(MENU + "/" + FRIENDS,
                new FriendsPageImpl());
        menuButtonsNavigator.addView(MENU + "/" + SEARCH,
                new SearchPageImpl());
        menuButtonsNavigator.addView(MENU + "/" + CLASS,
                new ClassPageImpl());
        menuButtonsNavigator.addView(MENU + "/" + TASKS,
                new TasksPageImpl());

        return menuButtonsNavigator;
    }

    private void createListenersForMenuButtons() {
        controller.createListenerForMenuButton(
                profileButton, menuButtonsNavigator, MENU + "/" + PROFILE);
        controller.createListenerForMenuButton(
                friendsButton, menuButtonsNavigator, MENU + "/" + FRIENDS);
        controller.createListenerForMenuButton(
                searchButton, menuButtonsNavigator, MENU + "/" + SEARCH);
        controller.createListenerForMenuButton(
                classButton, menuButtonsNavigator, MENU + "/" + CLASS);
        controller.createListenerForMenuButton(
                tasksButton, menuButtonsNavigator, MENU + "/" + TASKS);
    }

    private SliderPanel createSlidePanelForChatPage() {
        return new SliderPanelBuilder(new ChatPageImpl(myUI))
                    .expanded(false)
                    .caption("Dialogs")
                    .mode(SliderMode.RIGHT)
                    .style("sliderPanel")
                    .tabPosition(SliderTabPosition.MIDDLE)
                    .build();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

       /*String basePath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        System.out.println(basePath);*/
//        FileResource avatarResource = new FileResource(new File(basePath + "VAADIN/themes/images/test.png"));
       /* FileResource avatarResource = new FileResource(new File(basePath + "/images/test.png"));
        avatarImage.setSource(avatarResource);*/

//        Image avatar = new Image("", avatarResource);

//        avatarLayout.addComponent(avatar, Alignment_M);
}
