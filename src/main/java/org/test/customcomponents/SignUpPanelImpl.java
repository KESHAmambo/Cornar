package org.test.customcomponents;

import org.test.tamplets.SignUpPanel;

/**
 * Created by Аркадий on 27.10.2016.
 */
public class SignUpPanelImpl extends SignUpPanel {
    public SignUpPanelImpl() {
        nameTextField.setInputPrompt("Name");
        surnameTextField.setInputPrompt("Surname");
        passwordTextField.setInputPrompt("Password");
        repeatPasswordTextField.setInputPrompt("Repeat password");
        educationTextArea.setInputPrompt("Education");
    }
}
