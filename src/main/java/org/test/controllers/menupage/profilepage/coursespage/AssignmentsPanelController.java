package org.test.controllers.menupage.profilepage.coursespage;

import com.vaadin.ui.VerticalLayout;
import org.test.customcomponents.menupage.friendspage.ProfileBoxImpl;
import org.test.customcomponents.menupage.profilepage.coursespage.AssignmentsPanelImpl;
import org.test.logic.Profile;

import java.util.List;
import java.util.Set;

/**
 * Created by abara on 30.11.2016.
 */
public class AssignmentsPanelController {
    private final AssignmentsPanelImpl assignmentsPanel;

    public AssignmentsPanelController(AssignmentsPanelImpl assignmentsPanel) {
        this.assignmentsPanel = assignmentsPanel;
    }

    public void fulfillPage(Set<Profile> assignedStudents) {
        VerticalLayout mainLayout = assignmentsPanel.getMainLayout();
        for (Profile profile: assignedStudents) {
            mainLayout.addComponentAsFirst(new ProfileBoxImpl(profile));
        }
    }
}
