package com.pddstudio.brtalk.callbacks.xmpp;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public interface XmppLoginCallback {
    //called before the connection
    void onPreparingXmppLogin();
    //called when tried to connect
    void onXmppLoginResult(boolean loginSuccessful);
}
