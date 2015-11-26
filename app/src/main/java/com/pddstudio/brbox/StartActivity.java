package com.pddstudio.brbox;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.pddstudio.brbox.enums.Page;
import com.pddstudio.brbox.managers.Navigate;
import com.pddstudio.brbox.managers.Preferences;
import com.pddstudio.brbox.views.NavigationDrawer;
import com.pddstudio.brbox.views.dialogs.ExitDialog;

public class StartActivity extends AppCompatActivity {

    //ui components
    private Toolbar toolbar;
    private Drawer drawer;

    //misc props
    private Navigate navigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //navbar color support for Android 5.0 > devices
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.primary_dark, getTheme()));
        }

        //initializing the application's preferences
        Preferences.initialize(this);

        //initializing ui components
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = new NavigationDrawer(this).addToolbar(toolbar).create();

        //initializing the nav handler
        navigate = Navigate.init(this, this, getSupportFragmentManager());

        //check whether the Bundle contains information or not
        if(savedInstanceState == null) {
            navigate.to(Page.HOME);
        } else {
            navigate.toBundleInfo(savedInstanceState);
        }

    }

    public void closeDrawerIfOpen() {
        if(drawer.isDrawerOpen()) drawer.closeDrawer();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            ExitDialog.show(this);
        }
    }

}
