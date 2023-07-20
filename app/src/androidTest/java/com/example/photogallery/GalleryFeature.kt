package com.example.photogallery

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class GalleryFeature {

    val mainActivity = ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayAppTitle() {
        assertDisplayed("PhotoGallery")
    }

    @Test
    fun displayListOfGallery() {

        Thread.sleep(4000)

        BaristaRecyclerViewAssertions.assertRecyclerViewItemCount(R.id.gallery_list, 25)

        onView(
            AllOf.allOf(
                withId(R.id.owner_id),
                ViewMatchers.isDescendantOfA(nthChildOf(withId(R.id.gallery_list), 0))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("123041183@N05")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(
            AllOf.allOf(
                withId(R.id.owner_name),
                ViewMatchers.isDescendantOfA(nthChildOf(withId(R.id.gallery_list), 0))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("federico.fragale")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(
            AllOf.allOf(
                withId(R.id.tags),
                ViewMatchers.isDescendantOfA(nthChildOf(withId(R.id.gallery_list), 0))
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun navigateToSecondScreen(){

        onView(
            AllOf.allOf(
                withId(R.id.gallery_image),
                ViewMatchers.isDescendantOfA(nthChildOf(withId(R.id.gallery_list), 0))
            )
        ).perform(click())

        assertDisplayed(R.id.image_details_screen)

    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }
}