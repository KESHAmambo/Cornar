package org.test.customcomponents;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import org.test.dbservice.utils.PasswordUtils;
import org.test.tamplets.SignUpPanel;

import java.util.Date;

/**
 * Created by Аркадий on 27.10.2016.
 */

public class SignUpPanelImpl extends SignUpPanel {
    SignUpPanelImpl() {

        createValidatorToPasswordField();
        createValidatorToEmailField();

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
        if (!emailTextFeild.isValid()) {
            Notification.show("Please use correct inputs");
            return null;
        }
        return emailTextFeild.getValue();
    }

    public String readPassword() {
        if (!passwordTextField.isValid()) {
            Notification.show("Please use correct inputs");
            return null;
        }
        char[] password = passwordTextField.getValue().toCharArray();
        return new String(PasswordUtils.hash(password));
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
            String newPassword = repeatPasswordTextField.getValue();
            return newPassword.equals(value);
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private class PasswordStrongValidator extends AbstractValidator<String> {
        PasswordStrongValidator() {
            super("Password is not enough strong");
        }

        @Override
        protected boolean isValidValue(String value) {
            if (value == null || (value.length() < 8 ) || !value.matches(".*\\d.*")){
                return false;
            }
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }
    private class EmailsValidator extends  AbstractValidator<String> {
        private static final String  emailRegExp =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        public EmailsValidator() {
            super("Incorrect email");
        }

        @Override
        protected boolean isValidValue(String value) {
            if (value.matches(emailRegExp))
                return true;
            return false;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

    private void createValidatorToPasswordField(){
        passwordTextField.addValidator(new PasswordEqualsValidator());
        passwordTextField.addValidator(new PasswordStrongValidator());
        repeatPasswordTextField.setImmediate(true);
        passwordTextField.setNullRepresentation("");
        passwordTextField.setNullSettingAllowed(true);
        passwordTextField.setValidationVisible(false);
        passwordTextField.addTextChangeListener(event -> {
            passwordTextField.setValue(event.getText());
            try {
                passwordTextField.setCursorPosition(event.getCursorPosition());
                passwordTextField.validate();
            }catch (Validator.InvalidValueException e){
                passwordTextField.setValidationVisible(true);
            }
        });
        repeatPasswordTextField.addValueChangeListener(e -> {
            passwordTextField.setValidationVisible(true);
            passwordTextField.isValid();
            passwordTextField.markAsDirty();
        });
    }

    private void createValidatorToEmailField(){
        emailTextFeild.addValidator(new EmailsValidator());
        emailTextFeild.setImmediate(true);
        emailTextFeild.setValidationVisible(false);
        emailTextFeild.addTextChangeListener(event -> {
            emailTextFeild.setValue(event.getText());
            try {
                emailTextFeild.setCursorPosition(event.getCursorPosition());
                emailTextFeild.validate();
            }catch (Validator.InvalidValueException e){
                emailTextFeild.setValidationVisible(true);
            }
        });
    }

}
