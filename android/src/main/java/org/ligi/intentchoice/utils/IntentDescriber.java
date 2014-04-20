package org.ligi.intentchoice.utils;

import android.content.Intent;

import com.google.common.base.Joiner;

public class IntentDescriber {
    private final Intent intent;

    public IntentDescriber(Intent intent) {
        this.intent = intent;
    }

    public String getUserFacingCategoriesString() {
        final String categories=Joiner.on(" ").join(intent.getCategories());
        return categories.replaceAll("android\\.intent\\.category\\.","");
    }

    public String getClaspedCategoriesString() {
        if (intent.getCategories()==null) {
            return "";
        }

        if (intent.getCategories().size()>1) {
            return "("+getUserFacingCategoriesString()+")";
        } else {
            return getUserFacingCategoriesString();
        }
    }

    public String getUserFacingActionString() {
        return intent.getAction().replace("android.intent.action.", "");
    }

    public String getUserFacingIntentDescription() {
        final StringBuilder res = new StringBuilder(getUserFacingActionString());
        if (intent.getCategories()!=null) {
            res.append("@" + getClaspedCategoriesString());
        }
        if (intent.getData()!=null) {
            res.append(intent.getData());
        }
        return res.toString();
    }

}
