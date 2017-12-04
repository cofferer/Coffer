package com.example.lakmal.coffer.UI;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.lakmal.coffer.Adapter.ExpListViewAdapterWithCheckbox;
import com.example.lakmal.coffer.Model.DataBaseHandler;
import com.example.lakmal.coffer.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cards extends Activity {

    //  ExpandableListAdapter listAdapter;
    ExpListViewAdapterWithCheckbox listAdapter;
    ExpandableListView expListView;
    ArrayList<String> listDataHeader = new ArrayList<>();
    HashMap<String, List<String>> listDataChild = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);


        Intent intent = getIntent();
        HashMap<String, ArrayList<String>> selectedBanksAndAllCards = (HashMap<String, ArrayList<String>>) intent.getSerializableExtra("selectedBanksAndAllCards");


        Log.d("janitha@card",selectedBanksAndAllCards+"");

        for (Map.Entry<String, ArrayList<String>> entry : selectedBanksAndAllCards.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            Log.d("janitha@card2_bank",key+"");
            Log.d("janitha@card2_cards",value+"");
            listDataHeader.add(key);
            listDataChild.put(key,value);
        }

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        //prepareListData();

        listAdapter = new ExpListViewAdapterWithCheckbox(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return true;
            }
        });
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int count =0;
                for(int mGroupPosition =0; mGroupPosition < listAdapter.getGroupCount(); mGroupPosition++)
                {

                    if(!listAdapter.getSelectedIndexesInGroup(mGroupPosition).isEmpty()){
                        Log.d("janitha@bla bla bla", listDataHeader.get(mGroupPosition));

                    }
                    count = count +  listAdapter.getNumberOfCheckedItemsInGroup(mGroupPosition);
                    Log.d("janitha_selected cards",mGroupPosition+" "+listAdapter.getSelectedIndexesInGroup(mGroupPosition)+"");
                    Log.d("janitha_selected cards",mGroupPosition+" "+listAdapter.getNumberOfCheckedItemsInGroup(mGroupPosition)+"");
                }
                if(count==0){
                    //if count is zero . block sending to the next intent
                    Log.d("janitha","හිස් එව්ව යවන්න බැහැ ඕයි  ");
                    DataBaseHandler dataBaseHandler = new DataBaseHandler(getApplicationContext());
                    Log.d("janitha","ඔන්න බලාගනින් ඩේට බේස් "+dataBaseHandler.getBankNames(listDataHeader));
                }
                else{
                    startActivity(new Intent(Cards.this, SignIn.class));
                }


            }
        });
    }
}