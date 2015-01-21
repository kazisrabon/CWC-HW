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
public class PasswordResetTest {
//    PasswordReset
    private PasswordReset passwordReset;

        @Before
        public void setup(){
            passwordReset = Robolectric.buildActivity(PasswordReset.class).create().get();
        }

        @Test
        public void checkActivityNotNull() throws Exception {
            assertNotNull(passwordReset);
        }

        @Test
        public void buttonClickShouldStartNewActivity() throws Exception
        {
            Button button1 = (Button) passwordReset.findViewById(R.id.bktolog);
            button1.performClick();
            Intent intent1 = Robolectric.shadowOf(passwordReset).peekNextStartedActivity();
            assertEquals(LoginActivity.class.getCanonicalName(), intent1.getComponent().getClassName());

            Button button2 = (Button) passwordReset.findViewById(R.id.respass);
            button2.performClick();
            Intent intent2 = Robolectric.shadowOf(passwordReset).peekNextStartedActivity();
            assertEquals(PasswordReset.class.getCanonicalName(), intent2.getComponent().getClassName());
        }
}
