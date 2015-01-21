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
        Button button1 = (Button) loginActivity.findViewById(R.id.btnLogin);
        button1.performClick();
        Intent intent1 = Robolectric.shadowOf(loginActivity).peekNextStartedActivity();
        assertEquals(DashboardActivity.class.getCanonicalName(), intent1.getComponent().getClassName());

        Button button2 = (Button) loginActivity.findViewById(R.id.passres);
        button2.performClick();
        Intent intent2 = Robolectric.shadowOf(loginActivity).peekNextStartedActivity();
        assertEquals(PasswordReset.class.getCanonicalName(), intent2.getComponent().getClassName());

        Button button3 = (Button) loginActivity.findViewById(R.id.btnLinkToRegisterScreen);
        button3.performClick();
        Intent intent3 = Robolectric.shadowOf(loginActivity).peekNextStartedActivity();
        assertEquals(RegisterActivity.class.getCanonicalName(), intent3.getComponent().getClassName());
    }
}
