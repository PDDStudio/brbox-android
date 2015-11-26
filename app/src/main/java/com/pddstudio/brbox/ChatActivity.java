package com.pddstudio.brbox;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.pddstudio.brbox.fragments.SingleContactFragment;
import com.pddstudio.brtalk.objects.SingleContact;

public class ChatActivity extends AppCompatActivity {

    public static final String CONTACT = "com.pddstudio.brbox.ChatActivity.CONTACT";

    //non ui components
    private SingleContact contact;
    private FragmentManager fragmentManager;

    //ui stuff
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent data = getIntent();
        this.contact = (SingleContact) data.getExtras().getSerializable(CONTACT);
        setContentView(R.layout.activity_chat);

        //navbar color for +5.0 devices
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.primary_dark, getTheme()));
        }

        //leaving the activity if the contact wasn't found
        if(contact == null) {
            Log.e("ChatActivity", "Contact is null. Leaving activity!");
            this.finish();
        } else {
            Log.i("ChatActivity", "Contact successfully re-serialized.");
        }

        //setting the toolbar
        toolbar = (Toolbar) findViewById(R.id.chat_toolbar);
        toolbar.setTitle(contact.getName());
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        //setting the fragment
        SingleContactFragment fragment = new SingleContactFragment().withContact(contact);
        fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.chat_fragment_placeholder, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

    }
}
