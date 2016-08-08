package org.ligi.intento;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;
import org.ligi.intento.etc.TestApp;

public class AppReplacingRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(final ClassLoader cl,
                                      final String className,
                                      final Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestApp.class.getName(), context);
    }
}
