package com.pddstudio.brbox.views.dialogs;

import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pddstudio.brbox.R;
import com.pddstudio.brbox.TestClass;
import com.pddstudio.brtalk.objects.SingleContact;

import java.util.List;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public final class ContactListDialog {

    private static ContactListDialog contactListDialog;

    private MaterialDialog dialog;
    private CharSequence[] contactNames;

    private ContactListDialog() {
        this.load();
        dialog = DialogUtil.getDefaultBuilderColors()
                .title("Your Contacts:")
                .items(contactNames)
                .positiveText(R.string.ok)
                .build();
    }

    private void load() {
        contactNames = new CharSequence[TestClass.getInstance().getContactsArray().length];
        for(int i = 0; i < TestClass.getInstance().getContactsArray().length; i++) {
            contactNames[i] = TestClass.getInstance().getContactsArray()[i].getName();
        }
    }

    public static void show() {
        contactListDialog = new ContactListDialog();
        contactListDialog.dialog.show();
    }
}
