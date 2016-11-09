package org.test.customcomponents;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import org.test.tamplets.SignUpPanel;

import java.util.Date;

/**
 * Created by Аркадий on 27.10.2016.
 */
class SignUpPanelImpl extends SignUpPanel {
    SignUpPanelImpl(Window signUpWindow) {
        passwordTextField.addValidator(new PasswordEqualsValidator());
        repeatPasswordTextField.setImmediate(true);
        repeatPasswordTextField.addValueChangeListener(e -> {
            passwordTextField.markAsDirty();
            passwordTextField.isValid();
        });
    }

    Button getSignUpButton() {
        return signUpButton;
    }

    String readName() {
        return nameTextField.getValue();
    }

    String readSurname() {
        return surnameTextField.getValue();
    }

    String readEmail() {
        return emailTextFeild.getValue();
    }

    String readPassword() {
        return passwordTextField.getValue();
    }

    String readEducation() {
        return educationTextArea.getValue();
    }

    Date readBirthDate() {
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
