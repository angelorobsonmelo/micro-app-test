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
import org.mockito.Mockito
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {

    @Test
    fun testNavitationScreen() {
        val mockNavController = Mockito.mock(NavController::class.java)
        val scenario = launchFragmentInContainer<FirstFragment>(themeResId = R.style.AppTheme)

        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mockNavController)
        }

        onView(withId(R.id.button)).perform(click())

        verify(mockNavController).navigate(R.id.action_firstFragment_to_secondFragment)
    }

}