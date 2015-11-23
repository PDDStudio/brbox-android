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
public class AboutFragment extends Fragment {

    private static final String FRAGMENT_ID_IDENTIFIER = "FragmentID";
    private static final String FRAGMENT_TAG_IDENTIFIER = "FragmentTAG";

    private int fragmentID;
    private String fragmentTag;

    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(int pageId, String pageTag) {
        AboutFragment aboutFragment = new AboutFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_ID_IDENTIFIER, pageId);
        bundle.putString(FRAGMENT_TAG_IDENTIFIER, pageTag);
        aboutFragment.setArguments(bundle);
        return aboutFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Check whether the bundle contains any states to be restored
        if(savedInstanceState == null) {
            this.fragmentTag = Navigate.PAGE_HOME_TAG;
            this.fragmentID = Navigate.PAGE_HOME;
        } else {
            this.fragmentTag = savedInstanceState.getString(FRAGMENT_TAG_IDENTIFIER);
            this.fragmentID = savedInstanceState.getInt(FRAGMENT_ID_IDENTIFIER);
        }
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        return root;
    }

}
