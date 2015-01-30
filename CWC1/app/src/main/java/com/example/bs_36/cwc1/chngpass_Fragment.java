package com.example.bs_36.cwc1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bs_36.cwc1.library.DatabaseHandler;
import com.example.bs_36.cwc1.library.UserFunctions;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by BS-36 on 1/26/2015.
 */
public class chngpass_Fragment extends Fragment {
    private RelativeLayout changePassword;
    private FragmentActivity dashboardActivity;
    EditText newpass;
    TextView alert;
    Button changepass;
    String newpas, email;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardActivity  = (FragmentActivity)    super.getActivity();
        changePassword = (RelativeLayout) inflater.inflate(R.layout.changepassword, container, false);

        return changePassword;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        newpass = (EditText) changePassword.findViewById(R.id.newpass);
        alert = (TextView) changePassword.findViewById(R.id.alertpass);
        changepass = (Button) changePassword.findViewById(R.id.btchangepass);

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new ProcessRegister().execute();

                DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());

                HashMap<String, String> user = new HashMap<String, String>();
                user = db.getUserDetails();

                newpas = newpass.getText().toString();
                email = user.get("email");

                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.chgPass(newpas, email);

                Toast.makeText(getActivity().getApplicationContext(), "Your Password is successfully changed.", Toast.LENGTH_SHORT).show();
//                    alert.setText("Your Password is successfully changed.");

            }
        });
    }
}
