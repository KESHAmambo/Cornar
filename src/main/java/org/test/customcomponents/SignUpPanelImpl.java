package org.test.customcomponents;

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
        createListenerForSingUpButton(signUpWindow);
    }

    private void createListenerForSingUpButton(Window signUpWindow) {
        //TODO check fields validity, notifications
        signUpButton.addClickListener(e -> {
            String name = nameTextField.getValue();
            String surname = nameTextField.getValue();
            String email = emailTextFeild.getValue();
            String password = passwordTextField.getValue();
            String education = educationTextArea.getValue();
            Date birthDate = birthDateField.getValue();

            DatabaseService dbService = DatabaseServiceFactory.getService();
            int signedUp = dbService.signUpUser(name, surname, email, birthDate, password, education);
            if (signedUp != 0) {
                Notification notification = new Notification("You have signed up");
                notification.show("You have signed up");
                UI.getCurrent().removeWindow(signUpWindow);
            }
        });
    }
}
