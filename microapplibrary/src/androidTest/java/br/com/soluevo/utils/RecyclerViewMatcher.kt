package br.com.soluevo.utils

import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            internal var resources: Resources? = null
            internal var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = Integer.toString(recyclerViewId)
                if (this.resources != null) {
                    try {
                        idDescription = this.resources!!.getResourceName(recyclerViewId)
                    } catch (var4: Resources.NotFoundException) {
                        idDescription = String.format("%s (resource name not found)", recyclerViewId)
                    }

                }

                description.appendText("RecyclerView with id: $idDescription at position: $position")
            }

            public override fun matchesSafely(view: View): Boolean {

                this.resources = view.resources

                if (childView == null) {
                    val recyclerView = view.rootView.findViewById<View>(recyclerViewId) as RecyclerView
                    if (recyclerView != null && recyclerView!!.getId() === recyclerViewId) {
                        val viewHolder = recyclerView!!.findViewHolderForAdapterPosition(position)
                        if (viewHolder != null) {
                            childView = viewHolder!!.itemView
                        }
                    } else {
                        return false
                    }
                }

                if (targetViewId == -1) {
                    return view === childView
                } else {
                    val targetView = childView!!.findViewById<View>(targetViewId)
                    return view === targetView
                }
            }
        }
    }

    companion object {

        fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {

            return RecyclerViewMatcher(recyclerViewId)
        }

        fun checkIfDisplayedOnRecycleViewItem(recyclerViewId: Int, position: Int, contentId: Int) {
            onView(withRecyclerView(recyclerViewId).atPosition(position))
                .check(matches(hasDescendant(withId(contentId))))
        }


        fun checkTextOnRecycleViewItem(recyclerViewId: Int, position: Int, contentId: Int, text: String) {
            onView(
                withRecyclerView(recyclerViewId)
                    .atPositionOnView(position, contentId)
            )
                .check(matches(withText(text)))
        }

        fun checkIfIsDisplayedWithTextOnRecyclerView(recyclerViewId: Int, position: Int, text: String) {
            onView(withRecyclerView(recyclerViewId).atPosition(position))
                .check(matches(hasDescendant(withText(text))))
        }

        fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
            checkNotNull(itemMatcher)
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    itemMatcher.describeTo(description)
                }

                protected override fun matchesSafely(view: RecyclerView): Boolean {
                    val viewHolder = view.findViewHolderForAdapterPosition(position) ?: return false
                    return itemMatcher.matches(viewHolder.itemView)
                }
            }
        }

        fun getItemCountFromRecyclerView(@IdRes RecyclerViewId: Int): Int {
            val COUNT = IntArray(1)
            val matcher = object : TypeSafeMatcher<View>() {
                override fun matchesSafely(item: View): Boolean {
                    COUNT[0] = (item as RecyclerView).getAdapter()?.itemCount!!
                    return true
                }

                override fun describeTo(description: Description) {}
            }
            onView(allOf(withId(RecyclerViewId), isDisplayed())).check(matches(matcher))
            val result = COUNT[0]
            COUNT[0] = 0
            return result
        }
    }

}
