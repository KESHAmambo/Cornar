package org.test.customcomponents;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.tamplets.SignPanel;

/**
 * Created by Аркадий on 23.10.2016.
 */
public class SignPanelImpl extends SignPanel {
    private Window signUpWindow;
    private SignUpPanelImpl signUpPanel;

    SignPanelImpl() {
        signUpWindow = new Window("Sign Up");
        signUpPanel = new SignUpPanelImpl(signUpWindow);

        customizeSignUpWindow();
        createListenerFopeningSignUpWindowButton();
    }

    public Button getSignInButton() {
        return signInButton;
    }

    public SignUpPanelImpl getSignUpPanel() {
        return signUpPanel;
    }

    public Window getSignUpWindow() {
        return signUpWindow;
    }

    public String getUserEmail() {
        return emailTextField.getValue();
    }

    public String getUserPassword() {
        return passwordTextField.getValue();
    }

    private void createListenerFopeningSignUpWindowButton() {
        openingSignUpWindowButton.addClickListener(e -> {
            UI.getCurrent().addWindow(signUpWindow);
        });
    }

    private Window customizeSignUpWindow() {
        signUpWindow.setContent(signUpPanel);
        signUpWindow.addStyleName("singUpWindow");
        signUpWindow.center();
        signUpWindow.setModal(true);
        signUpWindow.setWidth("60%");
        signUpWindow.setHeight("80%");
        return signUpWindow;
    }
}
