package org.ligi.intento

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TheIntroActivity {

    @get:Rule
    val rule = ActivityTestRule(IntroActivity::class.java, true, false)

    @Test
    fun shouldDisplayEmptyViewWhenNoRules()  {
        rule.launchActivity(null)
        onView(withId(R.id.emptyView)).check(matches(isDisplayed()))
    }
}