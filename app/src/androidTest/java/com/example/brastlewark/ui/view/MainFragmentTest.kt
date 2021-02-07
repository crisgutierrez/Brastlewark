package com.example.brastlewark.ui.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.brastlewark.R
import com.example.brastlewark.model.Gnome
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MainFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var testGnome: Gnome

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun viewMainFragmentTest() {
        // VERIFY
        onView(withId(R.id.main)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.gnome_recycler_view)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun selectGnomeFromListTest() {
        // Nav to fragment_gnome_details
        onView(withId(R.id.gnome_recycler_view)).perform(ViewActions.click())

        // VERIFY
        onView(withId(R.id.fragment_gnome_details)).check(matches(ViewMatchers.isDisplayed()))
    }
}