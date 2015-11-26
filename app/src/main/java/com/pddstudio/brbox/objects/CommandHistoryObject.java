package com.pddstudio.brbox.objects;

import com.pddstudio.brtalk.objects.RequestFailure;
import com.pddstudio.brtalk.objects.RequestObject;
import com.pddstudio.brtalk.objects.ResponseObject;
import com.pddstudio.brtalk.objects.SingleContact;

/**
 * This Class was created by Patrick J
 * on 25.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class CommandHistoryObject {

    private SingleContact contactTarget;
    private RequestObject requestObject;
    private ResponseObject responseObject;
    private RequestFailure requestFailure;
    private boolean requestFailed = false;

    public CommandHistoryObject() {

    }

    public SingleContact getContactTarget() {
        return contactTarget;
    }

    public void setContactTarget(SingleContact contactTarget) {
        this.contactTarget = contactTarget;
    }

    public RequestObject getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(RequestObject requestObject) {
        this.requestObject = requestObject;
    }

    public ResponseObject getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(ResponseObject responseObject) {
        this.responseObject = responseObject;
    }

    public boolean isRequestFailed() {
        return requestFailed;
    }

    public void setRequestFailed(boolean requestFailed) {
        this.requestFailed = requestFailed;
    }

    public RequestFailure getRequestFailure() {
        return requestFailure;
    }

    public void setRequestFailure(RequestFailure requestFailure) {
        this.requestFailure = requestFailure;
    }
}
