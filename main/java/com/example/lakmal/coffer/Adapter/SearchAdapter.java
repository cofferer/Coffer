package com.example.lakmal.coffer.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lakmal.coffer.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter <SearchAdapter.DerpHolder> {

    private List<SearchListItem> listData;
    private LayoutInflater inflater;

    public SearchAdapter(List<SearchListItem> listData, Context c) {
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public SearchAdapter.DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {

        SearchListItem item = listData.get(position);

        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubTitle());

    }

    public void setListData(ArrayList<SearchListItem> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView thumbnail;
        TextView title;
        TextView subTitle;
        View container;


        public DerpHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.im_item_icon);

            subTitle = itemView.findViewById(R.id.lbl_item_sub_title);

            title = itemView.findViewById(R.id.lbl_item_text);

            container = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("janitha", "item clicked");

        }


    }
}
