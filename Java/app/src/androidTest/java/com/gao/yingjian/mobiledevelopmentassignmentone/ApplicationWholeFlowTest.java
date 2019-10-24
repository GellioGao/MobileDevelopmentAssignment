package com.gao.yingjian.mobiledevelopmentassignmentone;

import android.app.Activity;
import android.util.Log;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import com.gao.yingjian.mobiledevelopmentassignmentone.Views.LoginActivity;
import com.gao.yingjian.mobiledevelopmentassignmentone.Views.SurveyListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collection;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertEquals;

public class ApplicationWholeFlowTest {
    private static final String VALID_USERNAME = "Valid Username";
    private static final String VALID_PASSWORD = "ValidPassword";
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private LoginActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

    Activity currentActivity;
    private Activity getActivityInstance(){
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> resumedActivities =
                        ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                for (Activity activity: resumedActivities){
                    Log.d("Your current activity: ", activity.getClass().getName());
                    currentActivity = activity;
                    break;
                }
            }
        });

        return currentActivity;
    }

    @Test
    public void whole_flow_running(){
        onView(withId(R.id.tieUsername))
                .perform(clearText(), typeText(VALID_USERNAME));
        closeSoftKeyboard();

        onView(withId(R.id.tiePassword))
                .perform(typeText(VALID_PASSWORD));
        closeSoftKeyboard();

        onView(withId(R.id.btnLogin))
                .perform(click());

        onData(anything())
                .inAdapterView(withId(R.id.gdvTask))
                .atPosition(0)
                .perform(click());

        onData(anything())
                .inAdapterView(withId(R.id.ltvSurveyList))
                .atPosition(1)
                .perform(click());

        onView(withId(R.id.btnDoneSurvey))
                .perform(click());

        onView(withId(R.id.btnSubmitSurvey))
                .perform(click());

        Activity surveyList = getActivityInstance();

        assertEquals(surveyList.getClass().getTypeName(), SurveyListActivity.class.getTypeName());
    }
}
