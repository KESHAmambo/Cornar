package org.test.customcomponents;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.test.MyUI;
import org.test.tamplets.MenuPage;
import org.test.tamplets.SignPanel;
import org.test.tamplets.SignUpPanel;

/**
 * Created by Аркадий on 23.10.2016.
 */
public class SignPanelImpl extends SignPanel {
    public SignPanelImpl(VerticalLayout basicLayout) {
        MenuPage menuPage = new MenuPageImpl();
        signInButton.addClickListener(e -> {
            //TODO
            basicLayout.removeAllComponents();
            basicLayout.addComponent(menuPage);

//            for(UI ui: VaadinSession.getCurrent().getUIs()) {
//                ui.access(() -> {
//                    ui.getPage().setLocation("/login/");
//                });
//            }
//            getSession().close();
        });

        SignUpPanel signUpPanel = new SignUpPanelImpl();
        signUpButton.addClickListener(e -> {
            Window signUpWindow = new Window("Sign Up", signUpPanel);
            signUpWindow.center();
            signUpWindow.setModal(true);
            signUpWindow.setWidth("50%");
            signUpWindow.setHeight("50%");
            UI.getCurrent().addWindow(signUpWindow);
        });
    }
}
