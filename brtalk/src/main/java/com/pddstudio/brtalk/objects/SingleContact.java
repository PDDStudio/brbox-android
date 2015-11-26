package com.pddstudio.brtalk.objects;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class SingleContact implements Serializable {

    //the 'real' name
    private String conName;
    //the jabber id
    private String conId;
    //the status, if set
    private String conStat;
    //internal id to handle requests
    private int uid;

    public SingleContact() {}

    public String getName() {
        return conName;
    }

    public void setName(String name) {
        this.conName = name;
    }

    public String getConnectionId() {
        return conId;
    }

    public void setConnectionId(String connectionId) {
        this.conId = connectionId;
    }

    public String getStatus() {
        return conStat;
    }

    public void setStatus(String status) {
        this.conStat = status;
    }

    public int getUniqueId() {
        return uid;
    }

    public void setUID(int uid) {
        this.uid = uid;
    }

}
