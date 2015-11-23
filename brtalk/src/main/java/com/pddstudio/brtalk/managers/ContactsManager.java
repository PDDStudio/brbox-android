package com.pddstudio.brtalk.managers;

import android.util.Log;

import com.pddstudio.brtalk.objects.SingleContact;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ContactsManager {

    private final AbstractXMPPConnection abstractXMPPConnection;
    private List<SingleContact> contactList;

    private ContactsManager(AbstractXMPPConnection abstractXMPPConnection) {
        this.abstractXMPPConnection = abstractXMPPConnection;
        //this.loadRoster();
        this.loadContacts();
    }

    public static ContactsManager forConnection(AbstractXMPPConnection abstractXMPPConnection) {
        return new ContactsManager(abstractXMPPConnection);
    }

    public List<SingleContact> getContactList() {
        return contactList;
    }

    private void loadRoster() {
        Roster roster = Roster.getInstanceFor(abstractXMPPConnection);
        Collection<RosterEntry> entries = roster.getEntries();
        for(RosterEntry entry : entries) {
            Log.d("Roster", "User: " + entry.getUser() + " Name: " + entry.getName() + " Status: " + entry.getStatus());
        }
        Collection<RosterGroup> groups = roster.getGroups();
        for(RosterGroup group : groups) {
            Log.d("RosterGroup", "Name: " + group.getName() + " Entries: " + group.getEntryCount());
        }
    }

    private void loadContacts() {
        contactList = new ArrayList<>();
        Roster roster = Roster.getInstanceFor(abstractXMPPConnection);
        Collection<RosterEntry> rosterEntries = roster.getEntries();
        int id = 0;
        for(RosterEntry entry : rosterEntries) {
            SingleContact singleContact = new SingleContact();
            singleContact.setUID(id);
            singleContact.setName(entry.getName());
            singleContact.setConnectionId(entry.getUser());
            if(entry.getStatus() != null) singleContact.setStatus(entry.getStatus().name());
            contactList.add(singleContact);
            id++;
        }
    }

    public SingleContact getContact(int UID) {
        return contactList.get(UID);
    }

    public SingleContact getContact(String contactID) {
        for(SingleContact contact : contactList) {
            if(contact.getConnectionId().toLowerCase().equals(contactID.toLowerCase())) return contact;
        }
        return null;
    }

}
