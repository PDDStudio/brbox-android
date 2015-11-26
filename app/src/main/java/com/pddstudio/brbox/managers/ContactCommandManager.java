package com.pddstudio.brbox.managers;

import android.util.Log;

import com.pddstudio.brbox.TestClass;
import com.pddstudio.brbox.objects.CommandHistoryObject;
import com.pddstudio.brtalk.BrTalk;
import com.pddstudio.brtalk.callbacks.xmpp.XmppMessageCallback;
import com.pddstudio.brtalk.objects.BoxCommand;
import com.pddstudio.brtalk.objects.RequestFailure;
import com.pddstudio.brtalk.objects.RequestObject;
import com.pddstudio.brtalk.objects.ResponseObject;
import com.pddstudio.brtalk.objects.SingleContact;

import java.util.List;

/**
 * This Class was created by Patrick J
 * on 25.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class ContactCommandManager {

    public interface NotificationInterface {
        void onItemAdded(CommandHistoryObject commandHistoryObject);
        void onListReplaced();
    }

    private final SingleContact targetContact;
    private final BrTalk brTalk;

    private List<CommandHistoryObject> historyObjectList;

    private NotificationInterface notificationInterface;

    private CommandHistoryObject commandHistoryObject;

    private ContactCommandManager(SingleContact contact) {
        this.targetContact = contact;
        this.historyObjectList = Preferences.getInstance().getSavedCommandHistory(contact);
        this.brTalk = TestClass.getInstance().getBrTalk();
        this.brTalk.getConnectionObject().addMessageCallback(messageCallback);
    }

    public static ContactCommandManager getHistory(SingleContact contact) {
        return new ContactCommandManager(contact);
    }

    //returns the List of saved commands for the given contact
    public List<CommandHistoryObject> getHistoryObjectList() {
        return historyObjectList;
    }

    //adds a new entry to the list and saves it to the device's storage
    public void addHistoryObject(CommandHistoryObject commandHistoryObject) {
        historyObjectList.add(commandHistoryObject);
        Preferences.getInstance().saveCommandHistory(targetContact, historyObjectList);
        if(notificationInterface != null) notificationInterface.onItemAdded(commandHistoryObject);
        //todo: notifyDatasetChagned() call required on adapter to see changes
    }

    //clears the list of history entries for the given contact and deletes the data saved on the storage
    public void clearHistory() {
        historyObjectList.clear();
        Preferences.getInstance().saveCommandHistory(targetContact, historyObjectList);
        if(notificationInterface != null) notificationInterface.onListReplaced();
        //todo: notifyDatasetChanged() call required on adapter to see changes
    }

    //resets the current list and pulls a fresh 'checkout' from the backend
    public void reset() {
        historyObjectList.clear();
        historyObjectList = Preferences.getInstance().getSavedCommandHistory(targetContact);
        if(notificationInterface != null) notificationInterface.onListReplaced();
    }

    //returns whether the contact has a history or not
    public boolean hasHistory() {
        return !historyObjectList.isEmpty();
    }

    //sends a new command
    public void sendCommand(BoxCommand command) {
        commandHistoryObject = new CommandHistoryObject();
        RequestObject requestObject = new RequestObject(targetContact, command);
        brTalk.getConnectionObject().sendRequest(requestObject);
    }

    //register the notification interface
    public void registerNotificationInterface(NotificationInterface notificationInterface) {
        this.notificationInterface = notificationInterface;
    }

    private XmppMessageCallback messageCallback = new XmppMessageCallback() {
        @Override
        public void onPrepareRequest(RequestObject requestObject) {
            if(commandHistoryObject != null) {
                commandHistoryObject.setContactTarget(requestObject.getSingleContact());
                commandHistoryObject.setRequestObject(requestObject);
                Log.d("ContactCMD", "added target and request object to history object!");
            } else {
                Log.d("ContactCMD", "unable to add target and request object to history object!");
            }
        }

        @Override
        public void onServeResponse(ResponseObject responseObject) {
            if(commandHistoryObject != null) {
                commandHistoryObject.setResponseObject(responseObject);
                Log.d("ContactCMD", "added response object to history object!");
                ContactCommandManager.this.addHistoryObject(commandHistoryObject);
                commandHistoryObject = null;
            } else {
                Log.d("ContactCMD", "unable to add response object to history object!");
            }
        }

        @Override
        public void onRequestFailure(RequestFailure requestFailure) {
            if(commandHistoryObject != null) {
                commandHistoryObject.setRequestFailure(requestFailure);
                commandHistoryObject.setRequestFailed(true);
                Log.d("ContactCMD", "added request failure object to history object!");
                ContactCommandManager.this.addHistoryObject(commandHistoryObject);
                commandHistoryObject = null;
            } else {
                Log.d("ContactCMD", "unable to add failure object to history object!");
            }
        }
    };

}
