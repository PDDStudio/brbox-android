package com.pddstudio.brbox.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pddstudio.brbox.R;
import com.pddstudio.brtalk.objects.SingleContact;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleContactFragment extends Fragment {

    private SingleContact singleContact;

    private View root;
    private RecyclerView recyclerView;

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
        return root;
    }

}
