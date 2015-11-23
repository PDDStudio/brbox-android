package com.pddstudio.brtalk.managers;

import android.support.annotation.Nullable;

import com.pddstudio.brtalk.BrTalk;
import com.pddstudio.brtalk.callbacks.BrRequestInterface;
import com.pddstudio.brtalk.callbacks.ConnectionInterface;
import com.pddstudio.brtalk.objects.BoxCommand;
import com.pddstudio.brtalk.objects.ConnectionObject;
import com.pddstudio.brtalk.objects.RequestObject;
import com.pddstudio.brtalk.objects.ResponseObject;
import com.pddstudio.brtalk.objects.SingleContact;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public abstract class RequestManager implements ConnectionInterface, BrRequestInterface {

    private final BrTalk brTalk;

    private SingleContact targetContact;
    private BoxCommand targetCommand;

    public RequestManager(BrTalk brTalk) {
        this.brTalk = brTalk;
    }

    public abstract RequestObject onRequestInit();
    public abstract void onRequestResponseReceived(ResponseObject responseObject);
    public abstract void onRequestFailed(@Nullable Exception exception);
    public abstract ConnectionObject getConnection();

    public void executeRequest() {
        RequestObject requestObject = this.onRequestInit();
        this.targetContact = requestObject.getSingleContact();
        this.targetCommand = requestObject.getBoxCommand();
    }

    private void connectionSetup() {

    }

}
