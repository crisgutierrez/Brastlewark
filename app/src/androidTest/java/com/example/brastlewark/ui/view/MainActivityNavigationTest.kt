package com.example.brastlewark.ui.view

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.brastlewark.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityNavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun gnomeDetailsFragmentAllDataTest() {
        // SETUP
        val activityScenario =launchActivity<MainActivity>()

        // Nav to fragment_gnome_details
        onView(withId(R.id.gnome_recycler_view)).perform(click())

        // VERIFY
        onView(withId(R.id.fragment_gnome_details)).check(matches(isDisplayed()))

        // Nav back
        pressBack()

        // VERIFY
        onView(withId(R.id.main)).check(matches(isDisplayed()))

    }
}