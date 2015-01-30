package com.example.bs_36.cwc1;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.example.bs_36.cwc1.app.AppController;
import com.android.volley.toolbox.JsonArrayRequest;

/**
 * Created by BS-36 on 1/28/2015.
 */
public class products_Fragment extends Fragment {
    private FragmentActivity dashboardActivity;
    private LinearLayout products;
    private List<Products> myProducts = new ArrayList<Products>();
    String url = "http://www.mocky.io/v2/54ca3487fb2205170c9eff13"; //http://cbllocator.thecitybank.com:8082/index.php/home/newBranchData
    InputStream is = null;
    String json = "";
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private static final String TAG = DashboardActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardActivity  = (FragmentActivity)    super.getActivity();
        products = (LinearLayout) inflater.inflate(R.layout.fragment_products, container, false);
        StrictMode.enableDefaults();
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        popupalteView();
        return products;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    private class ParseAsyncTask extends AsyncTask<String, Void, Boolean> {

        public ParseAsyncTask(FragmentActivity activity, LinearLayout products) {

        }

        @Override
        protected Boolean doInBackground(String... arg0) {
//
//            JsonArrayRequest movieReq = new JsonArrayRequest(url,
//                    new Response.Listener<JSONArray>() {
//                        @Override
//                        public void onResponse(JSONArray response) {
//                            Log.d(TAG, response.toString());
//                            hidePDialog();
//
//                            // Parsing json
//                            for (int i = 0; i < response.length(); i++) {
//                                try {
//
//                                    JSONObject obj = response.getJSONObject(i);
//                                    Products prdct = new Products();
//                                    prdct.setImgUrl(obj.getString("image"));
//                                    prdct.setImgUrl(obj.getString("branch_name"));
//                                    prdct.setImgUrl(obj.getString("district"));
//                                    prdct.setImgUrl(obj.getString("latitude"));
//                                    prdct.setImgUrl(obj.getString("longitude"));
//                                    prdct.setImgUrl(obj.getString("address"));
//                                    prdct.setImgUrl(obj.getString("phone"));
//                                    prdct.setImgUrl(obj.getString("mobile"));
//                                    prdct.setImgUrl(obj.getString("email"));
//
//                                    // adding movie to movies array
//                                    myProducts.add(prdct);
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//
//                            // notifying list adapter about data changes
//                            // so that it renders the list view with updated data
////                            adapter.notifyDataSetChanged();
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    VolleyLog.d(TAG, "Error: " + error.getMessage());
//                    hidePDialog();
//
//                }
//            });
//
//            // Adding request to request queue
//            AppController.getInstance().addToRequestQueue(movieReq);

            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(url);
                HttpResponse response;
                response = httpclient.execute(httpget);

                HttpEntity entity = response.getEntity();
                is = entity.getContent();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"), 8);

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();

                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jArray = parser.parse(json).getAsJsonArray();

                for (JsonElement obj : jArray) {
                    Products parse = gson.fromJson(obj, Products.class);
                    Log.e("parse", jArray.toString());
                    myProducts.add(parse);
                }
                hidePDialog();

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            populateListView();
            clickCallBack();
        }

    }

    private void popupalteView() {

        new ParseAsyncTask(getActivity(),products).execute();

    }
    private void populateListView() {
        ArrayAdapter<Products> adapter = new MyListAdapter();
        ListView list = (ListView) products.findViewById(R.id.listViewProducts);
        list.setAdapter(adapter);

    }

    private void clickCallBack() {
        ListView list = (ListView) products.findViewById(R.id.listViewProducts);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                Products clickedBranch = myProducts.get(position);
                sendNotification(clickedBranch.getBranch_name());
                String message = "You clicked " + position + " which is "
                        + clickedBranch.getBranch_name();

//                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG)
//                        .show();
            }
        });
    }
    private class MyListAdapter extends ArrayAdapter<Products> {

        public MyListAdapter() {
            super(dashboardActivity, R.layout.item_view, myProducts);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            itemView = getActivity().getLayoutInflater().inflate(R.layout.item_view,
                    parent, false);

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();

            Products products1 = myProducts.get(position);

//            ImageView imageView = (ImageView) itemView
//                    .findViewById(R.id.imageView1);
//            imageView.;
            NetworkImageView thumbNail = (NetworkImageView) itemView.findViewById(R.id.imageView1);
            thumbNail.setImageUrl(products1.getImgUrl(), imageLoader);

            TextView textView1 = (TextView) itemView
                    .findViewById(R.id.textView1);
            textView1.setText("Branch Name: " + products1.getBranch_name());

            TextView textView2 = (TextView) itemView
                    .findViewById(R.id.textView2);
            textView2.setText("District: " + products1.getDistrict());

            TextView textView3 = (TextView) itemView
                    .findViewById(R.id.textView3);
            textView3.setText("Latitude: "+products1.getLatitude());

            TextView textView4 = (TextView) itemView
                    .findViewById(R.id.textView4);
            textView4.setText("Longitude: "+products1.getLongitude());

            TextView textView5 = (TextView) itemView
                    .findViewById(R.id.textView5);
            textView5.setText("Address: " + products1.getAddress());

            TextView textView6 = (TextView) itemView
                    .findViewById(R.id.textView6);
            textView6.setText("Phone: "+products1.getPhone());

            TextView textView7 = (TextView) itemView
                    .findViewById(R.id.textView7);
            textView7.setText("Mobile: "+products1.getMobile());

            TextView textView8 = (TextView) itemView
                    .findViewById(R.id.textView8);
            textView8.setText("Email: " + products1.getEmail());

            return itemView;
        }
    }
    private void sendNotification(String branch_name) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(dashboardActivity);
        builder.setAutoCancel(true);
        builder.setContentTitle("Products");
        builder.setContentText(branch_name);
        builder.setSmallIcon(R.drawable.ic_launcher);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) dashboardActivity.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(8, notification);
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hidePDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }
}
