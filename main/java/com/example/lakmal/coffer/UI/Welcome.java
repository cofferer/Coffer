package com.example.lakmal.coffer.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.lakmal.coffer.Adapter.MySingleton;
import com.example.lakmal.coffer.Model.DataBaseHandler;
import com.example.lakmal.coffer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Welcome extends AppCompatActivity {

    Button button;

    int APP_LEVEL;

    int SERVER_APP_LEVEL;

    String update_url = "enter the app level checking URL here";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(
                new Button.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Welcome.this, Banks.class));

                    }
                }
        );
    }

    public void updateDb() {


        JSONArray jsonBody = new JSONArray();

        try {
            jsonBody.put(Integer.parseInt("version"), "coffer_version");

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        JsonObjectRequest req = new JsonObjectRequest(
//
//                Request.Method.POST,
//
//                update_url,
//
//                jsonBody,
//
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        try {
//                            String server_version = response.getString("version");
//
//                            SERVER_APP_LEVEL = Integer.parseInt(server_version);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//                ,
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("janitha", "error response : " + error);
//                        Log.d("janitha", "error response : " + error.getMessage());
//                    }
//                }
//        );

        JsonArrayRequest req = new JsonArrayRequest(

                Request.Method.POST,

                update_url,

                jsonBody,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jo = response.getJSONObject(i);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }

                }

                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("janitha", "error response : " + error);
                        Log.d("janitha", "error response : " + error.getMessage());
                    }
                }
        );


        MySingleton.getInstance().addToRequestQueue(req);

        if (APP_LEVEL < SERVER_APP_LEVEL) {

            //update the current app level
            APP_LEVEL = SERVER_APP_LEVEL;

            //database update is to be done here

            DataBaseHandler dataBaseHandler = new DataBaseHandler(Welcome.this);

            dataBaseHandler.restCategory();


        }
    }


}
