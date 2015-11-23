package com.pddstudio.brbox.views.dialogs;

import android.content.Context;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brbox.R;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public final class ExitDialog {

    private static ExitDialog exitDialog;

    private MaterialDialog dialog;

    private ExitDialog(Context context) {
        dialog = new MaterialDialog.Builder(context)
                .title(R.string.exit_dialog_title)
                .content(R.string.exit_dialog_content)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        System.exit(0);
                    }
                })
                .build();
    }

    public static void show(Context context) {
        exitDialog = new ExitDialog(context);
        exitDialog.dialog.show();
    }

}
