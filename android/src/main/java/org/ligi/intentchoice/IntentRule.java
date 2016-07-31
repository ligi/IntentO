package org.ligi.intentchoice;

import android.content.Intent;

public interface IntentRule {
    boolean matchesIntent(Intent intent);
}
