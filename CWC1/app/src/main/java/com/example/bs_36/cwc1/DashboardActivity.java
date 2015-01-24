package com.example.bs_36.cwc1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import com.example.bs_36.cwc1.library.UserFunctions;
import com.example.bs_36.cwc1.library.DatabaseHandler;



public class DashboardActivity extends Activity {
    UserFunctions userFunctions;
    Button btnLogout;
    Button changepas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Dashboard Screen for the application
         * */
        // Check login status in database
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
            setContentView(R.layout.dashboard);

            DatabaseHandler db = new DatabaseHandler(getApplicationContext());

            HashMap<String,String> user = new HashMap<String, String>();
            user = db.getUserDetails();

            final TextView fname = (TextView)findViewById(R.id.fname);
            fname.setText(user.get("name"));

            changepas = (Button) findViewById(R.id.btchangepass);
            changepas.setOnClickListener(new View.OnClickListener(){
                public void onClick(View arg0){

                    Intent chgpass = new Intent(getApplicationContext(), ChangePassword.class);

                    startActivity(chgpass);
                }

            });

            btnLogout = (Button) findViewById(R.id.btnLogout);
            btnLogout.setOnClickListener(new View.OnClickListener() {

                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    userFunctions.logoutUser(getApplicationContext());
                    Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    // Closing dashboard screen
                    finish();
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.search:
                Toast.makeText(getBaseContext(), "Search", Toast.LENGTH_SHORT)
                        .show();
                break;

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
                Intent shareIntent = new Intent(getApplicationContext(), ShareMenu.class);
                startActivity(shareIntent);
                break;

            case R.id.map:
                Toast.makeText(getBaseContext(), "Map", Toast.LENGTH_SHORT).show();
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
