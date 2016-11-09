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

    public MainPageImpl() {
        controller = new MainPageController(this);

        signPanel = new SignPanelImpl();
        underSignLayout.addComponent(signPanel);

        createListenerForSignUpButton();
    }

    private void createListenerForSignUpButton() {
        Window signUpWindow = signPanel.getSignUpWindow();
        SignUpPanelImpl signUpPanel = signPanel.getSignUpPanel();
        String userName = signUpPanel.readName();
        String userSurname = signUpPanel.readSurname();
        String userEmail = signUpPanel.readEmail();
        String userPassword = signUpPanel.readPassword();
        String userEducation = signUpPanel.readEducation();
        Date userBirthDate = signUpPanel.readBirthDate();
        Button signUpButton = signUpPanel.getSignUpButton();

        controller.createListenerForSingUpButton(
                signUpButton, signUpWindow, userName, userSurname,
                userEmail, userPassword, userEducation, userBirthDate);
    }

    public void provideNavigationForSignIn(Navigator navigator) {
        String userEmail = signPanel.getUserEmail();
        String userPassword = signPanel.getUserPassword();
        Button signInButton = signPanel.getSignInButton();

        controller.createListenerForSingInButton(
                signInButton, navigator, userEmail, userPassword);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
