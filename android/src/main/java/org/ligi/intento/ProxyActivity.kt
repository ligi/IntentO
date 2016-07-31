package org.ligi.intento

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class ProxyActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.setClassName(BuildConfig.APPLICATION_ID,ChooserActivity::class.java.name)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

}
