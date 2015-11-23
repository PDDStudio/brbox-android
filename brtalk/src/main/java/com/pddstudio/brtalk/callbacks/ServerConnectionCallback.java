package com.pddstudio.brtalk.callbacks;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public interface ServerConnectionCallback {
    void onPreparingConnection();
    void onConnectionResultReceived(boolean status);
}
