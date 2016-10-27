package org.test.customcomponents;

import com.vaadin.ui.VerticalLayout;
import org.test.MyUI;
import org.test.tamplets.MainPage;
import org.test.tamplets.SignPanel;

/**
 * Created by Аркадий on 23.10.2016.
 */
public class MainPageImpl extends MainPage {
    public MainPageImpl(VerticalLayout basicLayout) {
        SignPanel signPanel = new SignPanelImpl(basicLayout);
        undersignLayout.addComponent(signPanel);
        /*SignPanelImpl signPanel = new SignPanelImpl();
        signPanel.setSizeFull();
        undersignLayout.addComponent(signPanel);*/
    }
}
