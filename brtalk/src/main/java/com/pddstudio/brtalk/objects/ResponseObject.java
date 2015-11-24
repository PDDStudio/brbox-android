package com.pddstudio.brtalk.objects;

import android.support.annotation.Nullable;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ResponseObject {

    private final Chat chat;
    private final Message message;
    private final String identifier;

    public ResponseObject(@Nullable String identifier, Chat chat, Message message) {
        this.chat = chat;
        this.message = message;
        this.identifier = identifier;
    }

    public Chat getChat() {
        return chat;
    }

    public Message getMessage() {
        return message;
    }

    public String getIdentifier() {
        return identifier;
    }

}
