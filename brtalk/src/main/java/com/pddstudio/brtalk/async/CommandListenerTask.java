package com.pddstudio.brtalk.async;

import android.os.AsyncTask;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brtalk.BrTalk;
import com.pddstudio.brtalk.callbacks.SendCommandCallback;
import com.pddstudio.brtalk.objects.BoxCommand;
import com.pddstudio.brtalk.objects.SingleContact;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class CommandListenerTask extends AsyncTask<Void, Void, Void> implements ChatMessageListener {

    private final SendCommandCallback sendCommandCallback;
    private final BrTalk brTalk;
    private final SingleContact singleContact;
    private final BoxCommand boxCommand;

    private ChatManager chatManager;
    private Chat chat;
    private Message message;

    private MaterialDialog dialog;

    private Chat rChat;
    private Message rMsg;
    private boolean msgReceived = false;

    public CommandListenerTask(BrTalk brTalk, SingleContact contact, BoxCommand command, SendCommandCallback sendCommandCallback) {
        this.sendCommandCallback = sendCommandCallback;
        this.singleContact = contact;
        this.boxCommand = command;
        this.brTalk = brTalk;
    }

    @Override
    public void onPreExecute() {
        this.dialog = sendCommandCallback.onPrepare();
        this.dialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        chatManager = ChatManager.getInstanceFor(brTalk.getXmppConnection());
        chat = chatManager.createChat(singleContact.getConnectionId());
        message = new Message();
        message.setType(Message.Type.chat);
        message.setBody(boxCommand.getCommandName());
        try {
            chat.addMessageListener(CommandListenerTask.this);
            chat.sendMessage(message);
        } catch (Exception e) {
            sendCommandCallback.onExceptionOccurred(e, dialog);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... voids) {
        Log.d("CommandListenerTask", "onProgressUpdate() : called");
        sendCommandCallback.onReplyReceived(true, rChat, rMsg, dialog);
    }

    @Override
    protected void onPostExecute(Void v) {
        Log.d("CommandListenerTask", "onProgressUpdate() : stopped");
    }


    @Override
    public void processMessage(Chat chat, Message message) {
        this.rChat = chat;
        this.rMsg = message;
        this.onProgressUpdate();
    }
}
