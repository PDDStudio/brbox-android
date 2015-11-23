package com.pddstudio.brbox.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pddstudio.brbox.R;
import com.pddstudio.brbox.TestClass;
import com.pddstudio.brbox.managers.Navigate;
import com.pddstudio.brbox.views.dialogs.ContactListDialog;
import com.pddstudio.brbox.views.dialogs.NewConnectionDialog;
import com.pddstudio.brtalk.callbacks.ServerConnectionCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String FRAGMENT_ID_IDENTIFIER = "FragmentID";
    private static final String FRAGMENT_TAG_IDENTIFIER = "FragmentTAG";

    private int fragmentID;
    private String fragmentTag;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(int pageId, String pageIdent) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_ID_IDENTIFIER, pageId);
        bundle.putString(FRAGMENT_TAG_IDENTIFIER, pageIdent);
        homeFragment.setArguments(bundle);
        return homeFragment;
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

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final EditText userName = (EditText) root.findViewById(R.id.login_username_field);
        final EditText userPassword = (EditText) root.findViewById(R.id.login_password_field);
        Button loginButton = (Button) root.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LoginButton", "Username: " + userName.getText().toString() + " Password: " + userPassword.getText().toString());
                //Testing the connection via Test-Class
                TestClass.getInstance().openTestConnection(getContext(), serverConnectionCallback);
            }
        });

        Button logoutButton = (Button) root.findViewById(R.id.logout_btn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestClass.getInstance().logout();
            }
        });

        Button contactList = (Button) root.findViewById(R.id.show_contacts_btn);
        contactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListDialog.show();
            }
        });

        return root;
    }

    private ServerConnectionCallback serverConnectionCallback = new ServerConnectionCallback() {
        @Override
        public void onPreparingConnection() {
            NewConnectionDialog.show();
        }

        @Override
        public void onConnectionResultReceived(boolean status) {
            NewConnectionDialog.showResult(status);
        }
    };

}
