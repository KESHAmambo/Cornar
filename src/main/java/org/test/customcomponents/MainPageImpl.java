package org.test.customcomponents;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Notification;
import org.test.MyUI;
import org.test.controllers.MainPageController;
import org.test.tamplets.MainPage;

/**
 * Created by Аркадий on 23.10.2016.
 */
public class MainPageImpl extends MainPage implements View {
    private MainPageController controller;
    private SignPanelImpl signPanel;

    public MainPageImpl() {
        controller = new MainPageController(this);
//
        signPanel = new SignPanelImpl();
        underSignLayout.addComponent(signPanel);

        controller.createListenerForSingInButton(signPanel);
        controller.createListenerForSingUpButton(signPanel);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
