package org.test.customcomponents.menupage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.test.controllers.menupage.ClassPageController;
import org.test.tamplets.menupage.ClassPage;

/**
 * Created by abara on 09.11.2016.
 */
public class ClassPageImpl extends ClassPage implements View {
    private final ClassPageController controller;

    public ClassPageImpl() {
        controller = new ClassPageController(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        controller.enterLesson();
    }
}
