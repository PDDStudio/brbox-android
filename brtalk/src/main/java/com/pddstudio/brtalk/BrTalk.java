package com.pddstudio.brtalk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.pddstudio.brtalk.async.CommandListenerTask;
import com.pddstudio.brtalk.async.OpenConnectionTask;
import com.pddstudio.brtalk.callbacks.SendCommandCallback;
import com.pddstudio.brtalk.callbacks.ServerConnectionCallback;
import com.pddstudio.brtalk.managers.CommandGroupManager;
import com.pddstudio.brtalk.managers.CommandManager;
import com.pddstudio.brtalk.managers.ContactsManager;
import com.pddstudio.brtalk.objects.BoxCommand;
import com.pddstudio.brtalk.objects.ClientSettings;
import com.pddstudio.brtalk.objects.ConnectionObject;
import com.pddstudio.brtalk.objects.ServerSettings;
import com.pddstudio.brtalk.objects.SingleContact;
import com.pddstudio.brtalk.services.ReceiverService;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.packet.Message;

import java.util.List;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class BrTalk {

    //variables received from the ConnectionBuilder
    private final ServerSettings serverSettings;
    private final ClientSettings clientSettings;
    private SendCommandCallback sendCommandCallback;
    private Context appContext;

    //storing the current connection
    private AbstractXMPPConnection xmppConnection;
    //optional identifier for the connection
    private final String connectionId;
    //for managing the contacts
    private ContactsManager contactsManager;
    //for managing the commands
    private final CommandManager commandManager;
    private final CommandGroupManager commandGroupManager;

    private BrTalk(ConnectionBuilder connectionBuilder) {
        this.serverSettings = connectionBuilder.serverSettings;
        this.clientSettings = connectionBuilder.clientSettings;
        this.connectionId = connectionBuilder.connectionId;
        if(connectionBuilder.context != null) this.appContext = connectionBuilder.context;
        if(connectionBuilder.sendCommandCallback != null) this.sendCommandCallback = connectionBuilder.sendCommandCallback;
        this.commandManager = CommandManager.getInstanceFor(this);
        this.commandGroupManager = CommandGroupManager.getInstanceFor(this);
    }

    public void connect(ServerConnectionCallback serverConnectionCallback) {
        new OpenConnectionTask(this, serverSettings, clientSettings, serverConnectionCallback).execute();
    }

    public ConnectionObject getConnectionObject() {
        return new ConnectionObject(serverSettings, clientSettings);
    }

    public boolean hasSendCommandCallback() {
        return this.sendCommandCallback != null;
    }

    public SendCommandCallback getSendCommandCallback() {
        return sendCommandCallback;
    }

    public void setXMPPConnection(AbstractXMPPConnection abstractXMPPConnection) {
        this.xmppConnection = abstractXMPPConnection;
    }

    public AbstractXMPPConnection getXmppConnection() {
        return xmppConnection;
    }

    public Context getAppContext()  {
        return appContext;
    }

    public void sendCommand(SingleContact contact, BoxCommand command) {
        //commandManager.sendCommand(contact, command);
        //new CommandListenerTask(this, contact, command, sendCommandCallback).execute();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void sendMessage() {
        if(xmppConnection != null) {
            ChatManager chatManager = ChatManager.getInstanceFor(xmppConnection);
            ReceiverService receiverService = ReceiverService.register(chatManager);
            contactsManager = ContactsManager.forConnection(xmppConnection);
            Chat chat = chatManager.createChat("dev-pdds-rpi@ubuntu-jabber.de");
            chat.addMessageListener(receiverService);
            try {
                Message message = new Message();
                message.setBody("Hello there! Test message incoming.");
                message.setType(Message.Type.chat);
                message.setSubject("Test-Message!");
                chat.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<SingleContact> getContacts() {
        return contactsManager.getContactList();
    }

    public static class ConnectionBuilder {

        private final ServerSettings serverSettings;
        private final ClientSettings clientSettings;
        private SendCommandCallback sendCommandCallback;
        private String connectionId;
        private Context context;

        public ConnectionBuilder(@NonNull ServerSettings serverSettings, @NonNull ClientSettings clientSettings) {
            this.serverSettings = serverSettings;
            this.clientSettings = clientSettings;
        }

        public ConnectionBuilder withCommandCallback(SendCommandCallback sendCommandCallback) {
            this.sendCommandCallback = sendCommandCallback;
            return this;
        }

        public ConnectionBuilder withConnectionID(String connectionId) {
            this.connectionId = connectionId;
            return this;
        }

        public ConnectionBuilder withContext(Context appContext) {
            this.context = appContext;
            return this;
        }

        public BrTalk build() {
            return new BrTalk(this);
        }

    }



}
