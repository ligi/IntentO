package org.ligi.intento

import android.app.Application

class App : Application() {

    companion object {
        val actionProvider by lazy { IntentRuleProvider() }
    }

}
