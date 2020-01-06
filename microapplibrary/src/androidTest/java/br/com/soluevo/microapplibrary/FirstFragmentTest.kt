package br.com.soluevo.microapplibrary

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {

    @Test
    fun testNavitationScreen() {
        // Create a mock NavController
//        val mockNavController = mock(NavController::class.java)


        val scenario = launchFragmentInContainer<FirstFragment>(themeResId = R.style.AppTheme2)

        scenario.onFragment {
//            Navigation.setViewNavController(it.requireView())

        }

        onView(withId(R.id.button)).perform(click())

//        onView(withId(R.id.textView)).check(matches(isDisplayed()))

    }

}