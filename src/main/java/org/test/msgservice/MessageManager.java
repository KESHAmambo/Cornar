package org.test.msgservice;

import com.vaadin.server.SessionExpiredException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.test.MyUI;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by abara on 17.11.2016.
 */
public class MessageManager {
    private static Map<Integer, VaadinSession> sessions = new TreeMap<>();

    public static void registerSession(int userId, VaadinSession session) {
        sessions.put(userId, session);
    }

    public static void sendChatMessage(ChatMessage message)
            throws NullPointerException, SessionExpiredException {
        VaadinSession session = sessions.get(message.getReceiverId());
        checkIfSessionAccessible(session, message.getReceiverId());
        for(UI ui: session.getUIs()) {
            ((MyUI) ui).receiveChatMessage(message);
        }
    }

    private static void checkIfSessionAccessible(VaadinSession session, Integer receiverId)
            throws SessionExpiredException {
        if(session == null) {
            throw new NullPointerException("No session with such receiverId is in the sessions list.");
        }
        else if(session.getState() != VaadinSession.State.OPEN) {
            sessions.remove(receiverId);
            throw new SessionExpiredException();
        }
    }

    public static void unregisterPreviousUserSession(int id) {
        sessions.remove(id);
    }


    public interface MessageListener {
        void receiveChatMessage(ChatMessage message);

        void showSentChatMessage(ChatMessage message);
    }
}
