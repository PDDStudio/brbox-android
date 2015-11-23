package com.pddstudio.brtalk.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.pddstudio.brtalk.BrTalk;

import java.util.HashSet;
import java.util.Set;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class CommandGroupManager {

    private static final String BRTALK_MNGR = "brtalk.commandgroup.manager";
    private static CommandGroupManager commandGroupManager;

    private SharedPreferences sharedPreferences;
    private Set<String> commandCategories;

    private CommandGroupManager(BrTalk brTalk) {
        this.commandCategories = new HashSet<>();
        if(brTalk.getAppContext() == null) {
            //todo handle action when no context is given
        } else {
            this.sharedPreferences = brTalk.getAppContext().getSharedPreferences(BRTALK_MNGR, Context.MODE_PRIVATE);
        }
    }

    public static CommandGroupManager getInstanceFor(BrTalk brTalk) {
        if(commandGroupManager == null) commandGroupManager = new CommandGroupManager(brTalk);
        return commandGroupManager;
    }



}
