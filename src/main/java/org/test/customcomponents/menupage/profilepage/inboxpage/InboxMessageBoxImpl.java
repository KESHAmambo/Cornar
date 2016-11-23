package org.test.customcomponents.menupage.profilepage.inboxpage;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.test.logic.InboxMessage;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.inboxpage.InboxMessageBox;
import org.test.tamplets.menupage.profilepage.inboxpage.InboxReadPanel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by abara on 22.11.2016.
 */
public class InboxMessageBoxImpl extends InboxMessageBox {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH);
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    private final InboxMessage message;

    public InboxMessageBoxImpl(InboxMessage message) {
        this.message = message;

        setTheme();
        fulfillLabels();
        createListenerForReadMessageButton();
    }

    private void setTheme() {
        Profile senderProfile = message.getSenderProfile();
        if(Profile.getCurrentProfile().equals(senderProfile)) {
            mainLayout.setStyleName("sentInboxMessageBoxLayout");
            readButton.addStyleName("sentInboxMessageReadButton");
        } else {
            mainLayout.setStyleName("receivedInboxMessageBoxLayout");
            readButton.addStyleName("receivedInboxMessageReadButton");
        }
    }

    private void fulfillLabels() {
        Profile senderProfile = message.getSenderProfile();
        Profile receiverProfile = message.getReceiverProfile();

        senderNameLabel.setValue(senderProfile.getName() + " " + senderProfile.getSurname());
        senderEmailLabel.setValue(senderProfile.getEmail());
        receiverNameLabel.setValue(receiverProfile.getName() + " " + receiverProfile.getSurname());
        receiverEmailLabel.setValue(receiverProfile.getEmail());
        themeLabel.setValue(message.getTheme());
        Date date = message.getDate();
        dateLabel.setValue(dateFormat.format(date));
        timeLabel.setValue(timeFormat.format(date));        
    }

    private void createListenerForReadMessageButton() {
        readButton.addClickListener(e -> {
            Window readWindow = createReadWindow();
            UI.getCurrent().addWindow(readWindow);
        });
    }

    private Window createReadWindow() {
        Window window = new Window();
        window.setContent(new InboxReadPanelImpl(message));
        window.center();
        window.setModal(true);
        window.setWidth("50%");
        window.setHeight("90%");
        return window;
    }
}
