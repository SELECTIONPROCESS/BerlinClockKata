package selectionprocess.berlinclockkata;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class BerlinClockActivityTest {

    @Rule
    public ActivityTestRule<BerlinClockActivity> mActivityRule = new ActivityTestRule<>(BerlinClockActivity.class);

    @Test
    public void berlinTimeConverter() {

        onView(withId(R.id.etDigitalTimeInput)).perform(typeText("00:00:00"));
        onView(withText("YOOOOOOOOOOOOOOOOOOOOOOO")).check(matches(isDisplayed()));

        onView(withId(R.id.etDigitalTimeInput)).perform(clearText());
        onView(withId(R.id.etDigitalTimeInput)).perform(typeText("23:59:59"));
        onView(withText("ORRRRRRROYYRYYRYYRYYYYYY")).check(matches(isDisplayed()));

        onView(withId(R.id.etDigitalTimeInput)).perform(clearText());
        onView(withId(R.id.etDigitalTimeInput)).perform(typeText("16:50:00"));
        onView(withText("YRRROROOOYYRYYRYYRYOOOOO")).check(matches(isDisplayed()));

        onView(withId(R.id.etDigitalTimeInput)).perform(clearText());
        onView(withId(R.id.etDigitalTimeInput)).perform(typeText("11:37:01"));
        onView(withText("ORROOROOOYYRYYRYOOOOYYOO")).check(matches(isDisplayed()));
    }

    @Test
    public void digitalTimeConverter() {

        onView(withId(R.id.etBerlinTimeInput)).perform(typeText("YOOOOOOOOOOOOOOOOOOOOOOO"));
        onView(withText("00:00:00")).check(matches(isDisplayed()));

        onView(withId(R.id.etBerlinTimeInput)).perform(clearText());
        onView(withId(R.id.etBerlinTimeInput)).perform(typeText("ORRRRRRROYYRYYRYYRYYYYYY"));
        onView(withText("23:59:01")).check(matches(isDisplayed()));

        onView(withId(R.id.etBerlinTimeInput)).perform(clearText());
        onView(withId(R.id.etBerlinTimeInput)).perform(typeText("YRRROROOOYYRYYRYYRYOOOOO"));
        onView(withText("16:50:00")).check(matches(isDisplayed()));

        onView(withId(R.id.etBerlinTimeInput)).perform(clearText());
        onView(withId(R.id.etBerlinTimeInput)).perform(typeText("ORROOROOOYYRYYRYOOOOYYOO"));
        onView(withText("11:37:01")).check(matches(isDisplayed()));
    }
}