package com.pddstudio.brtalk.objects;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class RequestObject {

    private final SingleContact singleContact;
    private final BoxCommand boxCommand;
    private final Date requestDate;

    public RequestObject(SingleContact contact, BoxCommand boxCommand) {
        this.singleContact = contact;
        this.boxCommand = boxCommand;
        this.requestDate = GregorianCalendar.getInstance(Locale.getDefault()).getTime();
    }

    public SingleContact getSingleContact() {
        return singleContact;
    }

    public BoxCommand getBoxCommand() {
        return boxCommand;
    }

    public Date getRequestDate() {
        return requestDate;
    }

}
