package com.pddstudio.brtalk.objects;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class RequestObject {

    private final SingleContact singleContact;
    private final BoxCommand boxCommand;

    public RequestObject(SingleContact contact, BoxCommand boxCommand) {
        this.singleContact = contact;
        this.boxCommand = boxCommand;
    }

    public SingleContact getSingleContact() {
        return singleContact;
    }

    public BoxCommand getBoxCommand() {
        return boxCommand;
    }

}
