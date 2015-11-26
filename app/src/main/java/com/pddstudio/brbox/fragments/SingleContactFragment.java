package com.pddstudio.brbox.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pddstudio.brbox.R;
import com.pddstudio.brbox.TestClass;
import com.pddstudio.brbox.adapters.CommandRecyclerAdapter;
import com.pddstudio.brbox.managers.ContactCommandManager;
import com.pddstudio.brtalk.objects.SingleContact;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleContactFragment extends Fragment {

    private static final String FRAGMENT_ID_IDENTIFIER = "FragmentID";
    private static final String FRAGMENT_TAG_IDENTIFIER = "FragmentTAG";

    private int fragmentID;
    private String fragmentTag;

    private SingleContact singleContact;
    private ContactCommandManager contactCommandManager;

    private View root;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private CommandRecyclerAdapter commandRecyclerAdapter;

    public SingleContactFragment() {
        // Required empty public constructor
    }

    public SingleContactFragment withContact(SingleContact singleContact) {
        this.singleContact = singleContact;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_single_contact, container, false);
        //todo: inflate layout (parse history and add actions)
        recyclerView = (RecyclerView) root.findViewById(R.id.contact_command_recycler_view);

        fab = (FloatingActionButton) root.findViewById(R.id.contact_command_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestClass.getInstance().sendHelpCommand(contactCommandManager);
            }
        });

        if(singleContact != null) {
            contactCommandManager = ContactCommandManager.getHistory(singleContact);
            if(!contactCommandManager.hasHistory()) Log.d("SCFragment", "no history found int contactCommandManager!");
            commandRecyclerAdapter = new CommandRecyclerAdapter(contactCommandManager);
            recyclerView.setAdapter(commandRecyclerAdapter);
        }

        return root;
    }

}
