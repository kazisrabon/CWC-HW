package com.example.bs_36.cwc1;

import android.content.Intent;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by BS-36 on 1/20/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class RegisterActivityTest {
//RegisterActivity
    private RegisterActivity registerActivity;

    @Before
    public void setup(){
        registerActivity = Robolectric.buildActivity(RegisterActivity.class).create().get();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(registerActivity);
    }

    @Test
    public void buttonClickShouldStartNewActivity() throws Exception
    {
        Button button1 = (Button) registerActivity.findViewById(R.id.btnRegister);
        button1.performClick();
        Intent intent1 = Robolectric.shadowOf(registerActivity).peekNextStartedActivity();
        assertEquals(DashboardActivity.class.getCanonicalName(), intent1.getComponent().getClassName());

        Button button2 = (Button) registerActivity.findViewById(R.id.btnLinkToRegisterScreen);
        button2.performClick();
        Intent intent2 = Robolectric.shadowOf(registerActivity).peekNextStartedActivity();
        assertEquals(LoginActivity.class.getCanonicalName(), intent2.getComponent().getClassName());
    }
}
