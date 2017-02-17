package org.test.msgservice;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.test.dbservice.DatabaseManager;
import org.test.logic.InboxMessage;
import org.test.logic.Profile;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by abara on 17.11.2016.
 */
public class UIAlterationManager {
    private static Map<Integer, VaadinSession> sessions = new TreeMap<>();

    public static void registerSession(int userId, VaadinSession session) {
        sessions.put(userId, session);
    }

    public static void unregisterSession(int id) {
        sessions.remove(id);
    }

    public static void sendChatMessage(ChatMessage message)
            throws NoSuchSessionException {
        boolean isAccessible = isReceiverSessionAccessible(message);
        if(isAccessible) {
            sendChatMessageToAllReceiverUIs(message);
            showSentChatMessageInAllSenderUIs(message);
        } else {
            throw new NoSuchSessionException();
        }
    }

    private static boolean isReceiverSessionAccessible(ChatMessage message) {
        int receiverId = message.getReceiverId();
        VaadinSession receiverSession = sessions.get(receiverId);
        if(receiverSession == null) {
            return false;
        } else if(receiverSession.getState() != VaadinSession.State.OPEN) {
            sessions.remove(receiverId);
            return false;
        } else {
            return true;
        }
    }

    private static void sendChatMessageToAllReceiverUIs(ChatMessage message) {
        int receiverId = message.getReceiverId();
        for(UI ui: sessions.get(receiverId).getUIs()) {
            ((UIAlterationListener) ui).receiveChatMessage(message);
        }
    }

    private static void showSentChatMessageInAllSenderUIs(ChatMessage message) {
        for (UI ui : VaadinSession.getCurrent().getUIs()) {
            ((UIAlterationListener) ui).showSentChatMessage(message);
        }
    }

    public static void showAddedFriendInAllUIs(Profile profile) {
        for (UI ui : VaadinSession.getCurrent().getUIs()) {
            ((UIAlterationListener) ui).showAddedFriend(profile);
        }
    }

    public static void sendInboxMessage(InboxMessage message) {
        DatabaseManager.storeInboxMessage(message);
        showSentInboxMessageInAllSenderUIs(message);
        sendInboxMessageToAllReceiverUIs(message);
    }

    private static void showSentInboxMessageInAllSenderUIs(InboxMessage message) {
        for(UI ui: VaadinSession.getCurrent().getUIs()) {
            ((UIAlterationListener) ui).showSentInboxMessage(message);
        }
    }

    private static void sendInboxMessageToAllReceiverUIs(InboxMessage message) {
        int receiverId = message.getReceiverProfile().getId();
        VaadinSession receiverSession = sessions.get(receiverId);
        if(receiverSession != null) {
            for (UI ui: receiverSession.getUIs()) {
                ((UIAlterationListener) ui).receiveInboxMessage(message);
            }
        }
    }


    public interface UIAlterationListener {
        void receiveChatMessage(ChatMessage message);
        void showSentChatMessage(ChatMessage message);

        void receiveInboxMessage(InboxMessage message);
        void showSentInboxMessage(InboxMessage message);

        void showAddedFriend(Profile profile);
    }

    public static class NoSuchSessionException extends Exception {

    }
}
