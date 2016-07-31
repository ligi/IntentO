package org.ligi.intentchoice;

import android.app.Application;

public class App extends Application {

    private static IntentRuleProvider actionProvider;

    public static IntentRuleProvider getActionProvider() {
        return actionProvider;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        actionProvider=new IntentRuleProvider();
    }

}
