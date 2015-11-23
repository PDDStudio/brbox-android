package com.pddstudio.brbox.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.pddstudio.brbox.views.dialogs.DialogUtil;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class Preferences {

    private static final String PREFERENCES_NAME = "brbox.preferences";
    private static Preferences preferences;

    private final SharedPreferences sharedPreferences;

    private Preferences(Activity activity) {
        this.sharedPreferences = activity.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        DialogUtil.setAppContext(activity);
    }

    public static Preferences initialize(Activity activity) {
        preferences = new Preferences(activity);
        return preferences;
    }

    public static Preferences getInstance() {
        return preferences;
    }

}
