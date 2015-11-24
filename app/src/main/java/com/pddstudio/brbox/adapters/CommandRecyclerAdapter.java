package com.pddstudio.brbox.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pddstudio.brbox.R;

import java.util.List;

/**
 * This Class was created by Patrick J
 * on 24.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class CommandRecyclerAdapter extends RecyclerView.Adapter<CommandRecyclerAdapter.ViewHolder> {

    public CommandRecyclerAdapter() {}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact_cmd_item, parent, false);
        //todo: align items layout and paddings
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //todo: set the data for the viewholder items
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView responseText;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
