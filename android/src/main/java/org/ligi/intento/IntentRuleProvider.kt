package org.ligi.intento

import android.content.Intent
import java.util.ArrayList

class IntentRuleProvider {

    val intentRules = ArrayList<IntentRule>()

    class SimpleIntentRule(intent: Intent, val packageName: String, val className: String) : IntentRule {

        private val action = intent.action
        private val categories = intent.categories

        override fun getFollowupIntent(intent: Intent): Intent? {

            if (action != null && !intent.action.equals((action))) {
                return null
            }

            if (categories != null && !categories.equals(intent.categories)) {
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
