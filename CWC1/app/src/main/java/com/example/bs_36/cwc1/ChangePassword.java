package com.example.bs_36.cwc1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bs_36.cwc1.library.UserFunctions;
import com.example.bs_36.cwc1.library.DatabaseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by BS-36 on 1/20/2015.
 */
public class ChangePassword extends Activity {
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";

    EditText newpass;
    TextView alert;
    Button changepass;
    Button cancel;
    String newpas, email;
    UserFunctions userFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())) {
            setContentView(R.layout.changepassword);
            getActionBar().setDisplayHomeAsUpEnabled(true);

            cancel = (Button) findViewById(R.id.btcancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    Intent login = new Intent(getApplicationContext(), DashboardActivity.class);

                    startActivity(login);
                    finish();
                }

            });

            newpass = (EditText) findViewById(R.id.newpass);
            alert = (TextView) findViewById(R.id.alertpass);
            changepass = (Button) findViewById(R.id.btchangepass);

            changepass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                new ProcessRegister().execute();

                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                    HashMap<String, String> user = new HashMap<String, String>();
                    user = db.getUserDetails();

                    newpas = newpass.getText().toString();
                    email = user.get("email");

                    UserFunctions userFunction = new UserFunctions();
                    JSONObject json = userFunction.chgPass(newpas, email);

                    alert.setText("Your Password is successfully changed.");

//                try {
//                    if (json.getString(KEY_SUCCESS) != null) {
//                        alert.setText("");
//                        String res = json.getString(KEY_SUCCESS);
//                        String red = json.getString(KEY_ERROR);
//
//
//                        if (Integer.parseInt(res) == 1) {
//                            /**
//                             * Dismiss the process dialog
//                             **/
//                            pDialog.dismiss();
//                            alert.setText("Your Password is successfully changed.");
//
//                        } else if (Integer.parseInt(red) == 2) {
//                            pDialog.dismiss();
//                            alert.setText("Invalid old Password.");
//                        } else {
//                            pDialog.dismiss();
//                            alert.setText("Error occured in changing Password.");
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                }
            });
        }else{
            // user is not logged in show login screen
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            // Closing dashboard screen
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.changepassword, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.help:
                Toast.makeText(getBaseContext(), "Help", Toast.LENGTH_SHORT).show();
                break;

            case R.id.about:
                Toast.makeText(getBaseContext(), "About", Toast.LENGTH_SHORT)
                        .show();
                // actionBar.hide();
                break;

            case R.id.call:
                Toast.makeText(getBaseContext(), "Call", Toast.LENGTH_SHORT).show();
                break;

            case R.id.share:
                Intent ShareIntent = new Intent();
                ShareIntent.setAction(Intent.ACTION_SEND);
                ShareIntent.setType("text/plain");
                //Put profile specific text here
                ShareIntent.putExtra(Intent.EXTRA_TEXT, "My Profile");
                PackageManager pm = getPackageManager();
                List<ResolveInfo> resolve = pm.queryIntentActivities(ShareIntent,
                        0);
                boolean isAvailable = resolve.size() > 0;
                if (isAvailable) {
                    startActivity(ShareIntent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No Handling Application Found,Please Install at least one (facebook,email or sms)", Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            case R.id.logout:
                userFunctions.logoutUser(getApplicationContext());
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
