package org.test.customcomponents;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import org.test.controllers.MainPageController;
import org.test.tamplets.MainPage;

import java.util.Date;

/**
 * Created by Аркадий on 23.10.2016.
 */
public class MainPageImpl extends MainPage implements View {
    private MainPageController controller;
    private SignPanelImpl signPanel;

    public MainPageImpl(Navigator navigator) {
        controller = new MainPageController(this);

        signPanel = new SignPanelImpl();
        underSignLayout.addComponent(signPanel);

        createListenerForSignInButton(navigator);
        createListenerForSignUpButton();
    }

    private void createListenerForSignUpButton() {
        controller.createListenerForSingUpButton(signPanel);
    }

    private void createListenerForSignInButton(Navigator navigator) {
        controller.createListenerForSingInButton(navigator, signPanel);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
