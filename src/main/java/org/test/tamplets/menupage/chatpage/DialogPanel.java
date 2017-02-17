package org.test.tamplets.menupage.chatpage;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class DialogPanel extends VerticalLayout {
    protected Label friendNameLabel;
    protected VerticalLayout messagesLayout;
    protected TextArea messageTextArea;
    protected Button sendMessageButton;

    public DialogPanel() {
        Design.read(this);
    }

    public Label getFriendNameLabel() {
        return friendNameLabel;
    }

    public VerticalLayout getMessagesLayout() {
        return messagesLayout;
    }

    public TextArea getMessageTextArea() {
        return messageTextArea;
    }

    public Button getSendMessageButton() {
        return sendMessageButton;
    }
}
