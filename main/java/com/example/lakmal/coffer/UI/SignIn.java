package com.example.lakmal.coffer.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lakmal.coffer.Adapter.MySingleton;
import com.example.lakmal.coffer.Model.UserItem;
import com.example.lakmal.coffer.Model.DataBaseHandler;
import com.example.lakmal.coffer.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {


    //set the server url
    String reg_url = "http://166.62.62.149/gayan/coffer/auth/signup";

    String username;
    String email;
    String password;
    String re_password;

    EditText username_text;
    EditText email_text;
    EditText password_text;
    EditText re_password_text;

    boolean isgooglepressed;

    AlertDialog.Builder alertDialog;

    String error;

    Context mContext;

    //private GoogleApiClient googleApiClient ;
    // private static final int REQ_CODE2 = 9001 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //set identities to the buttons
        // SignInButton googleLogin = (SignInButton) findViewById(R.id.googleLogIn);
        Button signUp = (Button) findViewById(R.id.signUp);
        Button tour = (Button) findViewById(R.id.tour);

        // GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        // googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();


        //set identities to the textedits
        username_text = (EditText) findViewById(R.id.username);
        email_text = (EditText) findViewById(R.id.email);
        password_text = (EditText) findViewById(R.id.password);
        re_password_text = (EditText) findViewById(R.id.re_password);


        alertDialog = new AlertDialog.Builder(SignIn.this);

        String error;

        //set on click listener to the sign up button
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();

            }
        });

        //set on click listener to the tour button
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goingTour();
            }
        });

        //set on click listener to the google sign in
//        googleLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//                startActivityForResult(intent , REQ_CODE2);
//
//            }
//        });


    }

    private void attemptSignUp() {
        //set strings to the text
        email = email_text.getText().toString();
        password = password_text.getText().toString();
        re_password = re_password_text.getText().toString();
        username = username_text.getText().toString();


        // Reset errors
        email_text.setError(null);
        password_text.setError(null);


        boolean cancel = false;
        View focusView = null;

//        //check for a valid username
//        if (TextUtils.isEmpty(username)) {
//            username_text.setError(getString(R.string.error_field_required));
//            focusView = email_text;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(email)) {
//            email_text.setError(getString(R.string.error_field_required));
//            focusView = email_text;
//            cancel = true;
//        } else if (!isEmailValid(email)) {
//            email_text.setError(getString(R.string.error_invalid_email));
//            focusView = email_text;
//            cancel = true;
//        } else if (!isGMail(email)) {
//            email_text.setError(getString(R.string.error_not_gmail));
//            focusView = email_text;
//            cancel = true;
//        }
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
//        } else if (!matchPassword(password, re_password)) {
//
//            password_text.setError("passwords are not matching");
//            focusView = password_text;
//            cancel = true;
//        }


        //view setting
        if (cancel)
            focusView.requestFocus();
        else {


            JSONObject jsonBody = new JSONObject();

            try {
                jsonBody.put("name", "coffereqdeeewrefeaawtrr");
                jsonBody.put("pass", "coffer2edwtwrefeaawtrr");
                jsonBody.put("email", "coffer.extdrogesned1w0100efreaatr@gmail.com");
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
                            String SERVER_U_NAME;
                            String SERVER_U_ID;
                            try {
                                Log.d("janitha", "response : " + response);
                                error = response.getString("error");

                                if (error == "true") {
                                    alertDialog.setTitle("Error SignUp!!!");
                                    alertDialog.setMessage("invalid try to log in...");
                                    displayAlert("login error");
                                    Log.d("janitha", "response : " + response);
                                } else {

                                    //save user from the json object
                                    JSONObject json2 = response.getJSONObject("user");
                                    SERVER_U_ID = json2.getString("user_id");
                                    SERVER_U_NAME =  json2.getString("email");

                                    //server data to the shared preference to
                                    serverDataToSharedPref(SERVER_U_NAME,SERVER_U_ID);

                                    //create an object of the user
                                    UserItem userItem = new UserItem();
                                    userItem.setUserName(SERVER_U_NAME);
                                    userItem.setUserID(SERVER_U_ID);

                                    //create an instance of the db class
                                    DataBaseHandler dataBaseHandler = new DataBaseHandler(SignIn.this);

                                    //add user to the local db
                                    dataBaseHandler.addUser(userItem);

                                    for (int xy = 0 ; xy<dataBaseHandler.getAllUsers().size();xy++){
                                        Log.d("janitha_db_res",dataBaseHandler.getAllUsers().get(xy).getUserName()+"");
                                        Log.d("janitha_db_res",dataBaseHandler.getAllUsers().get(xy).getUserID()+"");
                                    }

                                    //start the activity
                                    startActivity(new Intent(SignIn.this, ListActivity.class));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("janitha", "error response : " + error);
                            Log.d("janitha", "error response : " + error.getMessage());
                        }
                    }
            ) {   /*
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();

                    //headers.put("Authorization", "5eb5633509f9c3f3d1e54804512683ab");
                    return headers;
                }*/
            };
            MySingleton.getInstance().addToRequestQueue(req);
        }

    }


    public void goingTour() {
        //getting the username
        String passing_username_tour = username_text.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", passing_username_tour);
        editor.commit();

        startActivity(new Intent(SignIn.this, ListActivity.class));

    }

    private boolean isPasswordValid(String password) {

        return (password.length() > 5);
    }


    private boolean isGMail(String email) {

        return email.contains("@gmail.com");

    }

    private boolean isEmailValid(String email) {

        return email.contains("@");

    }

    private boolean matchPassword(String Password, String Re_Password) {
        if (!Password.equals(Re_Password)) {

            password_text.setText("");
            re_password_text.setText("");
            return false;
        } else
            return true;
    }


    public void displayAlert(final String code) {

        alertDialog.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (code.equals("login error")) {
                            username_text.setText("");
                            email_text.setText("");
                            password_text.setText("");
                            re_password_text.setText("");
                        }
                    }
                }
        );

        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();


    }

    public void serverDataToSharedPref(String username , String userId){
        SharedPreferences sharedPref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("server_username", username);
        editor.putString("server_userId", userId);
        editor.commit();

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
//        if(requestCode==REQ_CODE2)
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
//            String name  = account.getDisplayName();
//            String email = account.getEmail();
//            String token = account.getIdToken();
//            String id    = account.getId();
//
//            Log.i("janitha" , name);
//            Log.i("janitha" , email);
//            Log.i("janitha" , token);
//            Log.i("janitha" , id);
//
//        }
//        else
//            Log.i("janitha" , "login error");
//    }
//
//    public void foo(View view){
//
//        if (view.getId() == R.id.googleLogIn){
//            isgooglepressed = true ;
//        }
//
//        else if (view.getId() == R.id.signUp){
//            isgooglepressed = false ;
//        }
//
//    }


}

