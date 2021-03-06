package org.ligi.intento.utils

import org.ligi.intento.model.SimpleIntent

class IntentDescriber(val intent: SimpleIntent) {

    val userFacingCategoriesString: String
        get() {
            val categories = intent.categories.joinToString(separator = " ")
            return categories.replace("android\\.intent\\.category\\.".toRegex(), "")
        }

    val claspedCategoriesString: String
        get() {
            if (intent.categories == null) {
                return ""
            }

            if (intent.categories.size > 1) {
                return "($userFacingCategoriesString)"
            } else {
                return userFacingCategoriesString
            }
        }

    val userFacingActionString: String
        get() = intent.action.replace("android.intent.action.", "")

    val userFacingIntentDescription: String
        get() {
            val res = StringBuilder(userFacingActionString)
            if (!intent.categories.isEmpty()) {
                res.append("@$claspedCategoriesString")
            }
            if (intent.data != null) {
                res.append(":${intent.data}")
            }
            return res.toString()
        }

}
