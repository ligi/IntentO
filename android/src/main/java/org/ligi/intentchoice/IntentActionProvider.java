package org.ligi.intentchoice;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntentActionProvider {

    private Map<IntentMatcher, ConditionalAction> intentToActionMap;

    public IntentActionProvider() {
        intentToActionMap=new HashMap<IntentMatcher,ConditionalAction>();
    }

    public List<ConditionalAction> getActionsForIntent(Intent intent) {
        List<ConditionalAction> res=new ArrayList<>();
        for (IntentMatcher intentMatcher : intentToActionMap.keySet()) {
            if (intentMatcher.matchesIntent(intent)) {
                res.add(intentToActionMap.get(intentMatcher));
            }
        }

        return res;
    }

    public Map<IntentMatcher, ConditionalAction> getIntentToActionMap() {
        return intentToActionMap;
    }
}
