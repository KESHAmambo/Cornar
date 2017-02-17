package org.test.customcomponents.menupage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.test.MyUI;
import org.test.controllers.menupage.ClassPageController;
import org.test.tamplets.menupage.ClassPage;

/**
 * Created by abara on 09.11.2016.
 */
public class ClassPageImpl extends ClassPage implements View {
    private final ClassPageController controller;
    private final MyUI myUI;

    public ClassPageImpl(MyUI myUI) {
        controller = new ClassPageController(this);
        this.myUI = myUI;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        controller.tryEnterLesson(myUI);
    }
}
