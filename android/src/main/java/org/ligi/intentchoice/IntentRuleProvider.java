package org.ligi.intentchoice;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IntentRuleProvider {

    private List<IntentRule> intentToActionList;

    public IntentRuleProvider() {
        intentToActionList = new ArrayList<>();
    }

    public List<IntentRule> getRulesForIntent(Intent intent) {
        List<IntentRule> res = new ArrayList<>();
        for (IntentRule intentRule : intentToActionList) {
            if (intentRule.matchesIntent(intent)) {
                res.add(intentRule);
            }
        }

        return res;
    }

    public List<IntentRule> getIntentToActionList() {
        return intentToActionList;
    }
}
