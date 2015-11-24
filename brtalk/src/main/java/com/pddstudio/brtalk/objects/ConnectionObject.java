package com.pddstudio.brtalk.objects;

import android.support.annotation.NonNull;

import com.pddstudio.brtalk.callbacks.xmpp.XmppLoginCallback;
import com.pddstudio.brtalk.callbacks.xmpp.XmppLogoutCallback;
import com.pddstudio.brtalk.callbacks.xmpp.XmppMessageCallback;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ConnectionObject implements XmppLoginCallback, XmppMessageCallback, XmppLogoutCallback {

    private final ClientSettings clientSettings;
    private final ServerSettings serverSettings;
    private AbstractXMPPConnection xmppConnection;
    private final List<XmppMessageCallback> messageCallbackList = new ArrayList<>();
    private final List<XmppLoginCallback> loginCallbackList = new ArrayList<>();
    private final List<XmppLogoutCallback> logoutCallbackList = new ArrayList<>();

    public ConnectionObject(ServerSettings serverSettings, ClientSettings clientSettings) {
        this.serverSettings = serverSettings;
        this.clientSettings = clientSettings;
    }

    //returns the server settings
    public ServerSettings getServerSettings() {
        return serverSettings;
    }

    //returns the client settings
    public ClientSettings getClientSettings() {
        return clientSettings;
    }

    //the connection object - this is only used by the OpenConnectionTask after a successful login
    public void setXmppConnection(AbstractXMPPConnection xmppConnection) {
        this.xmppConnection = xmppConnection;
    }

    //this function needs to be replaced later - once everything is working TODO
    public AbstractXMPPConnection getXmppConnection() {
        return xmppConnection;
    }

    //method the check whether the connection is open or not
    public boolean isConnected() {
        if(xmppConnection == null) return false;
        return xmppConnection.isConnected();
    }

    //method to disconnect the current connection (if open)
    public void disconnect() {
        if(xmppConnection != null && xmppConnection.isConnected()) xmppConnection.disconnect();
        this.onLogout(!isConnected());
    }

    //for adding multiple XmppMessageCallback interfaces
    public void addMessageCallback(XmppMessageCallback messageCallbacks) {
        messageCallbackList.add(messageCallbacks);
    }

    //for adding multiple XmppLoginCallback interfaces
    public void addLoginCallback(XmppLoginCallback xmppLoginCallback) {
        loginCallbackList.add(xmppLoginCallback);
    }

    //for adding multiple XmppLogoutCallback interfaces
    public void addLogoutCallback(XmppLogoutCallback xmppLogoutCallback) {
        logoutCallbackList.add(xmppLogoutCallback);
    }

    public void sendRequest(@NonNull RequestObject requestObject) {
        if(xmppConnection != null && xmppConnection.isConnected()) {
            this.onPrepareRequest();
            try {
                ChatManager chatManager = ChatManager.getInstanceFor(xmppConnection);
                Chat chat = chatManager.createChat(requestObject.getSingleContact().getConnectionId());
                chat.addMessageListener(chatMessageListener);
                Message message = new Message();
                message.setBody(requestObject.getBoxCommand().getCommandTarget());
                message.setType(Message.Type.chat);
                chat.sendMessage(message);
            } catch (Exception e) {
                RequestFailure requestFailure = new RequestFailure(e.toString(), null, e.getLocalizedMessage());
                e.printStackTrace();
                this.onRequestFailure(requestFailure);
            }
        } else {
            RequestFailure requestFailure = new RequestFailure("XMPPConnectionFailure", "Unable to find connection.", null);
            this.onRequestFailure(requestFailure);
        }
    }

    private final ChatMessageListener chatMessageListener = new ChatMessageListener() {
        @Override
        public void processMessage(Chat chat, Message message) {
            ResponseObject responseObject = new ResponseObject(null, chat, message);
            ConnectionObject.this.onServeResponse(responseObject);
        }
    };

    @Override
    public void onPreparingXmppLogin() {
        for(XmppLoginCallback callback : loginCallbackList) {
            callback.onPreparingXmppLogin();
        }
    }

    @Override
    public void onXmppLoginResult(boolean loginSuccessful) {
        for(XmppLoginCallback callback : loginCallbackList) {
            callback.onXmppLoginResult(loginSuccessful);
        }
    }

    @Override
    public void onPrepareRequest() {
        for(XmppMessageCallback callback : messageCallbackList) {
            callback.onPrepareRequest();
        }
    }

    @Override
    public void onServeResponse(ResponseObject responseObject) {
        for(XmppMessageCallback callback : messageCallbackList) {
            callback.onServeResponse(responseObject);
        }
    }

    @Override
    public void onRequestFailure(RequestFailure requestFailure) {
        for (XmppMessageCallback callback : messageCallbackList) {
            callback.onRequestFailure(requestFailure);
        }
    }

    @Override
    public void onLogout(boolean logoutSuccess) {
        for (XmppLogoutCallback callback : logoutCallbackList) {
            callback.onLogout(logoutSuccess);
        }
    }
}
