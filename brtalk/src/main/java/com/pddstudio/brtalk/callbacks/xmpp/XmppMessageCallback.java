package com.pddstudio.brtalk.callbacks.xmpp;

import com.pddstudio.brtalk.objects.RequestFailure;
import com.pddstudio.brtalk.objects.RequestObject;
import com.pddstudio.brtalk.objects.ResponseObject;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public interface XmppMessageCallback {
    void onPrepareRequest();
    void onServeResponse(ResponseObject responseObject);
    void onRequestFailure(RequestFailure requestFailure);
}
