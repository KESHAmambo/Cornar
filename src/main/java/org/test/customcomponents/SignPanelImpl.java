package org.test.customcomponents;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.test.dbservice.DatabaseService;
import org.test.dbservice.DatabaseServiceFactory;
import org.test.tamplets.MenuPage;
import org.test.tamplets.SignPanel;
import org.test.tamplets.SignUpPanel;

/**
 * Created by Аркадий on 23.10.2016.
 */
public class SignPanelImpl extends SignPanel {
    public SignPanelImpl(VerticalLayout basicLayout) {
        createListenerForSingInButton(basicLayout);
        createListenerForSignUpButton();
    }

    private void createListenerForSignUpButton() {
        Window signUpWindow = createSignUpWindow();

        signUpButton.addClickListener(e -> {
            UI.getCurrent().addWindow(signUpWindow);
        });
    }

    private Window createSignUpWindow() {
        Window signUpWindow = new Window("Sign Up");
        SignUpPanel signUpPanel = new SignUpPanelImpl(signUpWindow);
        signUpWindow.setContent(signUpPanel);
        signUpWindow.addStyleName("singUpWindow");
        signUpWindow.center();
        signUpWindow.setModal(true);
        signUpWindow.setWidth("60%");
        signUpWindow.setHeight("80%");
        return signUpWindow;
    }

    private void createListenerForSingInButton(VerticalLayout basicLayout) {
        MenuPage menuPage = new MenuPageImpl();

        signInButton.addClickListener(e -> {
            //TODO

            String emailOfUser = emailTextField.getValue();
            String password = this.passwordTextField.getValue();
            if (!isValidUser(emailOfUser,password)) {
                this.passwordTextField.focus();
                return;
            }
            basicLayout.removeAllComponents();
            basicLayout.addComponent(menuPage);
            //VaadinService.getCurrentRequest().getWrappedSession().setAttribute("user", emailOfUser);
//            for(UI ui: VaadinSession.getCurrent().getUIs()) {
//                ui.access(() -> {
//                    ui.getPage().setLocation("/login/");
//                });
//            }
//            getSession().close();
        });
    }
    private boolean isValidUser(String email, String password){
        DatabaseService dbService= DatabaseServiceFactory.getService();
        return dbService.isUserExist(email, password);
    }


}
