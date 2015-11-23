package com.pddstudio.brtalk.async;

import android.os.AsyncTask;
import android.util.Log;

import com.pddstudio.brtalk.BrTalk;
import com.pddstudio.brtalk.callbacks.ServerConnectionCallback;
import com.pddstudio.brtalk.objects.ClientSettings;
import com.pddstudio.brtalk.objects.ConnectionObject;
import com.pddstudio.brtalk.objects.ServerSettings;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.sasl.provided.SASLDigestMD5Mechanism;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;


/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class OpenConnectionTask extends AsyncTask<Void, Void, Boolean> {

    private final ServerConnectionCallback serverConnectionCallback;
    private final ServerSettings serverSettings;
    private final ClientSettings clientSettings;
    private final BrTalk brTalk;

    private AbstractXMPPConnection xmppConnection;

    public OpenConnectionTask(BrTalk brTalk, ServerSettings serverSettings, ClientSettings clientSettings, ServerConnectionCallback serverConnectionCallback) {
        this.serverConnectionCallback = serverConnectionCallback;
        this.serverSettings = serverSettings;
        this.clientSettings = clientSettings;
        this.brTalk = brTalk;
        //todo replace with ConnectionObject
    }

    @Override
    public void onPreExecute() {
        serverConnectionCallback.onPreparingConnection();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        XMPPTCPConnectionConfiguration configuration = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword(clientSettings.getUserName(), clientSettings.getUserPassword())
                .setHost(serverSettings.getServerDomain())
                .setServiceName(serverSettings.getServerDomain())
                .setPort(serverSettings.getServerPort())
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setDebuggerEnabled(true)
                .build();

        xmppConnection = new XMPPTCPConnection(configuration);
        SASLMechanism saslMechanism = new SASLDigestMD5Mechanism();
        SASLAuthentication.registerSASLMechanism(saslMechanism);
        SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
        SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");


        try {
            xmppConnection.connect();
            xmppConnection.login();
            Log.d("BrTalk", "Connection successful!");
            return true;
        } catch (Exception e) {
            Log.d("BrTalk", "Connection failed!");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onPostExecute(Boolean result) {
        if(result) brTalk.setXMPPConnection(xmppConnection);
        serverConnectionCallback.onConnectionResultReceived(result);
    }

}
