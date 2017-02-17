package org.test.logic;

import java.util.Date;

/**
 * Created by abara on 22.11.2016.
 */
public class InboxMessage {
    private final Profile senderProfile;
    private final Profile receiverProfile;
    private final Date date;
    private final String theme;
    private final String text;
    private boolean wasRead;

    public InboxMessage(
            Profile senderProfile, Profile receiverProfile,
            Date date, String theme,
            String text, boolean wasRead) {
        this.senderProfile = senderProfile;
        this.receiverProfile = receiverProfile;
        this.date = date;
        this.theme = theme;
        this.text = text;
        this.wasRead = wasRead;
    }

    public Profile getSenderProfile() {
        return senderProfile;
    }

    public Profile getReceiverProfile() {
        return receiverProfile;
    }

    public Date getDate() {
        return date;
    }

    public String getTheme() {
        return theme;
    }

    public String getText() {
        return text;
    }

    public boolean isWasRead() {
        return wasRead;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }
}
