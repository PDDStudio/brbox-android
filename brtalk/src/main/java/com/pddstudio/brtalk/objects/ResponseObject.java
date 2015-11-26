package com.pddstudio.brtalk.objects;

import android.support.annotation.Nullable;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ResponseObject {

    private final Chat chat;
    private final Message message;
    private final String identifier;
    private final Date responseDate;

    public ResponseObject(@Nullable String identifier, Chat chat, Message message) {
        this.chat = chat;
        this.message = message;
        this.identifier = identifier;
        this.responseDate = GregorianCalendar.getInstance(Locale.getDefault()).getTime();
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

    public Date getResponseDate() {
        return responseDate;
    }

}
