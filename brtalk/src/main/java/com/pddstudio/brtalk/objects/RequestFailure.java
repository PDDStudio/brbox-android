package com.pddstudio.brtalk.objects;

import android.support.annotation.Nullable;

/**
 * This Class was created by Patrick J
 * on 24.11.15. For more Details and Licensing
 * have a look at the README.md
 */
public class RequestFailure {

    private final String failDescriptionTitle;
    private final String failDescriptionContentShort;
    private final String failDescriptionContentLong;

    public RequestFailure(@Nullable String title, @Nullable String contentShort, @Nullable String contentLong) {
        if(title == null) this.failDescriptionTitle = "";
        else this.failDescriptionTitle = title;
        if(contentShort == null) this.failDescriptionContentShort = "";
        else this.failDescriptionContentShort = contentShort;
        if(contentLong == null) this.failDescriptionContentLong = "";
        else this.failDescriptionContentLong = contentLong;
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
}
