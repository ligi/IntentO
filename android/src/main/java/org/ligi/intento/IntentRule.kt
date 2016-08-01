package org.ligi.intento

import android.content.Intent

interface IntentRule {
    fun getFollowupIntent(intent: Intent): Intent?
}
