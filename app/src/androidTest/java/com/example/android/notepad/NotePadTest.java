package com.example.android.notepad;

import android.test.ActivityInstrumentationTestCase2;

import com.google.android.libraries.cloudtesting.screenshots.ScreenShotter;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class NotePadTest extends ActivityInstrumentationTestCase2<NotesList> {

    public NotePadTest() {
        super(NotesList.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testAddNote() throws InterruptedException {
        // Take a screenshot when app becomes visible.
        onView(isRoot());
        ScreenShotter.takeScreenshot("main_screen_1", getActivity());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Add note")).perform(click());
        onView(allOf(withId(R.id.note), hasFocus())).perform(typeTextIntoFocusedView("Note 1"));
        closeSoftKeyboard();
        pressBack();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Add note")).perform(click());
        onView(allOf(withId(R.id.note), hasFocus())).perform(typeTextIntoFocusedView("Note 2"));
        closeSoftKeyboard();
        pressBack();

        onView(withText("Note 1")).check(matches(isDisplayed()));
        onView(withText("Note 2")).check(matches(isDisplayed()));

        // Take one more screenshot at the end of the test.
        ScreenShotter.takeScreenshot("main_screen_2", getActivity());
    }
}

