package com.pddstudio.brtalk.objects;

import android.support.annotation.Nullable;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * This Class was created by Patrick J
 * on 24.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class RequestFailure {

    private final String failDescriptionTitle;
    private final String failDescriptionContentShort;
    private final String failDescriptionContentLong;
    private final Date failDate;

    public RequestFailure(@Nullable String title, @Nullable String contentShort, @Nullable String contentLong) {
        if(title == null) this.failDescriptionTitle = "";
        else this.failDescriptionTitle = title;
        if(contentShort == null) this.failDescriptionContentShort = "";
        else this.failDescriptionContentShort = contentShort;
        if(contentLong == null) this.failDescriptionContentLong = "";
        else this.failDescriptionContentLong = contentLong;
        this.failDate = GregorianCalendar.getInstance(Locale.getDefault()).getTime();
    }

    public String getFailDescriptionTitle() {
        return failDescriptionTitle;
    }

    public String getFailDescriptionContentShort() {
        return failDescriptionContentShort;
    }

    public String getFailDescriptionContentLong() {
        return failDescriptionContentLong;
    }

    public Date getFailDate() {
        return failDate;
    }
}
