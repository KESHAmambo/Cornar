package org.test.customcomponents;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.dbservice.DatabaseService;
import org.test.dbservice.DatabaseServiceFactory;
import org.test.tamplets.SignUpPanel;

import java.net.DatagramSocketImpl;
import java.util.Date;

/**
 * Created by Аркадий on 27.10.2016.
 */
public class SignUpPanelImpl extends SignUpPanel {
    public SignUpPanelImpl(Window signUpWindow) {
        //passwordTextField.addValidator(new PasswordValidator());
        passwordTextField.addValidator(new PasswordEqualsValidator());
        repeatPasswordTextField.setImmediate(true);
        repeatPasswordTextField.addValueChangeListener(e -> {
            passwordTextField.markAsDirty();
            passwordTextField.isValid();
        });
        createListenerForSingUpButton(signUpWindow);
    }

    private void createListenerForSingUpButton(Window signUpWindow) {
        signUpButton.addClickListener(e -> {
            String name = nameTextField.getValue();
            String surname = nameTextField.getValue();
            String email = emailTextFeild.getValue();
            String password = passwordTextField.getValue();
            String confirmPassword = repeatPasswordTextField.getValue();
            String education = educationTextArea.getValue();
            Date birthDate = birthDateField.getValue();

            DatabaseService dbService = DatabaseServiceFactory.getService();
            int signedUp = dbService.signUpUser(name, surname, email, birthDate, password, education);
            if (signedUp != 0) {
                Notification notification = new Notification("You have signed up");
                notification.show("You have signed up");
            }
        });
    }

     class PasswordEqualsValidator
            extends AbstractValidator<String> {

        public PasswordEqualsValidator() {
            super("The password  is not equals");
        }

        @Override
        protected boolean isValidValue(String value) {
            String password = value;
            if (value != null  && (value.length() < 8 ) || !value.matches(".*\\d.*")){
                return false;
            }
            String newPassword = repeatPasswordTextField.getValue();
            return newPassword.equals(password);
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

}
