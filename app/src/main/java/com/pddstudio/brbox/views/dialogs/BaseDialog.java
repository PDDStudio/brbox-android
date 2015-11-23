package com.pddstudio.brbox.views.dialogs;

import android.content.Context;
import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brbox.managers.Preferences;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public abstract class BaseDialog {

    private MaterialDialog dialog;
    private MaterialDialog.Builder dialogBuilder;

    @StringRes private int dialogTitle;
    @StringRes private int dialogContent;
    @StringRes private int positiveText;
    @StringRes private int negativeText;
    @StringRes private int neutralText;

    private MaterialDialog.SingleButtonCallback positiveCallback;
    private MaterialDialog.SingleButtonCallback negativeCallback;
    private MaterialDialog.SingleButtonCallback neutralCallback;

    private boolean cancelable;
    private Context context;

    public BaseDialog() {
    }

    public void setDialogTitle(@StringRes int dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public void setDialogContent(@StringRes int dialogContent) {
        this.dialogContent = dialogContent;
    }

    public void setPositiveText(@StringRes int positiveText) {

    }

    public void setPositiveText(@StringRes int positiveText, MaterialDialog.SingleButtonCallback positiveCallback) {

    }

    public void setNegativeText(@StringRes int negativeText) {

    }

    public void setNegativeText(@StringRes int negativeText, MaterialDialog.SingleButtonCallback negativeCallback) {

    }

    public void setNeutralText(@StringRes int neutralText) {

    }

    public void setNeutralText(@StringRes int neutralText, MaterialDialog.SingleButtonCallback neutralCallback) {

    }



}
