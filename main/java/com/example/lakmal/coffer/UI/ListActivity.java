package com.example.lakmal.coffer.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lakmal.coffer.Adapter.DerpAdapter;
import com.example.lakmal.coffer.Adapter.MySingleton;
import com.example.lakmal.coffer.Model.DerpData;
import com.example.lakmal.coffer.Model.ListItem;
import com.example.lakmal.coffer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DerpAdapter.ItemClickCallback {


    private RecyclerView recView;
    private DerpAdapter adapter;


    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private static final String EXTRA_IMAGE = "EXTRA_IMAGE";
    private static final String EXTRA_OFF_PERCENT = "EXTRA_OFF_PERCENT";
    private static final String EXTRA_DETAIL = "EXTRA_DETAIL";


    private ArrayList listData;

    AlertDialog.Builder alertDialog;

    int width;
    int height;

    CountDownTimer c;
    String offer_url = "enter the offer URL here";

    List<ListItem> offers = new ArrayList<>();

    @Override
    protected void onDestroy() {
        c.cancel();
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        listData = (ArrayList) DerpData.getListData();


        recView = (RecyclerView) findViewById(R.id.rec_list);

        //adding the layout manager , gridlayoutmanager , staggred layoutmanager
        recView.setLayoutManager(new LinearLayoutManager(this));

        //passing data from static method getliistdata to the adapter
        adapter = new DerpAdapter(DerpData.getListData(), this);

        //pass the adapter data
        recView.setAdapter(adapter);

        adapter.setItemClickCallback((DerpAdapter.ItemClickCallback) this);

        //get the user name from the signin pasge if there isno user name it implies thart the user has taken a tour
        SharedPreferences sharedPref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);

        String name = sharedPref.getString("username", "");

        Log.d("janitha", "trying" + name.length());

        //alert dialog for the tour end
        alertDialog = new AlertDialog.Builder(ListActivity.this);

        if (name.length() == 0) {

            c = new CountDownTimer(6000, 1000) {

                public void onTick(long millisUntilFinished) {


                }

                public void onFinish() {
                    alertDialog.setTitle("Make You Own Feed");
                    alertDialog.setMessage("Make most out of Coffer, customize Offer fed by adding you preffered banks and card. Yes you can do it,and it is very easy");
                    displayAlert("tour meaasge");
                }
            };
            c.start();
        }

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(ListActivity.this, Browse.class));

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int p) {

        //link to the new activity

        ListItem item = (ListItem) listData.get(p);

        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();

        extras.putString(EXTRA_QUOTE, item.getTitle());
        extras.putString(EXTRA_ATTR, item.getSubTitle());
        extras.putInt(EXTRA_IMAGE, item.getImageResId());
        extras.putString(EXTRA_DETAIL, item.getDetail());
        extras.putString(EXTRA_OFF_PERCENT, item.getOff_percent());

        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(i);
    }

    @Override
    public void onSecondaryIconClick(int p) {
        ListItem item = (ListItem) listData.get(p);
        //update our data
        if (item.isFavourite()) {
            item.setFavourite(false);
        } else {
            item.setFavourite(true);
        }
        //pass new data to adapter and update
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }

    public void displayAlert(final String code) {


        alertDialog.setPositiveButton(
                "NOT NOW",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (code.equals("tour message")) {


                        }
                    }
                }
        );

        alertDialog.setNegativeButton(
                "LET'S DO IT",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(ListActivity.this, SignIn.class));

                    }
                }
        );

        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        lp.copyFrom(alertDialog1.getWindow().getAttributes());
        lp.width = (width * 4) / 5;
        lp.height = (height * 33) / 100;
        lp.x = 0;
        lp.y = 0;
        alertDialog1.getWindow().setAttributes(lp);
    }

    public void getCardData(){



        final JSONObject[] jsonBody = {new JSONObject()};
        String offer_data_url = "enter the URL hers";

        JsonObjectRequest req = new JsonObjectRequest(

                Request.Method.GET,

                offer_data_url,

                null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (!response.getBoolean("error")) {

                                JSONArray banks = response.getJSONArray("offers");


                                for (int i = 0; i < banks.length(); i++) {

                                    JSONObject bankObj = banks.getJSONObject(i);
                                    ListItem offer = new ListItem();

//                                    offer.setDetail();
//                                    offer.setImageResId();

                                    offers.add(offer);
                                }

                            } else {
                                // Log.d("janitha", "onna pannooo");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                        jsonBody[0] = response;

                    }
                },

                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        // Log.d("janitha", error.toString());
                        // As of f605da3 the following should work
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                                //  Log.d("janitha", e1.toString());
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                                //  Log.d("janitha", e2.toString());
                            }

                        }
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "4a1b00e8-b125-4bd8-8e15-6e1202a3eff9");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance().addToRequestQueue(req);




    }
}


