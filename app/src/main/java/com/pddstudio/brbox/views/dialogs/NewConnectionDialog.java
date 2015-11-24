package com.pddstudio.brbox.views.dialogs;

import android.util.Log;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brbox.R;
import com.pddstudio.brbox.TestClass;

import org.jivesoftware.smack.AbstractXMPPConnection;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public final class NewConnectionDialog {

    private static NewConnectionDialog newConnectionDialog;

    private MaterialDialog dialog;

    private NewConnectionDialog() {
        dialog = DialogUtil.getDefaultBuilderColors()
                .title(R.string.new_connection_title)
                .content(R.string.new_connection_content)
                .progress(true, 0)
                .cancelable(false)
                .build();
    }

    public static void show() {
        newConnectionDialog = new NewConnectionDialog();
        newConnectionDialog.dialog.show();
    }

    public static void showResult(boolean result) {
        if(newConnectionDialog != null && newConnectionDialog.dialog.isShowing()) newConnectionDialog.dialog.hide();
        MaterialDialog.Builder dialogBuilder = DialogUtil.getDefaultBuilderColors();
        if(result) {
            dialogBuilder.title(R.string.new_connection_result_positive_title);
            dialogBuilder.content(R.string.new_connection_result_positive_content);
            dialogBuilder.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                    AbstractXMPPConnection xmppConnection = TestClass.getInstance().currentConnection();
                    Log.d("ConnectionInfo", "Hostname: " + xmppConnection.getHost() + " User: " + xmppConnection.getUser());
                    Log.d("ConnectionInfo", "Connected: " + xmppConnection.isConnected() + " Authenticated: " + xmppConnection.isAuthenticated());
                    Log.d("ConnectionInfo", "Anonymous: " + xmppConnection.isAnonymous() + " Secured Connection: " + xmppConnection.isSecureConnection());
                }
            });
        } else {
            dialogBuilder.title(R.string.new_connection_result_negative_title);
            dialogBuilder.content(R.string.new_connection_result_negative_content);
        }
        dialogBuilder.positiveText(R.string.ok);
        dialogBuilder.show();
    }
}
