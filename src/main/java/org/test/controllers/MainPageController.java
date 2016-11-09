package org.test.controllers;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.customcomponents.MainPageImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Profile;

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
            Button signInButton, Navigator navigator,
            String userEmail, String userPassword) {
        signInButton.addClickListener(e -> {
            //TODO: uncomment
//            if(DatabaseManager.doesUserExist(userEmail, userPassword)) {
                Profile.fulfillProfile(Profile.getCurrentProfile(), userEmail);
                navigator.navigateTo(MENU_PAGE.toString());
//            }
        });
    }

    public void createListenerForSingUpButton(
            Button signUpButton, Window signUpWindow,
            String userName, String userSurname,
            String userEmail, String userPassword,
            String userEducation, Date userBirthDate) {
        signUpButton.addClickListener(e -> {
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
