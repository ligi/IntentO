package org.ligi.intento

import android.app.Application
import org.ligi.intento.etc.AppComponent
import org.ligi.intento.etc.AppModule
import org.ligi.intento.etc.DaggerAppComponent

open class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = createComponent()
    }

    open fun createComponent() = DaggerAppComponent.builder().appModule(AppModule(this)).build()
}
