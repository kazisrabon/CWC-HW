package com.example.bs_36.cwc1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bs_36.cwc1.library.UserFunctions;
import com.example.bs_36.cwc1.library.DatabaseHandler;

import org.json.JSONObject;

/**
 * Created by BS-36 on 1/20/2015.
 */
public class PasswordReset extends Activity {
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";

    EditText email;
    TextView alert;
    Button resetpass;
    String forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordreset);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Button login = (Button) findViewById(R.id.bktolog);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });

        email = (EditText) findViewById(R.id.forpas);
        alert = (TextView) findViewById(R.id.alert);
        resetpass = (Button) findViewById(R.id.respass);
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new ProcessRegister().execute();
                forgotpassword = email.getText().toString();
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.forPass(forgotpassword);
                Toast.makeText(getApplicationContext(), "A recovery email is sent to you, see it for more details.", Toast.LENGTH_SHORT).show();
//                alert.setText("A recovery email is sent to you, see it for more details.");
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

