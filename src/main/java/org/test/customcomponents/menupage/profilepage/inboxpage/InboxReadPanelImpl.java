package org.test.customcomponents.menupage.profilepage.inboxpage;

import org.test.logic.InboxMessage;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.inboxpage.InboxReadPanel;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by abara on 22.11.2016.
 */
public class InboxReadPanelImpl extends InboxReadPanel {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy HH:mm", Locale.ENGLISH);

    public InboxReadPanelImpl(InboxMessage message) {
        fulfillLabels(message);
    }

    private void fulfillLabels(InboxMessage message) {
        Profile senderProfile = message.getSenderProfile();
        Profile receiverProfile = message.getReceiverProfile();

        senderNameLabel.setValue(senderProfile.getName() + " " + senderProfile.getSurname());
        senderEmailLabel.setValue(senderProfile.getEmail());
        receiverNameLabel.setValue(receiverProfile.getName() + " " + receiverProfile.getSurname());
        receiverEmailLabel.setValue(receiverProfile.getEmail());
        themeLabel.setValue(message.getTheme());
        dateLabel.setValue(dateFormat.format(message.getDate()));
        richTextArea.setValue(message.getText());
    }
}
