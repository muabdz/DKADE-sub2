package com.dicoding.muadz.footballmatchschedule

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecycleViewBehavior() {
        Thread.sleep(8000)
        onView(withId(R.id.last_match_list))
            .check(matches(isDisplayed()))
        onView(withId(R.id.last_match_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.last_match_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

    }

    @Test
    fun testAppBehaviour() {
        Thread.sleep(8000)
        onView(ViewMatchers.withText("Man City"))
            .check(matches(isDisplayed()))
        onView(ViewMatchers.withText("Man City")).perform(click())

        Thread.sleep(8000)
        onView(withId(R.id.add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(ViewMatchers.withText("Added to favorite"))
            .check(matches(isDisplayed()))
        Espresso.pressBack()

        onView(withId(R.id.bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(R.id.favorites)).perform(click())

        onView(ViewMatchers.withText("Man City"))
            .check(matches(isDisplayed()))
    }
}