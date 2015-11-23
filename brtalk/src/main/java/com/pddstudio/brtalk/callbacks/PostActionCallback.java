package com.pddstudio.brtalk.callbacks;

import android.support.annotation.Nullable;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
//used to send commands to the target
public interface PostActionCallback {
    @Nullable ChatMessageListener getCustomMessageListenerCallback();
    void onPostAction();
    void onPostActionFailed(@Nullable Exception exception);
    void onAnswerReceived(Chat chat, Message message);
}
