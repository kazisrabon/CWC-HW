package com.example.bs_36.cwc1;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShareMenu extends Activity {
    SocialAuthAdapter adapter;

    // Android Components
    Button update;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_layout);

        // Welcome Message
        TextView textview = (TextView) findViewById(R.id.text);
        textview.setText("Share");

        adapter = new SocialAuthAdapter(new ResponseListener());
        adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
        adapter.addProvider(Provider.TWITTER, R.drawable.twitter);
        adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);
        adapter.addProvider(Provider.GOOGLE, R.drawable.google);
        adapter.addProvider(Provider.GOOGLEPLUS, R.drawable.googleplus);
        adapter.addProvider(Provider.MYSPACE, R.drawable.myspace);
        adapter.addProvider(Provider.RUNKEEPER, R.drawable.runkeeper);
        adapter.addProvider(Provider.YAHOO, R.drawable.yahoo);
        adapter.addProvider(Provider.YAMMER, R.drawable.yammer);
        adapter.addProvider(Provider.FOURSQUARE, R.drawable.foursquare);
        adapter.addProvider(Provider.FLICKR, R.drawable.flickr);
        adapter.addProvider(Provider.INSTAGRAM, R.drawable.instagram);

        // For twitter use add callback method. Put your own callback url here.
        adapter.addCallBack(Provider.TWITTER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");
        adapter.addCallBack(Provider.YAMMER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share_menu, menu);
        final MenuItem item = menu.findItem(R.id.share_action);
        View actionView = item.getActionView().findViewById(R.id.imgbtnShare);
        adapter.enable(actionView);
        return true;
    }

    /**
     * Listens Response from Library
     *
     */

    private final class ResponseListener implements DialogListener {
        @Override
        public void onComplete(Bundle values) {
            // Variable to receive message status
            Log.d("Share-Menu", "Authentication Successful");

            // Get name of provider after authentication
            final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
            Log.d("Share-Bar", "Provider Name = " + providerName);
            Toast.makeText(ShareMenu.this, providerName + " connected", Toast.LENGTH_SHORT).show();

            update = (Button) findViewById(R.id.update);
            edit = (EditText) findViewById(R.id.editTxt);

            // Please avoid sending duplicate message. Social Media Providers
            // block duplicate messages.

            update.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Call updateStatus to share message via oAuth providers
                    adapter.updateStatus(edit.getText().toString(), new MessageListener(), false);
                }
            });
        }

        @Override
        public void onError(SocialAuthError error) {
            error.printStackTrace();
            Log.d("Share-Menu", error.getMessage());
        }

        @Override
        public void onCancel() {
            Log.d("Share-Menu", "Authentication Cancelled");
        }

        @Override
        public void onBack() {
            Log.d("Share-Menu", "Dialog Closed by pressing Back Key");

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
                Toast.makeText(ShareMenu.this, "Message not posted on" + provider, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }
}
