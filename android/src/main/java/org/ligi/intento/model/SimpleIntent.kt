package org.ligi.intento.model

import android.content.Intent
import android.net.Uri

class SimpleIntent(val action: String, val categories: Set<String>, val data: Uri? = null, val extras: List<Pair<String, Any>> = emptyList(), val type: String? = null) {
    companion object {
        fun fromIntent(intent: Intent): SimpleIntent {
            val extras = intent.extras
            val extrasMap = if (extras==null) emptyList<Pair<String, Any>>() else extras.keySet().map { Pair(it, intent.extras[it]) }
            val categories = if (intent.categories == null) emptySet<String>() else intent.categories
            return SimpleIntent(intent.action, categories, intent.data, extrasMap, intent.type)
        }
    }
}