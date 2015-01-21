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
public class ChangePasswordTest {
    private ChangePassword changePassword;

    @Before
    public void setup(){
        changePassword = Robolectric.buildActivity(ChangePassword.class).create().get();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(changePassword);
    }

    @Test
    public void buttonClickShouldStartNewActivity() throws Exception
    {
        Button button1 = (Button) changePassword.findViewById(R.id.btchangepass);
        button1.performClick();
        Intent intent = Robolectric.shadowOf(changePassword).peekNextStartedActivity();
        assertEquals(ChangePassword.class.getCanonicalName(), intent.getComponent().getClassName());

        Button button2 = (Button) changePassword.findViewById(R.id.btcancel);
        button2.performClick();
        Intent intent = Robolectric.shadowOf(changePassword).peekNextStartedActivity();
        assertEquals(DashboardActivity.class.getCanonicalName(), intent.getComponent().getClassName());
    }
}
