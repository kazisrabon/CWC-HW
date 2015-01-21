package com.example.bs_36.cwc1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.fest.assertions.api.android.*;
/**
 * Created by BS-36 on 1/20/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class LoginActivityTest {
    private LoginActivityTest loginActivity;

    @Before
    public void setup(){
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().get();
    }

    @Test
    public void loginActivityAppearsAsExpectedInitially(){
//        assert (loginActivity.btnLogin)
    }
}
