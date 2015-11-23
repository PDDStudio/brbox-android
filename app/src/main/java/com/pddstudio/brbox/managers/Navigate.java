package com.pddstudio.brbox.managers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pddstudio.brbox.R;
import com.pddstudio.brbox.StartActivity;
import com.pddstudio.brbox.enums.Page;
import com.pddstudio.brbox.fragments.AboutFragment;
import com.pddstudio.brbox.fragments.AccountFragment;
import com.pddstudio.brbox.fragments.CommandFragment;
import com.pddstudio.brbox.fragments.HomeFragment;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class Navigate {

    //public identifiers
    public static final int PAGE_HOME = 0;
    public static final String PAGE_HOME_TAG = "FRAGMENT_HOME";
    public static final int PAGE_ABOUT = 1;
    public static final String PAGE_ABOUT_TAG = "FRAGMENT_ABOUT";
    public static final int PAGE_COMMANDS = 2;
    public static final String PAGE_COMMANDS_TAG = "FRAGMENT_CMD";
    public static final int PAGE_ACCOUNTS = 3;
    public static final String PAGE_ACCOUNTS_TAG = "FRAGMENT_ACC";

    //bundle identifier
    private static final String LAST_PAGE_ID = "FRAGMENT_ID";
    private static final String LAST_PAGE_TAG = "FRAGMENT_TAG";
    
    //singleton instance
    private static Navigate navigateInstance;

    private final StartActivity activity;
    private final Context context;
    private final FragmentManager fragmentManager;

    private Navigate(StartActivity appCompatActivity, Context context, FragmentManager fragmentManager) {
        this.activity = appCompatActivity;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public static Navigate init(StartActivity startActivity, Context context, FragmentManager fragmentManager) {
        navigateInstance = new Navigate(startActivity, context, fragmentManager);
        return navigateInstance;
    }

    public static Navigate getInstance() {
        return navigateInstance;
    }

    private String getFragmentTagForID(int fragmentID) {
        switch (fragmentID) {
            default:
            case PAGE_HOME:
                return PAGE_HOME_TAG;
            case PAGE_ABOUT:
                return PAGE_ABOUT_TAG;
            case PAGE_COMMANDS:
                return PAGE_COMMANDS_TAG;
            case PAGE_ACCOUNTS:
                return PAGE_ACCOUNTS_TAG;
        }
    }

    private void setFragment(int id, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case PAGE_HOME:
                HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(PAGE_HOME_TAG);
                if(homeFragment == null) {
                    Log.d("setFragment()", "HomeFragment was null. Creating new instance!");
                    homeFragment = HomeFragment.newInstance(id, tag);
                }
                fragmentTransaction.replace(R.id.fragment_placeholder, homeFragment);
                break;
            case PAGE_ABOUT:
                AboutFragment aboutFragment = (AboutFragment) fragmentManager.findFragmentByTag(PAGE_ABOUT_TAG);
                if(aboutFragment == null) {
                    Log.d("setFragment()", "AboutFragment was null. Creating new instance!");
                    aboutFragment = AboutFragment.newInstance(id, tag);
                }
                fragmentTransaction.replace(R.id.fragment_placeholder, aboutFragment);
                break;
            case PAGE_COMMANDS:
                CommandFragment commandFragment = (CommandFragment) fragmentManager.findFragmentByTag(PAGE_COMMANDS_TAG);
                if(commandFragment == null) {
                    Log.d("setFragment()", "CommandFragment was null. Creating new instance!");
                    commandFragment = CommandFragment.newInstance(id, tag);
                }
                fragmentTransaction.replace(R.id.fragment_placeholder, commandFragment);
                break;
            case PAGE_ACCOUNTS:
                AccountFragment accountFragment = (AccountFragment) fragmentManager.findFragmentByTag(PAGE_ACCOUNTS_TAG);
                if(accountFragment == null) {
                    Log.d("setFragment()", "AccountFragment was null. Creating new instance");
                    accountFragment = AccountFragment.newInstance(id, tag);
                }
                fragmentTransaction.replace(R.id.fragment_placeholder, accountFragment);
                break;
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        Log.d("Navigate", "Commited FragmentTransaction! Target TAG: " + tag);
        if(activity != null) activity.closeDrawerIfOpen();
    }

    public void to(final int pageIdentifier) {

    }

    public void to(final String pageIdentifier) {

    }

    public void to(final Page page) {
        setFragment(page.getFragmentID(), page.getFragmentTag());
    }

    public void toBundleInfo(Bundle bundle) {
        //no default value required, as because getInt() will return 0 if the value wasn't found
        int pageID = bundle.getInt(LAST_PAGE_ID);
        String pageTag = bundle.getString(LAST_PAGE_TAG, getFragmentTagForID(pageID));
        setFragment(pageID, pageTag);
    }

}
