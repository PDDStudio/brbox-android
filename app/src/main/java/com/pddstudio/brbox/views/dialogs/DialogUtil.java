package com.pddstudio.brbox.views.dialogs;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brbox.R;
import com.pddstudio.brbox.managers.Preferences;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class DialogUtil {

    private static Context appContext;

    public static void setAppContext(Context context) {
        appContext = context;
    }

    protected static Context getContext() {
        return appContext;
    }

    public static MaterialDialog.Builder getDefaultBuilderColors() {
        MaterialDialog.Builder baseBuilder = new MaterialDialog.Builder(appContext);
        baseBuilder.backgroundColorRes(R.color.dialogBackgroundColor)
                .titleColorRes(R.color.dialogTitleColor)
                .contentColorRes(R.color.dialogContentColor)
                .positiveColorRes(R.color.dialogPositiveTextColor)
                .neutralColorRes(R.color.dialogNeutralTextColor)
                .negativeColorRes(R.color.dialogNegativeTextColor);
        return baseBuilder;
    }
}
