package com.pddstudio.brbox.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pddstudio.brbox.R;
import com.pddstudio.brbox.managers.Navigate;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommandFragment extends Fragment {

    private static final String FRAGMENT_ID_IDENTIFIER = "FragmentID";
    private static final String FRAGMENT_TAG_IDENTIFIER = "FragmentTAG";

    private int fragmentID;
    private String fragmentTag;

    private View root;
    private ListView commandList;
    private FloatingActionButton fab;

    public CommandFragment() {
        // Required empty public constructor
    }

    public static CommandFragment newInstance(int id, String tag) {
        CommandFragment commandFragment = new CommandFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_ID_IDENTIFIER, id);
        bundle.putString(FRAGMENT_TAG_IDENTIFIER, tag);
        commandFragment.setArguments(bundle);
        return commandFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Check the Bundle
        if(savedInstanceState == null) {
            this.fragmentTag = Navigate.PAGE_HOME_TAG;
            this.fragmentID = Navigate.PAGE_HOME;
        } else {
            this.fragmentTag = savedInstanceState.getString(FRAGMENT_TAG_IDENTIFIER);
            this.fragmentID = savedInstanceState.getInt(FRAGMENT_ID_IDENTIFIER);
        }
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_command, container, false);
        commandList = (ListView) root.findViewById(R.id.command_list);
        fab = (FloatingActionButton) root.findViewById(R.id.add_command_fab);
        //todo add parsing for bundle and loading
        return root;
    }

}
