package com.pddstudio.brtalk.managers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brtalk.BrTalk;
import com.pddstudio.brtalk.callbacks.PostActionCallback;
import com.pddstudio.brtalk.objects.BoxCommand;
import com.pddstudio.brtalk.objects.SingleContact;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class CommandManager implements ChatMessageListener {

    private final BrTalk brTalk;
    private PostActionCallback postActionCallback;

    private CommandManager(BrTalk brTalk) {
        this.brTalk = brTalk;
    }

    public static CommandManager getInstanceFor(BrTalk brTalk) {
        return new CommandManager(brTalk);
    }

    public void sendCommand(@NonNull SingleContact contact, @NonNull BoxCommand boxCommand) {
        Log.d("CommandManager", "sendCommand() called for " + contact.getConnectionId() + "|" + contact.getName() + " -> " + boxCommand.getCommandTarget());
        if(brTalk.hasSendCommandCallback()) brTalk.getSendCommandCallback().onPrepare();
        if(brTalk.getXmppConnection().isConnected()) {
            ChatManager chatManager = ChatManager.getInstanceFor(brTalk.getXmppConnection());
            Chat chat = chatManager.createChat(contact.getConnectionId());
            try {
                Message message = new Message();
                message.setType(Message.Type.chat);
                message.setBody(boxCommand.getCommandTarget());
                chat.sendMessage(message);
                chat.addMessageListener(this);
            } catch (Exception e) {
                //if(brTalk.hasSendCommandCallback()) brTalk.getSendCommandCallback().onExceptionOccurred(e, dialog);
                //else Log.e("CommandManager", "Exception thrown, but no callback given!");
                e.printStackTrace();
            }
        } else {
            //if(brTalk.hasSendCommandCallback()) brTalk.getSendCommandCallback().onConnectionFailure(dialog);
            //else Log.e("CommandManager", "unable to send command! No callback given.");
        }
    }

    public void sendCommand(@NonNull SingleContact contact, @NonNull BoxCommand boxCommand, @NonNull PostActionCallback postActionCallback) {
        this.postActionCallback = postActionCallback;
        postActionCallback.onPostAction();
        if(brTalk.getXmppConnection().isConnected()) {
            ChatManager chatManager = ChatManager.getInstanceFor(brTalk.getXmppConnection());
            Chat chat = chatManager.createChat(contact.getConnectionId());
            try {
                Message message = new Message();
                message.setType(Message.Type.chat);
                message.setBody(boxCommand.getCommandName());
                chat.addMessageListener(this);
                if(postActionCallback.getCustomMessageListenerCallback() != null) chat.addMessageListener(postActionCallback.getCustomMessageListenerCallback());
                chat.sendMessage(message);
            } catch (Exception e) {
                postActionCallback.onPostActionFailed(e);
                Log.e("CommandManager", "Exception thrown, but no callback given!");
                e.printStackTrace();
            }
        } else {
            postActionCallback.onPostActionFailed(null);
            Log.e("CommandManager", "unable to send command! No callback given.");
        }
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        postActionCallback.onAnswerReceived(chat, message);
    }
}
