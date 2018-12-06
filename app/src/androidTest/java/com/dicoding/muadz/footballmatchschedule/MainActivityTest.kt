package com.dicoding.muadz.footballmatchschedule

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private var tvTeam1: String? = null
    private var tvTeam2: String? = null
    private var lastMatchList: RecyclerView? = null
    private val removeFavorite = "Removed from favorite"
    private val addFavorite = "Added to favorite"

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setup() {
        activityRule.launchActivity(null)
        lastMatchList = activityRule.activity.findViewById(R.id.last_match_list)
    }

    @Test
    fun testAppBehaviour() {
        Thread.sleep(5000)
        onView(withId(R.id.last_match_list))
            .check(matches(isDisplayed()))
        onView(withId(R.id.last_match_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        tvTeam1 = lastMatchList?.findViewHolderForAdapterPosition(1)?.itemView?.findViewById<TextView>(R.id.tvTeam1)
            ?.text.toString()
        tvTeam2 = lastMatchList?.findViewHolderForAdapterPosition(1)?.itemView?.findViewById<TextView>(R.id.tvTeam2)
            ?.text.toString()

        Thread.sleep(3000)
        onView(withId(R.id.add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite))
            .perform(click())
        onView(withText(addFavorite))
            .check(matches(isDisplayed()))

        Espresso.pressBack()
        onView(withId(R.id.bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(R.id.favorites))
            .perform(click())

        onView(withId(R.id.favoritematch_list))
            .check(matches(isDisplayed()))
        onView(allOf(withText(tvTeam1), hasSibling(withText(tvTeam2))))
            .check(matches(isDisplayed()))
            .perform(click())

        Thread.sleep(3000)
        onView(withId(R.id.add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite))
            .perform(click())
        onView(withText(removeFavorite))
            .check(matches(isDisplayed()))

    }
}