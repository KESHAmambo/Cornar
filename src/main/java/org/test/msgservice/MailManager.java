package org.test.msgservice;

import org.test.logic.InboxMessage;
import org.test.logic.Profile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;


/**
 * Created by abara on 16.12.2016.
 */
public class MailManager {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private MailManager() {

    }

    public static void sendMail(InboxMessage inboxMessage) {
        final String username = "cornar.ltd@gmail.com";
        final String password = "417GroupCRN";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cornar.ltd@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(inboxMessage.getReceiverProfile().getEmail()));
            message.setSubject(inboxMessage.getTheme());
            Profile senderProfile = inboxMessage.getSenderProfile();
            String header = senderProfile.getName() + " " + senderProfile.getSurname() +
                    "(" + dateFormat.format(inboxMessage.getDate()) +
                    ";email:" + senderProfile.getEmail() + "):\n";
            message.setText(
                    "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<body>\n" +
                            "<h3>" + header +
                            inboxMessage.getText() +
                            "\n" +
                            "</body>\n" +
                            "</html>",
                    "utf-8", "html");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
