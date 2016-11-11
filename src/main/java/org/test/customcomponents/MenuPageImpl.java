package org.test.customcomponents;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.test.controllers.MenuPageController;
import org.test.customcomponents.menupage.*;
import org.test.tamplets.MenuPage;
import org.vaadin.sliderpanel.SliderPanel;
import org.vaadin.sliderpanel.SliderPanelBuilder;
import org.vaadin.sliderpanel.client.SliderMode;
import org.vaadin.sliderpanel.client.SliderTabPosition;

import static org.test.logic.PageName.*;

/**
 * Created by Аркадий on 26.10.2016.
 */
public class MenuPageImpl extends MenuPage implements View {
    private final MenuPageController controller;

    public MenuPageImpl(Navigator navigator) {
        controller = new MenuPageController(this);

        majorHorizontalLayout.addComponent(createChatSlidePanel());
        mainPanel.setContent(new ProfilePageImpl());

        provideNavigation(navigator);
    }

    private void provideNavigation(Navigator generalNavigator) {
        provideNavigationForLogOut(generalNavigator);
        Navigator menuButtonsNavigator = createNavigatorForMenuButtons();
        createListenersForMenuButtons(menuButtonsNavigator);
    }

    private Navigator createNavigatorForMenuButtons() {
        Navigator menuButtonsNavigator = new Navigator(UI.getCurrent(), mainPanel);

        menuButtonsNavigator.addView("", new ProfilePageImpl());
        menuButtonsNavigator.addView(MENU.toString(), new ProfilePageImpl());

        menuButtonsNavigator.addView(MENU + "/" + PROFILE,
                new ProfilePageImpl());
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

    private void createListenersForMenuButtons(Navigator menuButtonsNavigator) {
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

    private SliderPanel createChatSlidePanel() {
        return new SliderPanelBuilder(createChatPanelContent())
                    .expanded(false)
                    .caption("Dialogs")
                    .mode(SliderMode.RIGHT)
                    .style("chatPanelMajorLayout")
                    .tabPosition(SliderTabPosition.MIDDLE)
                    .build();
    }

    private Component createChatPanelContent() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setHeightUndefined();
        verticalLayout.setWidth("400");
        verticalLayout.setHeight("100%");
        return verticalLayout;
    }

    public void provideNavigationForLogOut(Navigator navigator) {
        controller.createListenerForLogOutButton(logOutButton, navigator);
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
