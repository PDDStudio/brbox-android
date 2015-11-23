package com.pddstudio.brtalk.objects;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ServerSettings {

    private String serverDomain;
    private int serverPort;
    private String serverName;

    public ServerSettings(String serverDomain, int serverPort) {
        this.serverDomain = serverDomain;
        this.serverPort = serverPort;
    }

    public String getServerDomain() {
        return serverDomain;
    }

    public void setServerDomain(String serverDomain) {
        this.serverDomain = serverDomain;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

}
