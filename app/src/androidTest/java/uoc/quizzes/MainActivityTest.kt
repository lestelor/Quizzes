package uoc.quizzes


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val appCompatButton = onView(
            allOf(
                withId(R.id.button_send), withText("Enviar"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    0
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())

        val appCompatRadioButton = onView(
            allOf(
                withId(R.id.radioButton2), withText("Sagrada Família"),
                childAtPosition(
                    allOf(
                        withId(R.id.radiogoup1),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            3
                        )
                    ),
                    1
                )
            )
        )
        appCompatRadioButton.perform(scrollTo(), click())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.button_send), withText("Enviar"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    0
                )
            )
        )
        appCompatButton2.perform(scrollTo(), click())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.button_a2), withText("Siguiente"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        val appCompatRadioButton2 = onView(
            allOf(
                withId(R.id.radioButton1), withText("Sponge Bob Square Pants"),
                childAtPosition(
                    allOf(
                        withId(R.id.radiogoup1),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            3
                        )
                    ),
                    0
                )
            )
        )
        appCompatRadioButton2.perform(scrollTo(), click())

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.button_send), withText("Enviar"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    0
                )
            )
        )
        appCompatButton4.perform(scrollTo(), click())

        val appCompatButton5 = onView(
            allOf(
                withId(R.id.button_a2), withText("Siguiente"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatButton5.perform(click())

        val appCompatRadioButton3 = onView(
            allOf(
                withId(R.id.radioButton1), withText("Jupiter"),
                childAtPosition(
                    allOf(
                        withId(R.id.radiogoup1),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            3
                        )
                    ),
                    0
                )
            )
        )
        appCompatRadioButton3.perform(scrollTo(), click())

        val appCompatButton6 = onView(
            allOf(
                withId(R.id.button_send), withText("Enviar"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    0
                )
            )
        )
        appCompatButton6.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
