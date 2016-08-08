package org.ligi.intento

import android.content.Intent
import android.graphics.Bitmap
import org.ligi.intento.model.SimpleIntent
import java.util.*

class IntentRuleProvider {

    val intentRules = ArrayList<IntentRule>()

    class SimpleIntentRule(intent: Intent, val packageName: String, val className: String,val icon: Bitmap) : IntentRule {

        val simpleIntent = SimpleIntent.fromIntent(intent)

        override fun getFollowupIntent(intent: Intent): Intent? {

            if ( !intent.action.equals((simpleIntent.action))) {
                return null
            }

            if ( !simpleIntent.categories.equals(intent.categories)) {
                return null
            }

            intent.setClassName(packageName, className)
            return intent
        }
    }

    fun getFollowUpIntent(intent: Intent): Intent? {

        if (intent.hasExtra("fromNotification")) {
            intentRules.removeAll { it.getFollowupIntent(intent) != null }
            return null
        }

        return intentRules.firstOrNull { it.getFollowupIntent(intent) != null }?.getFollowupIntent(intent)
    }
}
