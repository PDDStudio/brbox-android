package com.pddstudio.brtalk.objects;

import org.jivesoftware.smack.AbstractXMPPConnection;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ConnectionObject {

    private final ClientSettings clientSettings;
    private final ServerSettings serverSettings;
    private AbstractXMPPConnection xmppConnection;

    public ConnectionObject(ServerSettings serverSettings, ClientSettings clientSettings) {
        this.serverSettings = serverSettings;
        this.clientSettings = clientSettings;
    }

    public ServerSettings getServerSettings() {
        return serverSettings;
    }

    public ClientSettings getClientSettings() {
        return clientSettings;
    }

    public AbstractXMPPConnection getXmppConnection() {
        return xmppConnection;
    }

    public void setXmppConnection(AbstractXMPPConnection xmppConnection) {
        this.xmppConnection = xmppConnection;
    }
}
