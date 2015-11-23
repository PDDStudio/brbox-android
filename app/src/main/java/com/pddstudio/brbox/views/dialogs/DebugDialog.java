package com.pddstudio.brbox.views.dialogs;

import android.support.annotation.Nullable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brbox.R;

/**
 * This Class was created by Patrick J
 * on 23.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class DebugDialog {

    private static MaterialDialog.Builder builder = DialogUtil.getDefaultBuilderColors();

    public static MaterialDialog getLoadingDialog(@Nullable String title, @Nullable String content) {
        MaterialDialog dialog;
        builder.progress(true, 0);
        if(title !=  null) builder.title(title);
        if(content != null) builder.content(content);
        dialog = builder.build();
        return dialog;
    }

    public static MaterialDialog getInfoDialog(String title, String content) {
        MaterialDialog dialog;
        builder.title(title).content(content).positiveText(R.string.ok);
        dialog = builder.build();
        return dialog;
    }
}
