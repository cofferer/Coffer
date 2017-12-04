package com.example.lakmal.coffer.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lakmal.coffer.Adapter.SearchAdapter;
import com.example.lakmal.coffer.Model.SearchData;
import com.example.lakmal.coffer.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Browse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner bank_spinner;
    Spinner category_spinner;
    Spinner other_spinner ;
    Button search;
    TextView bank;
    RelativeLayout view1;
    SearchView searchView;
    RecyclerView searchResults;


    String searchQuery;

    private RecyclerView recView;
    private SearchAdapter adapter;


    private ArrayList listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);


        view1 = (RelativeLayout) findViewById(R.id.view1);
        searchResults = (RecyclerView) findViewById(R.id.rec_list_browse);

        //set IDs for spinners
        bank_spinner = (Spinner) findViewById(R.id.bank_spinner);
        category_spinner = (Spinner) findViewById(R.id.card_spinner);
        other_spinner  = (Spinner) findViewById(R.id.other_spinner);

        searchView = (SearchView) findViewById(R.id.search);

        //default hint for the sarch view
        searchView.setQueryHint("search for something");

        //hide the sear5ch results view initially
        searchResults.setVisibility(GONE);

        search = (Button) findViewById(R.id.searchbtn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Browse.this, Browser.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //for now visibilty set visible when the search button clicks
                searchResults.setVisibility(VISIBLE);
                searchView.clearFocus();
            }
        });

        listData = (ArrayList) SearchData.getListData();

        recView = (RecyclerView) findViewById(R.id.rec_list_browse);

        //adding the layout manager , gridlayoutmanager , staggred layoutmanager
        recView.setLayoutManager(new LinearLayoutManager(this));

        //passing data from static method getliistdata to the adapter
        adapter = new SearchAdapter(SearchData.getListData(), this);

        //pass the adapter data
        recView.setAdapter(adapter);

        // Spinner click listener
        bank_spinner.setOnItemSelectedListener(this);
        category_spinner.setOnItemSelectedListener(this);
        other_spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("nothing selected");
        categories.add("BOC");
        categories.add("NSB");
        categories.add("NDB");
        categories.add("NTB");
        categories.add("HSBC");
        categories.add("HDFC");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        bank_spinner.setAdapter(dataAdapter);
        category_spinner.setAdapter(dataAdapter);
        other_spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
// On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        Log.d("janitha", parent.getItemAtPosition(position).toString());
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        switch (parent.getId()) {
            case R.id.bank_spinner:
                Log.d("janitha", "bsnks clicked");
                break;

            case R.id.card_spinner:
                Log.d("janitha", "cards clicked");
                break;

            case R.id.other_spinner:
                Log.d("janitha", "others clicked");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
