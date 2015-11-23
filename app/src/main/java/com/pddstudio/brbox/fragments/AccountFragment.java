package com.pddstudio.brbox.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pddstudio.brbox.R;
import com.pddstudio.brbox.managers.Navigate;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private static final String FRAGMENT_ID_IDENTIFIER = "FragmentID";
    private static final String FRAGMENT_TAG_IDENTIFIER = "FragmentTAG";

    private int fragmentID;
    private String fragmentTag;

    private View root;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(int id, String tag) {
        AccountFragment accountFragment = new AccountFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_ID_IDENTIFIER, id);
        bundle.putString(FRAGMENT_TAG_IDENTIFIER, tag);
        accountFragment.setArguments(bundle);
        return accountFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment (and restoring saved information, if any)
        if(savedInstanceState == null) {
            this.fragmentTag = Navigate.PAGE_HOME_TAG;
            this.fragmentID = Navigate.PAGE_HOME;
        } else {
            this.fragmentTag = savedInstanceState.getString(FRAGMENT_TAG_IDENTIFIER);
            this.fragmentID = savedInstanceState.getInt(FRAGMENT_ID_IDENTIFIER);
        }

        root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

}
