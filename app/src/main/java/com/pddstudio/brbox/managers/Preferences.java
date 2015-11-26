package com.pddstudio.brbox.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.pddstudio.brbox.objects.CommandHistoryObject;
import com.pddstudio.brbox.views.dialogs.DialogUtil;
import com.pddstudio.brtalk.objects.SingleContact;

import java.util.LinkedList;
import java.util.List;

import io.paperdb.Paper;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class Preferences {

    private static final String PREFERENCES_NAME = "brbox.preferences";
    private static Preferences preferences;

    private static final String CONTACT_COMMAND_HISTORY = "contact_cmd_history";

    private final SharedPreferences sharedPreferences;

    private Preferences(Activity activity) {
        this.sharedPreferences = activity.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        //initializing paper for pojo de/serialization
        Paper.init(activity);

        DialogUtil.setAppContext(activity);
    }

    public static Preferences initialize(Activity activity) {
        preferences = new Preferences(activity);
        return preferences;
    }

    public static Preferences getInstance() {
        return preferences;
    }

    //save the given command history for the given contact
    public void saveCommandHistory(SingleContact contact, List<CommandHistoryObject> commandHistoryObjectList) {
        Paper.book(contact.getConnectionId()).write(CONTACT_COMMAND_HISTORY, commandHistoryObjectList);
    }

    //load the command history for the given contact
    public List<CommandHistoryObject> getSavedCommandHistory(SingleContact contact) {
        return Paper.book(contact.getConnectionId()).read(CONTACT_COMMAND_HISTORY, new LinkedList<CommandHistoryObject>());
    }

}
