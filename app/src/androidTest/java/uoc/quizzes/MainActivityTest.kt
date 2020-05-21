package uoc.quizzes


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// The major part of the code corresponds to the result of execute run-> Record Espresso Test, checking
// some variables that are indicated below.
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

        // Here place the Toast checker. It checks that if no radiobutton is selected, the corresponding toast is visible
        onView(withText(R.string.SelectOption)).inRoot(
            withDecorView(
                not(
                    `is`(
                        mActivityTestRule.activity!!.window.decorView
                    )
                )
            )
        ).check(matches(isDisplayed()))


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

        // Here place the Text View checker. It checks that the correct answer is selected
        val textView = onView(withId(R.id.tv_2))
        textView.check(matches(withText(R.string.Texto_resultado)))

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

        // Here place the Text View checker. It checks that all the correct answer are selected
        val textView2 = onView(withId(R.id.tv_2))
        textView2.check(matches(withText(R.string.Felicidades_txt)))
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
