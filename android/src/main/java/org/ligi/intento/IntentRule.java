package org.ligi.intento;

import android.content.Intent;

public interface IntentRule {
    boolean matchesIntent(Intent intent);
}
