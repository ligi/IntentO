package org.ligi.intento.etc;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.ligi.intento.App;
import org.ligi.intento.IntentRuleProvider;

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public IntentRuleProvider provideIntentRuleProvider() {
        return new IntentRuleProvider();
    }

    @Provides
    @Singleton
    public App provideApp() {
        return app;
    }


}
