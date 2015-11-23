package com.pddstudio.brtalk.objects;

import android.support.annotation.Nullable;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class BoxCommand {

    //visible name for the command
    private String commandName;

    //command send to the target
    private String commandTarget;

    //description for the command
    private String commandDesc;

    //the group where the command is sort in
    private String commandGroup;

    //represents a collection of information for a command
    public BoxCommand() {}

    public BoxCommand(String commandName, String commandTarget, String commandDesc, @Nullable String commandGrp) {
        this.commandName = commandName;
        this.commandTarget = commandTarget;
        this.commandDesc = commandDesc;
        this.commandGroup = commandGrp;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandTarget() {
        return commandTarget;
    }

    public void setCommandTarget(String commandTarget) {
        this.commandTarget = commandTarget;
    }

    public String getCommandDesc() {
        return commandDesc;
    }

    public void setCommandDesc(String commandDesc) {
        this.commandDesc = commandDesc;
    }
}
