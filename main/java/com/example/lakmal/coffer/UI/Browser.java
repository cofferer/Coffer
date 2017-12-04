package com.example.lakmal.coffer.UI;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lakmal.coffer.Adapter.SearchAdapter;
import com.example.lakmal.coffer.Model.SearchData;
import com.example.lakmal.coffer.R;

public class Browser extends AppCompatActivity {

    private RecyclerView recView;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse);

        recView = (RecyclerView) findViewById(R.id.rec_list2);

        //adding the layout manager , gridlayoutmanager , staggred layoutmanager
        recView.setLayoutManager(new LinearLayoutManager(this));

        //passing data from static method getliistdata to the adapter
        adapter = new SearchAdapter(SearchData.getListData(), this);

        //pass the adapter data
        recView.setAdapter(adapter);

    }
}
