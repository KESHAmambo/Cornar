package org.test.customcomponents.menupage.profilepage.coursespage;

import org.test.controllers.menupage.profilepage.coursespage.AssignmentsPanelController;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.coursespage.AssignmentsPanel;

import java.util.Set;

/**
 * Created by abara on 30.11.2016.
 */
public class AssignmentsPanelImpl extends AssignmentsPanel {
    private final AssignmentsPanelController controller;

    public AssignmentsPanelImpl(Set<Profile> assignedStudents) {
        controller = new AssignmentsPanelController(this);

        controller.fulfillPage(assignedStudents);
    }
}
