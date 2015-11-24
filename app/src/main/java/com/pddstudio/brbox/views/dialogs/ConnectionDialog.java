package com.pddstudio.brbox.views.dialogs;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brbox.R;

/**
 * This Class was created by Patrick J
 * on 24.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public final class ConnectionDialog {

    private MaterialDialog dialog;

    private ConnectionDialog() {
        dialog = DialogUtil.getDefaultBuilderColors()
                .title(R.string.connection_disconnected_title)
                .content(R.string.connection_disconnected_content)
                .positiveText(R.string.ok)
                .build();
    }

    public static void showDisconnected() {
        ConnectionDialog connectionDialog = new ConnectionDialog();
        connectionDialog.dialog.show();
    }


}
