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
import static org.robolectric.util.FragmentTestUtil.startFragment;
/**
 * Created by BS-36 on 1/20/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class ChangePasswordTest {
    private chngpass_Fragment changePassword;
//    private DashboardActivity dashboardActivity;
    @Before
    public void setup(){

//        dashboardActivity = Robolectric.buildActivity(DashboardActivity.class).create().get();
        changePassword = new chngpass_Fragment();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        startFragment(changePassword);
        assertNotNull(changePassword);
    }

}
