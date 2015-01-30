package com.example.bs_36.cwc1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bs_36.cwc1.library.DatabaseHandler;

import java.util.HashMap;

/**
 * Created by BS-36 on 1/26/2015.
 */
public class profile_Fragment extends Fragment {

    private LinearLayout profile;
    private FragmentActivity dashboardActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardActivity  = (FragmentActivity)    super.getActivity();
        profile    = (LinearLayout)    inflater.inflate(R.layout.menu1_layout, container, false);
        return profile;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());

        HashMap<String,String> user = new HashMap<String, String>();
        user = db.getUserDetails();

        final TextView fname = (TextView)profile.findViewById(R.id.fname);
        fname.setText(user.get("name"));
    }
}
