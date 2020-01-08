package br.com.soluevo.microapplibrary.application.fragments.products.products

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.utils.RecyclerViewMatcher.Companion.withRecyclerView
import br.com.soluevo.utils.SetupMockServerManager
import br.com.soluevo.utils.TestUtils.waitEspresso
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class ProductsFragmentTest {

    abstract class Describe_Products_Network : SetupMockServerManager() {

        private val mockNavController = Mockito.mock(NavController::class.java)

        @Before
        fun init() {
            val scenario =
                launchFragmentInContainer<ProductsFragment>(themeResId = R.style.AppTheme)

            scenario.onFragment {
                Navigation.setViewNavController(it.requireView(), mockNavController)
            }
        }

    }

    internal class Context_Success : Describe_Products_Network() {

        @Before
        fun setup() {
            mockServer.setDispatcher(ProductsMockServerDispatcher().RequestDispatcher())

            waitEspresso(5000)
        }

        @Test
        fun testTitleIsDisplayed() {
            onView(
                CoreMatchers.allOf(
                    withId(R.id.titleTextView),
                    isDescendantOfA(
                        withRecyclerView(R.id.recyclerView).atPosition(
                            0
                        )
                    )
                )
            ).check(matches(isDisplayed()))
        }

        @Test
        fun testDescriptionIsDisplayed() {
            onView(
                CoreMatchers.allOf(
                    withId(R.id.descriptionTextView),
                    isDescendantOfA(
                        withRecyclerView(R.id.recyclerView).atPosition(
                            0
                        )
                    )
                )
            ).check(matches(isDisplayed()))
        }

    }


}