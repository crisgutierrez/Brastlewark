package com.example.brastlewark.ui.view

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.brastlewark.R
import com.example.brastlewark.model.Gnome
import com.example.brastlewark.testsuport.FakeGnome
import com.example.brastlewark.testsuport.FakeGnomeNoProfessionNoFriends
import com.example.brastlewark.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class GnomeDetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var testGnome: Gnome

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun gnomeDetailsFragmentAllDataTest() {
        // SETUP
        val bundle = Bundle()
        bundle.putSerializable("gnome", testGnome)

        val scenario = launchFragmentInHiltContainer<GnomeDetailsFragment>(
            fragmentArgs = bundle,
            factory = FragmentFactory()
        )

        // VERIFY
        onView(withId(R.id.gnome_name)).check(matches(withText(testGnome.name)))
        onView(withId(R.id.gnome_age)).check(matches(withText(FakeGnome.age)))
        onView(withId(R.id.gnome_weight)).check(matches(withText(FakeGnome.weight)))
        onView(withId(R.id.gnome_height)).check(matches(withText(FakeGnome.height)))
        onView(withId(R.id.gnome_hair_color)).check(matches(withText(FakeGnome.hairColor)))
        onView(withId(R.id.gnome_profession)).check(matches(withText(FakeGnome.professions)))
        onView(withId(R.id.gnome_friend)).check(matches(withText(FakeGnome.friends)))
    }

    @Test
    fun gnomeDetailsFragmentNoProfessionTest() {
        // SETUP
        testGnome.friends = emptyList()
        testGnome.professions = emptyList()
        val bundle = Bundle()
        bundle.putSerializable("gnome", testGnome)

        val scenario = launchFragmentInHiltContainer<GnomeDetailsFragment>(
            fragmentArgs = bundle,
            factory = FragmentFactory()
        )

        // VERIFY
        onView(withId(R.id.gnome_name)).check(matches(withText(testGnome.name)))
        onView(withId(R.id.gnome_age)).check(matches(withText(FakeGnomeNoProfessionNoFriends.age)))
        onView(withId(R.id.gnome_weight)).check(matches(withText(FakeGnomeNoProfessionNoFriends.weight)))
        onView(withId(R.id.gnome_height)).check(matches(withText(FakeGnomeNoProfessionNoFriends.height)))
        onView(withId(R.id.gnome_hair_color)).check(matches(withText(FakeGnomeNoProfessionNoFriends.hairColor)))
        onView(withId(R.id.gnome_profession)).check(matches(withText(FakeGnomeNoProfessionNoFriends.professions)))
        onView(withId(R.id.gnome_friend)).check(matches(withText(FakeGnomeNoProfessionNoFriends.friends)))
    }
}