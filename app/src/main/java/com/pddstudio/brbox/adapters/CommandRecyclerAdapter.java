package com.pddstudio.brbox.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pddstudio.brbox.R;
import com.pddstudio.brbox.managers.ContactCommandManager;
import com.pddstudio.brbox.objects.CommandHistoryObject;

import java.util.List;

/**
 * This Class was created by Patrick J
 * on 24.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class CommandRecyclerAdapter extends RecyclerView.Adapter<CommandRecyclerAdapter.ViewHolder> implements ContactCommandManager.NotificationInterface {

    private List<CommandHistoryObject> itemList;
    private ContactCommandManager contactCommandManager;

    public CommandRecyclerAdapter(ContactCommandManager commandHistoryObjects) {
        this.contactCommandManager = commandHistoryObjects;
        this.itemList = commandHistoryObjects.getHistoryObjectList();
        contactCommandManager.registerNotificationInterface(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact_cmd_item, parent, false);
        //todo: align items layout and paddings
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //todo: set the data for the viewholder items
        //getting the history object
        CommandHistoryObject historyObject = itemList.get(position);
        //request info
        holder.sendText.setText("Send Text: " + historyObject.getRequestObject().getBoxCommand().getCommandDesc());
        holder.sendDate.setText("Date: " + historyObject.getRequestObject().getRequestDate());
        //response info
        if(!historyObject.isRequestFailed()) {
            //on success
            holder.responseText.setText("ResponseText: " + historyObject.getResponseObject().getMessage().getBody());
            holder.responseDate.setText("Date: " + historyObject.getResponseObject().getResponseDate());
        } else {
            //on failed
            holder.responseText.setText("Request failed! " + historyObject.getRequestFailure().getFailDescriptionContentShort());
            holder.responseDate.setText("Date: " + historyObject.getRequestFailure().getFailDate());
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onItemAdded(CommandHistoryObject commandHistoryObject) {
        if(itemList != null) itemList.add(commandHistoryObject);
        this.notifyDataSetChanged();
    }

    @Override
    public void onListReplaced() {
        if(itemList != null) itemList = contactCommandManager.getHistoryObjectList();
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sendText;
        public TextView sendDate;
        public TextView responseText;
        public TextView responseDate;

        public ViewHolder(View itemView) {
            super(itemView);
            sendDate = (TextView) itemView.findViewById(R.id.request_date);
            sendText = (TextView) itemView.findViewById(R.id.request_text);
            responseDate = (TextView) itemView.findViewById(R.id.response_date);
            responseText = (TextView) itemView.findViewById(R.id.response_text);
        }
    }

}
