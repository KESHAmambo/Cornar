package org.test.customcomponents.menupage.profilepage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.test.controllers.menupage.profilepage.SchedulePageController;
import org.test.tamplets.menupage.profilepage.SchedulePage;

/**
 * Created by abara on 10.11.2016.
 */
public class SchedulePageImpl extends SchedulePage implements View {
    private final SchedulePageController controller;

    public SchedulePageImpl() {
        controller = new SchedulePageController(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        controller.updateSchedule();
    }
}
