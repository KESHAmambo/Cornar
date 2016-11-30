package org.test.utils;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import org.test.customcomponents.menupage.profilepage.inboxpage.InboxComposePanelImpl;

/**
 * Created by abara on 20.11.2016.
 */
public class UIHelper {
    private UIHelper() {

    }

    public static void showSuccessNotification(String caption, String styleName) {
        Notification notification = new Notification(caption);
        notification.setDelayMsec(3000);
        notification.setStyleName(styleName);
        notification.show(Page.getCurrent());
    }
}
