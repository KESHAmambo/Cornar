package org.test.controllers.menupage.profilepage;

import org.test.customcomponents.menupage.profilepage.InboxPageImpl;
import org.test.logic.InboxMessage;

import java.util.Date;
import java.util.List;

/**
 * Created by abara on 11.11.2016.
 */
public class InboxPageController {
    private final InboxPageImpl inboxPage;

    public InboxPageController(InboxPageImpl inboxPage) {
        this.inboxPage = inboxPage;
    }

    public void sortMessages(List<InboxMessage> messages) {
        messages.sort((InboxMessage m1, InboxMessage m2) -> {
            Date date1 = m1.getDate();
            Date date2 = m2.getDate();
            return date2.compareTo(date1);
        });
    }
}
