package com.example.bs_36.cwc1;

import org.brickred.socialauth.Photo;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class ShareMenu extends ActionBarActivity {

    // SocialAuth Component
    SocialAuthAdapter adapter;
    Profile profileMap;
    List<Photo> photosList;

    // Android Components
    Button update;
    EditText edit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.share_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Welcome Message
        TextView textview = (TextView) findViewById(R.id.text);
        textview.setText("Welcome to Code Warrior Challenge Project. Her You can share to any social network!!!");

        // Create Your Own Share Button
        Button share = (Button) findViewById(R.id.sharebutton);
        share.setText("Share");
        share.setTextColor(Color.WHITE);
        share.setBackgroundResource(R.drawable.button_gradient);

        // Add it to Library
        adapter = new SocialAuthAdapter(new ResponseListener());

        // Add providers
        adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
        adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
        adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);
        adapter.addProvider(Provider.YAHOO, R.drawable.yahoo);
        adapter.addProvider(Provider.YAMMER, R.drawable.yammer);
        adapter.addProvider(Provider.EMAIL, R.drawable.email);
        adapter.addProvider(Provider.MMS, R.drawable.mms);

        // Providers require setting user call Back url
        adapter.addCallBack(Provider.TWITTER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");
        adapter.addCallBack(Provider.YAMMER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");

        // Enable Provider
        adapter.enable(share);

    }

    /**
     * Listens Response from Library
     *
     */

    private final class ResponseListener implements DialogListener {
        @Override
        public void onComplete(Bundle values) {

            Log.d("ShareButton", "Authentication Successful");

            // Get name of provider after authentication
            final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
            Log.d("ShareButton", "Provider Name = " + providerName);
            Toast.makeText(ShareMenu.this, providerName + " connected", Toast.LENGTH_LONG).show();

            update = (Button) findViewById(R.id.update);
            edit = (EditText) findViewById(R.id.editTxt);

            // Please avoid sending duplicate message. Social Media Providers
            // block duplicate messages.

            update.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    adapter.updateStatus(edit.getText().toString(), new MessageListener(), false);
                }
            });

            // Share via Email Intent
            if (providerName.equalsIgnoreCase("share_mail")) {
                // Use your own code here
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                        "kaziiit@gmail.com", null));
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Test");
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                        "image5964402.png");
                Uri uri = Uri.fromFile(file);
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(emailIntent, "Test"));
            }

            // Share via mms intent
            if (providerName.equalsIgnoreCase("share_mms")) {

                // Use your own code here
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                        "image5964402.png");
                Uri uri = Uri.fromFile(file);

                Intent mmsIntent = new Intent(Intent.ACTION_SEND, uri);
                mmsIntent.putExtra("sms_body", "Test");
                mmsIntent.putExtra(Intent.EXTRA_STREAM, uri);
                mmsIntent.setType("image/png");
                startActivity(mmsIntent);
            }

        }

        @Override
        public void onError(SocialAuthError error) {
            Log.d("ShareButton", "Authentication Error: " + error.getMessage());
        }

        @Override
        public void onCancel() {
            Log.d("ShareButton", "Authentication Cancelled");
        }

        @Override
        public void onBack() {
            Log.d("Share-Button", "Dialog Closed by pressing Back Key");
        }

    }

    // To get status of message after authentication
    private final class MessageListener implements SocialAuthListener<Integer> {
        @Override
        public void onExecute(String provider, Integer t) {
            Integer status = t;
            if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
                Toast.makeText(ShareMenu.this, "Message posted on " + provider, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ShareMenu.this, "Message not posted on " + provider, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
