package com.pddstudio.brtalk;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pddstudio.brtalk.async.OpenConnectionTask;
import com.pddstudio.brtalk.callbacks.ServerConnectionCallback;
import com.pddstudio.brtalk.callbacks.xmpp.XmppLoginCallback;
import com.pddstudio.brtalk.managers.CommandGroupManager;
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
    private final ConnectionObject connectionObject;
    private Context appContext;

    //optional identifier for the connection
    private final String connectionId;
    //for managing the contacts
    private ContactsManager contactsManager;

    private BrTalk(ConnectionBuilder connectionBuilder) {
        if(connectionBuilder.connectionId != null) this.connectionId = connectionBuilder.connectionId;
        else this.connectionId = "NONE";
        if(connectionBuilder.context != null) this.appContext = connectionBuilder.context;
        this.connectionObject = new ConnectionObject(connectionBuilder.serverSettings, connectionBuilder.clientSettings);
    }

    public void connect() {
        new OpenConnectionTask(connectionObject).execute();
    }

    public ConnectionObject getConnectionObject() {
        return connectionObject;
    }

    public Context getAppContext()  {
        return appContext;
    }

    public List<SingleContact> getContacts() {
        return contactsManager.getContactList();
    }

    public List<BoxCommand> getCommands() {
        return null;
        //TODO: add command list
    }

    public static class ConnectionBuilder {

        private final ServerSettings serverSettings;
        private final ClientSettings clientSettings;
        private String connectionId;
        private Context context;

        public ConnectionBuilder(@NonNull ServerSettings serverSettings, @NonNull ClientSettings clientSettings) {
            this.serverSettings = serverSettings;
            this.clientSettings = clientSettings;
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
