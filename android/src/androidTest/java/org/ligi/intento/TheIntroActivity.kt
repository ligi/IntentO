package org.ligi.intento

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.Visibility.GONE
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.ligi.intento.etc.TestModule
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class TheIntroActivity {

    @get:Rule
    val rule = ActivityTestRule(IntroActivity::class.java, true, false)

    @Test
    fun shouldDisplayEmptyViewWhenNoRules()  {
        TestModule.intentRuleProvider.intentRules.clear()
        rule.launchActivity(null)

        onView(withId(R.id.emptyView)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldNotDisplayEmptyViewWhenRules()  {
        TestModule.intentRuleProvider.intentRules.add(Mockito.mock(IntentRule::class.java))
        rule.launchActivity(null)

        onView(withId(R.id.emptyView)).check(matches(withEffectiveVisibility(GONE)))
    }

}