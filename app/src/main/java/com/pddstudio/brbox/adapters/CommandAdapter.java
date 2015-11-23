package com.pddstudio.brbox.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pddstudio.brbox.R;
import com.pddstudio.brtalk.objects.BoxCommand;

import java.util.List;

/**
 * This Class was created by Patrick J
 * on 21.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class CommandAdapter extends BaseAdapter {

    private List<BoxCommand> commandList;

    public CommandAdapter(List<BoxCommand> commands) {
        this.commandList = commands;
    }

    @Override
    public int getCount() {
        return commandList.size();
    }

    @Override
    public BoxCommand getItem(int position) {
        if(position >= 0 && position < commandList.size()) {
            return commandList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SingleCommandView singleCommandView;
        BoxCommand boxCommand = commandList.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.box_command_item, parent, false);
            singleCommandView = new SingleCommandView();
            singleCommandView.commandTitle = (TextView) convertView.findViewById(R.id.command_title);
            singleCommandView.commandDescription = (TextView) convertView.findViewById(R.id.command_desc);
            convertView.setTag(singleCommandView);
        } else {
            singleCommandView = (SingleCommandView) convertView.getTag();
        }

        singleCommandView.commandTitle.setText(boxCommand.getCommandName());
        singleCommandView.commandDescription.setText(boxCommand.getCommandDesc());
        return convertView;
    }

    private class SingleCommandView {
        public TextView commandTitle;
        public TextView commandDescription;
        //todo maybe add delete button, too?
    }

}
