package org.ligi.intento.etc

import dagger.Module
import dagger.Provides
import org.ligi.intento.App
import org.ligi.intento.IntentRuleProvider
import javax.inject.Singleton

@Module
class TestModule(val app: App) {

    companion object {
        var intentRuleProvider = IntentRuleProvider()
    }


    @Provides
    @Singleton
    fun provideIntentRuleProvider(): IntentRuleProvider {
        return intentRuleProvider
    }

    @Provides
    @Singleton
    fun provideApp(): App {
        return app
    }
}
