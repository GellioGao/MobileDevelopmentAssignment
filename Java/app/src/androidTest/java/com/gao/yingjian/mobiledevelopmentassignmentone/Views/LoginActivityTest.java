package com.gao.yingjian.mobiledevelopmentassignmentone.Views;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.gao.yingjian.mobiledevelopmentassignmentone.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.gao.yingjian.mobiledevelopmentassignmentone.TextInputLayoutErrorText.hasTextInputLayoutErrorText;
import static org.junit.Assert.*;

public class LoginActivityTest {
    private static final String EMPTY_STRING = "";
    private static final String VALID_USERNAME = "Valid Username";
    private static final String VALID_PASSWORD = "ValidPassword";
    private static final String INVALID_USERNAME = "NO";
    private static final String INVALID_SHORT_PASSWORD = "invalid";
    private static final String INVALID_LONG_PASSWORD = "invalidlongpasswordinvalidlongpasswordinvalidlongpassword";

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);
    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeNavigationDrawerActivity.class.getName(), null, false);
    private LoginActivity activity;
    private void incorrectLogin(String username, String password) {
        onView(withId(R.id.tieUsername))
                .perform(clearText(), typeText(username));
        closeSoftKeyboard();

        onView(withId(R.id.tiePassword))
                .perform(typeText(password));
        closeSoftKeyboard();

        onView(withId(R.id.btnLogin))
                .perform(click());
    }
    private void correctLogin() {
        onView(withId(R.id.tieUsername))
                .perform(clearText(), typeText(VALID_USERNAME));
        closeSoftKeyboard();

        onView(withId(R.id.tiePassword))
                .perform(typeText(VALID_PASSWORD));
        closeSoftKeyboard();

        onView(withId(R.id.btnLogin))
                .perform(click());

    }
    private void checkErrorMessages(int usernameMessageId, int passwordMessageId) {
        onView(withId(R.id.tilUsername))
                .check(matches(hasTextInputLayoutErrorText(activity.getString(usernameMessageId))));

        onView(withId(R.id.tilPassword))
                .check(matches(hasTextInputLayoutErrorText(activity.getString(passwordMessageId))));
    }
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }
    @After
    public void tearDown() throws Exception {
        activity = null;
    }
    @Test
    public void testUserInputScenario_empty_username_and_empty_password_message(){
        incorrectLogin(EMPTY_STRING, EMPTY_STRING);
        checkErrorMessages(R.string.login_username_error_message_empty_string, R.string.login_password_error_message_empty_string);
    }
    @Test
    public void testUserInputScenario_invalid_username_and_empty_password_message(){
        incorrectLogin(INVALID_USERNAME, EMPTY_STRING);
        checkErrorMessages(R.string.login_username_error_message_too_short_string, R.string.login_password_error_message_empty_string);
    }
    @Test
    public void testUserInputScenario_empty_username_and_invalid_short_password_message(){
        incorrectLogin(EMPTY_STRING, INVALID_SHORT_PASSWORD);
        checkErrorMessages(R.string.login_username_error_message_empty_string, R.string.login_password_error_message_too_short_string);
    }
    @Test
    public void testUserInputScenario_empty_username_and_invalid_long_password_message(){
        incorrectLogin(EMPTY_STRING, INVALID_LONG_PASSWORD);
        checkErrorMessages(R.string.login_username_error_message_empty_string, R.string.login_password_error_message_too_long_string);
    }
    @Test
    public void testUserInputScenario_invalid_username_and_invalid_short_password_message(){
        incorrectLogin(INVALID_USERNAME, INVALID_SHORT_PASSWORD);
        checkErrorMessages(R.string.login_username_error_message_too_short_string, R.string.login_password_error_message_too_short_string);
    }
    @Test
    public void testUserInputScenario_invalid_username_and_invalid_long_password_message(){
        incorrectLogin(INVALID_USERNAME, INVALID_LONG_PASSWORD);
        checkErrorMessages(R.string.login_username_error_message_too_short_string, R.string.login_password_error_message_too_long_string);
    }
    @Test
    public void testActivityJumping(){
        correctLogin();
        Activity activity2 = getInstrumentation()
                .waitForMonitorWithTimeout(monitor, 0);
        assertNotNull(activity2);
    }
}