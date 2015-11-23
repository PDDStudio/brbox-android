package com.pddstudio.brtalk.callbacks;

import com.pddstudio.brtalk.objects.RequestObject;
import com.pddstudio.brtalk.objects.ResponseObject;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public interface BrRequestInterface {
    RequestObject onRequestInit();
    void onRequestResponseReceived(ResponseObject responseObject);
}
