package com.example.lakmal.coffer.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lakmal.coffer.Adapter.MySingleton;
import com.example.lakmal.coffer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    //private GoogleApiClient googleApiClient ;


    EditText email_text;
    EditText password_text;

    String email;
    String password;

    String reg_url = "http://166.62.62.149/gayan/coffer/auth/login";

    String error;

    AlertDialog.Builder alertDialog;

    //private GoogleApiClient googleApiClient ;
    // private static final int REQ_CODE = 9001 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //set identities to the buttons
        Button logIn = (Button) findViewById(R.id.logIn);
        Button googleLogin = (Button) findViewById(R.id.googleLogIn);
        Button signUp = (Button) findViewById(R.id.singUp);

        //set identities to the textedits
        email_text = (EditText) findViewById(R.id.email);
        password_text = (EditText) findViewById(R.id.password);

        alertDialog = new AlertDialog.Builder(LogIn.this);

        //GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        //googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();


        //set on click listener to the native log in
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();

            }
        });

        //set on click listener to the google sign in
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //google signup code here
                //Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                // startActivityForResult(intent , REQ_CODE);
            }
        });

        //set on click listener to the signup
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sign up code here
                //attemptLogin();
                startActivity(new Intent(LogIn.this, SignIn.class));
            }
        });

    }

    private void attemptLogin() {

        //set strings to the text
        email = email_text.getText().toString();
        password = password_text.getText().toString();


        // Reset errors
        email_text.setError(null);
        password_text.setError(null);


        boolean cancel = false;
        View focusView = null;

//        // Check for a valid email address.
//        if (TextUtils.isEmpty(email)) {
//            email_text.setError(getString(R.string.error_field_required));
//            focusView = email_text;
//            cancel = true;
//       }
//// else if (!isEmailValid(email)) {
////            email_text.setError(getString(R.string.error_invalid_email));
////            focusView = email_text;
////            cancel = true;
////        }
//
//        //check for a valid password
//        if (TextUtils.isEmpty(password)) {
//            password_text.setError(getString(R.string.error_field_required));
//            focusView = password_text;
//            cancel = true;
//        } else if (!isPasswordValid(password)) {
//            password_text.setError(getString(R.string.error_short_password));
//            focusView = password_text;
//            cancel = true;
//        }


        //view setting
        if (cancel)
            focusView.requestFocus();
        else {

//            User user = new User( email , password);
//            String jsonBody = new Gson().toJson(user);
//            JSONObject jsonbody = null;

            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("pass", "coffer1");
                jsonBody.put("email", "coffer.extrogene@gmsil.com");
                jsonBody.put("key", "200e132f-1bf6-4c0a-b1c8");
            } catch (JSONException e) {
                e.printStackTrace();
            }




            JsonObjectRequest req = new JsonObjectRequest(

                    Request.Method.POST,

                    reg_url,

                    jsonBody,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("janitha", "response : " + response);

                            try {
                                error = response.getString("error");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (error == "true") {
                                alertDialog.setTitle("Error LogIn!!!");
                                alertDialog.setMessage("invalid try to log in please try again...");
                                displayAlert("login error");
                            } else {
                                startActivity(new Intent(LogIn.this, ListActivity.class));
                            }

                        }
                    },

                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            Log.d("janitha", error.toString());
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
                                    Log.d("janitha", e1.toString());
                                } catch (JSONException e2) {
                                    // returned data is not JSONObject?
                                    e2.printStackTrace();
                                    Log.d("janitha", e2.toString());
                                }
                            }
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "5eb5633509f9c3f3d1e54804512683ab");
                    return headers;
                }
            };
            MySingleton.getInstance().addToRequestQueue(req);

        }

    }

    private boolean isPasswordValid(String password) {
        return (password.length() > 4);
    }


    private boolean isEmailValid(String email) {

        return email.contains("@");

    }

    public void displayAlert(final String code) {

        alertDialog.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (code.equals("login error")) {
                            email_text.setText("");
                            password_text.setText("");

                        }
                    }
                }
        );

        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();


    }


//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==REQ_CODE)
//        {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleResult(result);
//
//
//        }
//    }
//
//    private void handleResult(GoogleSignInResult result) {
//        if (result.isSuccess()){
//            GoogleSignInAccount account = result.getSignInAccount();
//            String name     = account.getDisplayName();
//            String email    = account.getEmail();
//            String token    = account.getIdToken();
//            String id       = account.getId();
//            String photoURL = account.getPhotoUrl().toString();
//
//            Log.i("janitha" , name);
//            Log.i("janitha" , email);
//            Log.i("janitha" , token);
//            Log.i("janitha" , id);
//            Log.i ("janitha", photoURL);
//
//            //name1.setText(name);
//            //email1.setText(email);
//        }
//    }


}
