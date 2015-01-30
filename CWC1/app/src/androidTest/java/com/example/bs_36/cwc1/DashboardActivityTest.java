package com.example.bs_36.cwc1;

/**
 * Created by BS-36 on 1/20/2015.
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import android.content.Intent;
import android.widget.Button;

@RunWith(RobolectricTestRunner.class)
public class DashboardActivityTest {
    private DashboardActivity dashboardActivity;

    @Before
    public void setup(){
        dashboardActivity = Robolectric.buildActivity(DashboardActivity.class).create().get();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(dashboardActivity);
    }

//    @Test
//    public void buttonClickShouldStartNewActivity() throws Exception
//    {
//        Button button1 = (Button) dashboardActivity.findViewById(R.id.btnLogout);
//        button1.performClick();
//        Intent intent1 = Robolectric.shadowOf(dashboardActivity).peekNextStartedActivity();
//        assertEquals(LoginActivity.class.getCanonicalName(), intent1.getComponent().getClassName());
//
//        Button button2 = (Button) dashboardActivity.findViewById(R.id.btchangepass);
//        button2.performClick();
//        Intent intent2 = Robolectric.shadowOf(dashboardActivity).peekNextStartedActivity();
//        assertEquals(ChangePassword.class.getCanonicalName(), intent2.getComponent().getClassName());
//    }
}
