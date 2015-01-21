package com.example.bs_36.cwc1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import android.content.Intent;
import android.widget.Button;
/**
 * Created by BS-36 on 1/20/2015.
 */
@RunWith(RobolectricTestRunner.class)

public class LoginActivityTest {
    private LoginActivity loginActivity;

    @Before
    public void setup(){
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().get();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(loginActivity);
    }

    @Test
    public void buttonClickShouldStartNewActivity() throws Exception
    {
        Button button = (Button) loginActivity.findViewById(R.id.btnLogin);
        button.performClick();
        Intent intent = Robolectric.shadowOf(loginActivity).peekNextStartedActivity();
        assertEquals(DashboardActivity.class.getCanonicalName(), intent.getComponent().getClassName());
    }
}
