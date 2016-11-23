package org.test.controllers;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.MyUI;
import org.test.customcomponents.MainPageImpl;
import org.test.customcomponents.MenuPageImpl;
import org.test.customcomponents.SignPanelImpl;
import org.test.customcomponents.SignUpPanelImpl;
import org.test.dbservice.DatabaseManager;
import org.test.msgservice.MessageManager;
import org.test.logic.Profile;

import java.util.Collection;
import java.util.Date;

import static org.test.logic.PageName.*;

/**
 * Created by abara on 08.11.2016.
 */
public class MainPageController {
    private final MainPageImpl mainPage;

    public MainPageController(MainPageImpl mainPage) {
        this.mainPage = mainPage;
    }

    public void createListenerForSingInButton(
            SignPanelImpl signPanel) {
        Button signInButton = signPanel.getSignInButton();

        signInButton.addClickListener(e -> {
            String userEmail = signPanel.getUserEmail();
            String userPassword = signPanel.getUserPassword();

            if(DatabaseManager.doesUserExist(userEmail, userPassword)) {
                signOutPreviousProfile();
                signInNewProfile(userEmail);
                navigateToMenuPageInAllUIs(VaadinSession.getCurrent().getUIs());
            } else {
                showNoSuchUserNotification();
            }
        });
    }

    private void signOutPreviousProfile() {
        Profile previousProfile = (Profile) VaadinSession.getCurrent().getAttribute("profile");
        if(previousProfile != null) {
            MessageManager.unregisterSession(previousProfile.getId());
        }
    }

    private void signInNewProfile(String userEmail) {
        Profile profile = Profile.fulfillProfile(userEmail);
        VaadinSession session = VaadinSession.getCurrent();
        session.setAttribute("profile", profile);
        MessageManager.registerSession(profile.getId(), session);
    }

    private void navigateToMenuPageInAllUIs(Collection<UI> uis) {
        for(UI ui: uis) {
            Navigator navigator = ui.getNavigator();
            navigator.removeView(MENU.toString());
            navigator.addView(MENU.toString(), new MenuPageImpl((MyUI) ui, navigator));
            navigator.navigateTo(MENU.toString());
        }
    }

    private void showNoSuchUserNotification() {
        Notification notification = new Notification(
                "Wrong credentials", Notification.Type.WARNING_MESSAGE);
        notification.setDelayMsec(3000);
        notification.show(Page.getCurrent());
    }

    public void createListenerForSingUpButton(SignPanelImpl signPanel) {
        Window signUpWindow = signPanel.getSignUpWindow();
        SignUpPanelImpl signUpPanel = signPanel.getSignUpPanel();
        Button signUpButton = signUpPanel.getSignUpButton();

        signUpButton.addClickListener(e -> {
            String userName = signUpPanel.readName();
            String userSurname = signUpPanel.readSurname();
            String userEmail = signUpPanel.readEmail();
            String userPassword = signUpPanel.readPassword();
            String userEducation = signUpPanel.readEducation();
            Date userBirthDate = signUpPanel.readBirthDate();

            int signUpResult = DatabaseManager.signUpUser(
                    userName, userSurname, userEmail,
                    userBirthDate, userPassword, userEducation);
            showSignUpResult(signUpResult, signUpWindow);
        });
    }

    private void showSignUpResult(int signUpResult, Window signUpWindow) {
        switch (signUpResult) {
            case 0:
                Notification notification = new Notification("You have signed up successfully!");
                notification.setDelayMsec(3000);
                notification.setStyleName("signUpNotification");
                notification.show(Page.getCurrent());
                UI.getCurrent().removeWindow(signUpWindow);
                break;
            case 1:
                Notification.show("User with the same e-mail already exists.",
                        Notification.Type.ERROR_MESSAGE);
                break;
            default:
                Notification.show("Unknown result-code: " + signUpResult,
                        Notification.Type.ERROR_MESSAGE);
                break;
        }
    }
}
