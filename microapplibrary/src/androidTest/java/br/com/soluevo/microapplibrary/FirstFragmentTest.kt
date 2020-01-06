package br.com.soluevo.microapplibrary

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {

    private val mockNavController = mock(NavController::class.java)

    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<FirstFragment>(themeResId = R.style.AppTheme)

        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mockNavController)
        }
    }

    @Test
    fun testNavigationToInSecondFragment() {
        onView(withId(R.id.button)).perform(click())
        verify(mockNavController).navigate(R.id.action_firstFragment_to_secondFragment)
    }

}