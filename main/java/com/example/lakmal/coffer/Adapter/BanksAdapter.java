package com.example.lakmal.coffer.Adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.lakmal.coffer.Model.SelectedCards;
import com.example.lakmal.coffer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class BanksAdapter extends BaseAdapter {

    ArrayList<Integer> list;
    Context context;
    SparseBooleanArray selectedItems;
    public ArrayList<Integer> items2;
    //check whether the item is clicked beforehand
    boolean isClicked;

    public BanksAdapter(Context c) {

        context = c;
        list = new ArrayList<>();
        selectedItems = new SparseBooleanArray();

        int[] images = {
                R.drawable.sample_0,
                R.drawable.sample_1,
                R.drawable.sample_2,
                R.drawable.sample_3
        };

        for (int i = 0; i < images.length; i++) {
            list.add(images[i]);
        }
    }
    public interface OnCheckBoxClicked{
        void onCheckBoxValueClicked(int position);
    }
    OnCheckBoxClicked mListener;
    public void setOnCheckBoxListener(OnCheckBoxClicked listener){
        mListener=listener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override

    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //get the root view
        //use the root view to find the rest of the views
        //set the values

        //position of an item
        final int x = i;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.bank, viewGroup, false);

        ImageView imageView = row.findViewById(R.id.thumbImage);
        final CheckBox checkBox = row.findViewById(R.id.itemCheckBox);

        //retrieve the image object
        int temp = list.get(i);

        imageView.setImageResource(temp);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                isClicked = checkBox.isChecked();
                if (isClicked) {

                    checkBox.setChecked(false);
                    checkCheckBox(x, !selectedItems.get(x));

                } else {
                    checkBox.setChecked(true);
                    checkCheckBox(x, !selectedItems.get(x));
                }
            }



        });


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCheckBox(x, !selectedItems.get(x));
                getSharedPreference();
            }
        });

        return row;
    }


    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    public void checkCheckBox(int position, boolean value) {
        if (value) {
            //put replaces the item if there is an item
            selectedItems.put(position, true);
            Log.d("janitha", selectedItems.toString());
            getSharedPreference();
            getsponebyone(position);
        } else {

            selectedItems.delete(position);
            Log.d("janitha_delete", selectedItems.toString());
            // Log.d("janitha_delete", getSelectedIndexes() + "");
            getsponebyone(position);
            getSharedPreference();
            //getSharedPreference();


        }
        ArrayList<Integer> items = new ArrayList<Integer>();

        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        //notifyDataSetChanged();
    }


    public ArrayList<Integer> getSelectedIndexes() {

        ArrayList<Integer> items = new ArrayList<Integer>();

        JSONObject jsonobj = new JSONObject();

        SelectedCards selectedCards = new SelectedCards();

        Log.d("janitha_se", selectedItems.size() + "");

        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        selectedCards.setSelectedBankIds(items);
        try {
            jsonobj.put("a", selectedCards.getSelectedBankIds());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("janitha", items + "");
        Log.d("janitha", jsonobj + "");
        return items;
    }
//
    public void getSharedPreference() {
        ArrayList<Integer> items2 = new ArrayList<Integer>();
        for (int i = 0; i < selectedItems.size(); i++) {
            items2.add(selectedItems.keyAt(i));
        }
        Log.d("janitha@adapter", items2 + "");
        SharedPreferences prefs = context.getSharedPreferences("SHARED_PREFS_FILE2", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString("TASKS2", ObjectSerializer.serialize(items2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    public void getsponebyone(int position) {
        SharedPreferences prefs = context.getSharedPreferences("SHARED_PREFS_FILE3", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("TASK3", position);
        editor.commit();
        Log.d("janith@setSP",position+"");
    }

}

