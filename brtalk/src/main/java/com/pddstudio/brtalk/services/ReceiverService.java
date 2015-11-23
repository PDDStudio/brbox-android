package com.pddstudio.brtalk.services;

import android.util.Log;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ReceiverService implements ChatManagerListener, ChatMessageListener {

    private final ChatManager chatManager;

    private ReceiverService(ChatManager chatManager) {
        this.chatManager = chatManager;
        this.chatManager.addChatListener(this);
        Log.d("ReceiverService", "created instance with given chat manager");
    }

    public static ReceiverService register(ChatManager chatManager) {
        return new ReceiverService(chatManager);
    }

    @Override
    public void chatCreated(Chat chat, boolean createdLocally) {
        if(!createdLocally) {
            chat.addMessageListener(this);
        }
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        Log.d("ReceiverService", "Message from: " + chat.getParticipant() + " : '" + message.getBody() + "'");
    }
}
