package com.example.waleed.mvi.main

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.waleed.mvi.R
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testProgressState() {
        val mainActivity = rule.activity as MainActivity
        mainActivity.render(MainViewState(true))

        Espresso.onView(ViewMatchers.withId(R.id.progressBar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.waleed.mvi", appContext.packageName)
    }
}