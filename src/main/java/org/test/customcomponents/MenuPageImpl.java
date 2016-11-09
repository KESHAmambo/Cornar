package org.test.customcomponents;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.test.controllers.MenuPageController;
import org.test.logic.PageName;
import org.test.logic.Profile;
import org.test.tamplets.MenuPage;
import org.vaadin.sliderpanel.SliderPanel;
import org.vaadin.sliderpanel.SliderPanelBuilder;
import org.vaadin.sliderpanel.client.SliderMode;
import org.vaadin.sliderpanel.client.SliderTabPosition;

/**
 * Created by Аркадий on 26.10.2016.
 */
public class MenuPageImpl extends MenuPage implements View {
    private final MenuPageController controller;

    public MenuPageImpl() {
        controller = new MenuPageController(this);

        majorHorizontalLayout.addComponent(createChatSlidePanel());


        Navigator navigator = new Navigator(UI.getCurrent(), mainPanel);

        navigator.addView(PageName.MENU_PAGE + "/" + PageName.PROFILE_PAGE.toString(),
                new ProfilePageImpl(Profile.getCurrentProfile()));
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
