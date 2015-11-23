package com.pddstudio.brbox.managers;

import com.pddstudio.brtalk.BrTalk;

import java.util.HashMap;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ConnectionManager {

    private static ConnectionManager connectionManager;

    private HashMap<String, BrTalk> connections;


    private ConnectionManager() {
        this.connections = new HashMap<>();
    }

    public static ConnectionManager getInstance() {
        if(connectionManager == null) connectionManager = new ConnectionManager();
        return connectionManager;
    }

    private void loadSavedConnections() {

    }

    private void saveConnections() {

    }

    public void addConnection(String identifier, BrTalk brTalk) {
        connections.put(identifier, brTalk);
    }

    public BrTalk getConnection(String identifier) {
        return connections.get(identifier);
    }

}
