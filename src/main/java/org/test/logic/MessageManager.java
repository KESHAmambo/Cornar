package org.test.logic;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by abara on 17.11.2016.
 */
public class MessageManager {
    private static Map<Integer, MessageListener> listeners = new TreeMap<>();

    static void registerListener(int listenerId, MessageListener listener) {
        listeners.put(listenerId, listener);
    }

    public static void detachMessageListener(int id) {
        listeners.remove(id);
    }

    public static void sendChatMessage(ChatMessage message)
            throws NullPointerException {
        MessageListener receiver = listeners.get(message.getReceiverId());
        receiver.receiveChatMessage(message);
    }


    public interface MessageListener {
        void receiveChatMessage(ChatMessage message);
    }
}
