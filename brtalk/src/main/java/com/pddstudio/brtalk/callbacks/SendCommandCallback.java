package com.pddstudio.brtalk.callbacks;

import com.afollestad.materialdialogs.MaterialDialog;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public interface SendCommandCallback {
    MaterialDialog onPrepare();
    void onReplyReceived(boolean sendSuccess, Chat chat, Message message, MaterialDialog dialog);
    void onConnectionFailure(MaterialDialog dialog);
    void onExceptionOccurred(Exception exception, MaterialDialog dialog);
}
