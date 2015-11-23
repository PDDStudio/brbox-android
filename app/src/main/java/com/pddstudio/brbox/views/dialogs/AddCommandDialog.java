package com.pddstudio.brbox.views.dialogs;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brbox.R;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public final class AddCommandDialog {

    private static AddCommandDialog addCommandDialog;

    private MaterialDialog dialog;

    private AddCommandDialog() {
        dialog = new MaterialDialog.Builder(DialogUtil.getContext())
                .title("")
                .customView(R.layout.dialog_add_command, true)
                .positiveText("")
                .build();
    }

    public static void show() {
        addCommandDialog = new AddCommandDialog();
        addCommandDialog.dialog.show();
    }

}
