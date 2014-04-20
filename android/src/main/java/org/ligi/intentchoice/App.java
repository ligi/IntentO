package org.ligi.intentchoice;

import android.app.Application;

public class App extends Application {

    private static IntentActionProvider actionProvider;

    public static IntentActionProvider getActionProvider() {
        return actionProvider;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        actionProvider=new IntentActionProvider();
    }

}
