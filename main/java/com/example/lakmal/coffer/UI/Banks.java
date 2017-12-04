package com.example.lakmal.coffer.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lakmal.coffer.Adapter.MySingleton;
import com.example.lakmal.coffer.Model.BankItem;
import com.example.lakmal.coffer.Model.DataBaseHandler;
import com.example.lakmal.coffer.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banks extends AppCompatActivity {
    private ImageAdapter imageAdapter;
    List<BankItem> bankItems = new ArrayList<>();
    ProgressDialog progressDialog;

    private Button doneBtn;
    //final DataBaseHandler dataBaseHandler = new DataBaseHandler(Banks.this);

    SparseBooleanArray selectedItems = new SparseBooleanArray();
    AlertDialog.Builder alertDialog;
    ProgressBar progressBar;

    List<Integer> selectedIndex = new ArrayList<>();
    Map<String,ArrayList<String>> selectedBanksAndAllCards = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);

        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progress_layout);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);

        doneBtn = (Button) findViewById(R.id.done_btn_pref);

        GridView imagegrid = (GridView) findViewById(R.id.PhoneImageGrid);


        alertDialog = new AlertDialog.Builder(Banks.this);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        imagegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CheckBox checkBox = ((ViewHolder) view.getTag()).checkbox;
                checkBox.setChecked(!checkBox.isChecked());
                if (checkBox.isChecked()) {

                    selectedItems.put(i, true);

                } else {
                    selectedItems.delete(i);
                }
            }
        });



        imageAdapter = new ImageAdapter();
        getBankData();
        imagegrid.setAdapter(imageAdapter);



        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("janitha_passing data", getSelectedIndexes() + "");


                if (getSelectedIndexes().isEmpty()) {
                    Log.d("janitha", "list is empty");
                    alertDialog.setTitle("Select A Bank!");
                    alertDialog.setMessage("please select at least one bank!!!");
                    displayAlert("login error");
                } else {

                    passData();
                }

            }
        });
    }


    public ArrayList<String> getSelectedIndexes() {

        ArrayList<String> items = new ArrayList<String>();

        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(String.valueOf(selectedIndex.get(selectedItems.keyAt(i))));
            Log.d("janitha_ids", selectedIndex.get(selectedItems.keyAt(i)) + " id number " + i + " " + selectedItems.keyAt(i));
        }
        return items;
    }

    public void displayAlert(final String code) {

        alertDialog.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (code.equals("login error")) {


                        }
                    }
                }
        );

        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();


    }

    public void passData() {

        JSONObject jsonBody = new JSONObject();
        JSONArray jsArray = new JSONArray(getSelectedIndexes());
        try {

            jsonBody.put("banks", jsArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String pass_bank_data_url = "http://166.62.62.149/gayan/coffer/api/v1/banks/cards";
        JsonObjectRequest req = new JsonObjectRequest(

                Request.Method.POST,

                pass_bank_data_url,

                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("janitha", "response : " + response);

                        try {
                            if(!response.getBoolean("error")){
                                JSONArray cardList = response.getJSONArray("card_list");

                                for(int i = 0 ; i<cardList.length();i++){
                                    JSONObject allcardData = (JSONObject) cardList.get(i);
                                    Log.d("janitha",allcardData.getString("bank_code")+" "+i+"th time");

                                    JSONArray cardsInOneBank = (JSONArray) allcardData.getJSONArray("cards");
                                    Log.d("janitha",cardsInOneBank+" ");
                                    ArrayList<String> allCards = new ArrayList<>();

                                    for(int j = 0 ;j<cardsInOneBank.length();j++){
                                        Log.d("janitha",cardsInOneBank.getJSONObject(j).get("card_id")+" "+j);
                                        allCards.add((String) cardsInOneBank.getJSONObject(j).get("name"));
                                    }
                                    selectedBanksAndAllCards.put(allcardData.getString("bank_code"),allCards);
                                }
                                Log.d("janitha",selectedBanksAndAllCards+"");
                                Log.d("janitha@bank",selectedBanksAndAllCards.keySet()+"");
                                Intent intent = new Intent(Banks.this, Cards.class);
                                intent.putExtra("selectedBanksAndAllCards", (Serializable) selectedBanksAndAllCards);
                                startActivityForResult(intent, 500);




                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                headers.put("Authorization", "4a1b00e8-b125-4bd8-8e15-6e1202a3eff9");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MySingleton.getInstance().addToRequestQueue(req);
    }

    public void getBankData() {



        final JSONObject[] jsonBody = {new JSONObject()};
        String pass_bank_data_url = "http://166.62.62.149/gayan/coffer/api/v1/banks";

        JsonObjectRequest req = new JsonObjectRequest(

                Request.Method.GET,

                pass_bank_data_url,

                null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (!response.getBoolean("error")) {

                                JSONArray banks = response.getJSONArray("banks");


                                for (int i = 0; i < banks.length(); i++) {

                                    JSONObject bankObj = banks.getJSONObject(i);
                                    BankItem bankItem = new BankItem();
                                    selectedIndex.add(Integer.valueOf(bankObj.getString("bank_code")));

                                    bankItem.setBankCode(bankObj.getString("bank_code"));
                                    bankItem.setBankName(bankObj.getString("name"));
                                    bankItem.setContact(bankObj.getString("contact"));
                                    bankItem.setWebSite(bankObj.getString("website"));
                                    bankItem.setLogoURL(bankObj.getString("logo_url"));


                                    //add bank to the db
                                    DataBaseHandler dataBaseHandler = new DataBaseHandler(getApplicationContext()) ;
                                    dataBaseHandler.addBank(bankItem);
                                    Log.d("janitha@db",dataBaseHandler.addBank(bankItem)+"");
                                    bankItems.add(bankItem);
                                }
                                imageAdapter.notifyDataSetChanged();
                            } else {
                                // Log.d("janitha", "onna pannooo");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                        jsonBody[0] = response;
                        progressDialog.dismiss();
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


    public class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;


        public ImageAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return bankItems.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }


        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(
                        R.layout.bank, null);
                holder.imageview = convertView.findViewById(R.id.thumbImage);
                holder.checkbox = convertView.findViewById(R.id.itemCheckBox);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.checkbox.setId(Integer.parseInt(bankItems.get(position).getBankCode()));
            holder.imageview.setId(Integer.parseInt(bankItems.get(position).getBankCode()));

            Uri uri = Uri.parse(bankItems.get(position).getLogoURL());
            Picasso.with(getApplicationContext())
                    .load(uri)
                    .fit()
                    .placeholder(R.drawable.sample_0)
                    .error(R.drawable.error)
                    .into(holder.imageview);


            holder.id = Integer.parseInt(bankItems.get(position).getBankCode());
            return convertView;
        }


    }

    class ViewHolder {
        ImageView imageview;
        CheckBox checkbox;
        int id;

    }
}
