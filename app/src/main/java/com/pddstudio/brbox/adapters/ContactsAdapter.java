package com.pddstudio.brbox.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pddstudio.brbox.R;
import com.pddstudio.brtalk.BrTalk;
import com.pddstudio.brtalk.objects.SingleContact;

import java.util.List;

/**
 * This Class was created by Patrick J
 * on 24.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ContactsAdapter extends BaseAdapter {

    private List<SingleContact> contactList;
    private final BrTalk brTalk;

    public ContactsAdapter(BrTalk brTalk) {
        this.brTalk = brTalk;
        this.contactList = brTalk.getContacts();
    }

    public void reloadContacts() {
        this.contactList = brTalk.getContacts();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public SingleContact getItem(int position) {
        if(position >= 0 && position < contactList.size()) {
            return contactList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactItemView contactItemView;
        SingleContact singleContact = contactList.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.contact_item, parent, false);
            contactItemView = new ContactItemView();
            contactItemView.contactName = (TextView) convertView.findViewById(R.id.contact_title);
            contactItemView.contactAddress = (TextView) convertView.findViewById(R.id.contact_address);
            convertView.setTag(contactItemView);
        } else {
            contactItemView = (ContactItemView) convertView.getTag();
        }

        contactItemView.contactName.setText(singleContact.getName());
        contactItemView.contactAddress.setText(singleContact.getConnectionId());
        return convertView;
    }

    private class ContactItemView {
        TextView contactName;
        TextView contactAddress;
    }

}
