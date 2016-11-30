package org.test.customcomponents.menupage.profilepage.inboxpage;

import com.vaadin.ui.Window;
import org.test.controllers.menupage.profilepage.inboxpage.InboxComposePanelController;
import org.test.tamplets.menupage.profilepage.inboxpage.InboxComposePanel;

/**
 * Created by abara on 22.11.2016.
 */
public class InboxComposePanelImpl extends InboxComposePanel {
    private final InboxComposePanelController controller;

    public InboxComposePanelImpl(Window window) {
        controller = new InboxComposePanelController(this);

        controller.createListenerForSendMessageButton(window);
    }
}
