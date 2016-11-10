package org.test.customcomponents;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import org.test.tamplets.SignUpPanel;

import java.util.Date;

/**
 * Created by Аркадий on 27.10.2016.
 */
public class SignUpPanelImpl extends SignUpPanel {
    SignUpPanelImpl(Window signUpWindow) {
        passwordTextField.addValidator(new PasswordEqualsValidator());
        repeatPasswordTextField.setImmediate(true);
        repeatPasswordTextField.addValueChangeListener(e -> {
            passwordTextField.markAsDirty();
            passwordTextField.isValid();
        });
    }

    public Button getSignUpButton() {
        return signUpButton;
    }

    public String readName() {
        return nameTextField.getValue();
    }

    public String readSurname() {
        return surnameTextField.getValue();
    }

    public String readEmail() {
        return emailTextFeild.getValue();
    }

    public String readPassword() {
        return passwordTextField.getValue();
    }

    public String readEducation() {
        return educationTextArea.getValue();
    }

    public Date readBirthDate() {
        return birthDateField.getValue();
    }

    private class PasswordEqualsValidator extends AbstractValidator<String> {
        PasswordEqualsValidator() {
            super("Passwords are not equal");
        }

        @Override
        protected boolean isValidValue(String value) {
            if (value == null || (value.length() < 8 ) || !value.matches(".*\\d.*")){
                return false;
            }
            String newPassword = repeatPasswordTextField.getValue();
            return newPassword.equals(value);
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

}
