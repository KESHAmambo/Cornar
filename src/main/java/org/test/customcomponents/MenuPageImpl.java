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
        menuButtonsNavigator.addView(MENU_PAGE.toString(), new ProfilePageImpl());

        menuButtonsNavigator.addView(MENU_PAGE + "/" + PROFILE_PAGE,
                new ProfilePageImpl());
        menuButtonsNavigator.addView(MENU_PAGE + "/" + FRIENDS,
                new FriendsPageImpl());
        menuButtonsNavigator.addView(MENU_PAGE + "/" + SEARCH,
                new SearchPageImpl());
        menuButtonsNavigator.addView(MENU_PAGE + "/" + CLASS,
                new ClassPageImpl());
        menuButtonsNavigator.addView(MENU_PAGE + "/" + TASKS,
                new TasksPageImpl());

        return menuButtonsNavigator;
    }

    private void createListenersForMenuButtons(Navigator menuButtonsNavigator) {
        controller.createListenerForMenuButton(
                profileButton, menuButtonsNavigator, MENU_PAGE + "/" + PROFILE_PAGE);
        controller.createListenerForMenuButton(
                friendsButton, menuButtonsNavigator, MENU_PAGE + "/" + FRIENDS);
        controller.createListenerForMenuButton(
                searchButton, menuButtonsNavigator, MENU_PAGE + "/" + SEARCH);
        controller.createListenerForMenuButton(
                classButton, menuButtonsNavigator, MENU_PAGE + "/" + CLASS);
        controller.createListenerForMenuButton(
                tasksButton, menuButtonsNavigator, MENU_PAGE + "/" + TASKS);
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

    private void provideNavigationForLogOut(Navigator navigator) {
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
