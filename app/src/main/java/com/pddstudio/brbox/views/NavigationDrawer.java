package com.pddstudio.brbox.views;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.pddstudio.brbox.R;
import com.pddstudio.brbox.StartActivity;
import com.pddstudio.brbox.enums.Page;
import com.pddstudio.brbox.managers.Navigate;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class NavigationDrawer implements Drawer.OnDrawerItemClickListener {

    private DrawerBuilder drawerBuilder;
    private final Activity activity;

    public NavigationDrawer(Activity activity) {
        this.activity = activity;
        drawerBuilder = new DrawerBuilder(activity);
    }

    public NavigationDrawer addToolbar(Toolbar toolbar) {
        drawerBuilder.withToolbar(toolbar);
        return this;
    }

    public Drawer create() {
        drawerBuilder
                .withDrawerItems(getDrawerItems())
                .withOnDrawerItemClickListener(this)
                .withHeader(R.layout.drawer_header)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true);
        return drawerBuilder.build();
    }

    private ArrayList<IDrawerItem> getDrawerItems() {
        ArrayList<IDrawerItem> drawerItems = new ArrayList<>();

        //TODO: Add icons to the items

        //Home-Item
        PrimaryDrawerItem homeItem = new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIdentifier(Navigate.PAGE_HOME);
        drawerItems.add(homeItem);

        //Accounts-Item
        PrimaryDrawerItem accountsItem = new PrimaryDrawerItem().withName(R.string.drawer_item_accounts).withIdentifier(Navigate.PAGE_ACCOUNTS);
        drawerItems.add(accountsItem);

        //Commands-Item
        PrimaryDrawerItem commandsItem = new PrimaryDrawerItem().withName(R.string.drawer_item_commands).withIdentifier(Navigate.PAGE_COMMANDS);
        drawerItems.add(commandsItem);

        //About-Item
        PrimaryDrawerItem aboutItem = new PrimaryDrawerItem().withName(R.string.drawer_item_about).withIdentifier(Navigate.PAGE_ABOUT);
        drawerItems.add(aboutItem);

        return drawerItems;
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        if(drawerItem != null) {
            Page p = Page.getPageForId(drawerItem.getIdentifier());
            if(p != null) Navigate.getInstance().to(p);
            else Log.e("NavigationDrawer", "Unable to change Page! ID not found...");
        }
        return true;
    }
}
