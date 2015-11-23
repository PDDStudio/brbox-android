package com.pddstudio.brbox.enums;

import com.pddstudio.brbox.managers.Navigate;

/**
 * This Class was created by Patrick J
 * on 20.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public enum Page {
    HOME(Navigate.PAGE_HOME, Navigate.PAGE_HOME_TAG),
    ABOUT(Navigate.PAGE_ABOUT, Navigate.PAGE_ABOUT_TAG),
    COMMANDS(Navigate.PAGE_COMMANDS, Navigate.PAGE_COMMANDS_TAG),
    ACCOUNTS(Navigate.PAGE_ACCOUNTS, Navigate.PAGE_ACCOUNTS_TAG);

    private final int fragmentID;
    private final String fragmentTag;

    Page(int fragmentID, String fragmentTag) {
        this.fragmentID = fragmentID;
        this.fragmentTag = fragmentTag;
    }

    public int getFragmentID() {
        return fragmentID;
    }

    public String getFragmentTag() {
        return fragmentTag;
    }

    public static int getFragmentID(Page page) {
        return page.getFragmentID();
    }

    public static String getFragmentTag(Page page) {
        return page.getFragmentTag();
    }

    public static Page getPageForId(int id) {
        switch (id) {
            case Navigate.PAGE_HOME:
                return HOME;
            case Navigate.PAGE_ABOUT:
                return ABOUT;
            case Navigate.PAGE_COMMANDS:
                return COMMANDS;
            case Navigate.PAGE_ACCOUNTS:
                return ACCOUNTS;
        }
        return null;
    }

}
