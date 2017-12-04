package com.example.lakmal.coffer.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lakmal.coffer.Model.ListItem;
import com.example.lakmal.coffer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DerpAdapter extends RecyclerView.Adapter <DerpAdapter.DerpHolder>{

    private List<ListItem> listData;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;



    public interface ItemClickCallback {
        void onItemClick(int p);
        void onSecondaryIconClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public DerpAdapter(List <ListItem> listData, Context c){
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public DerpAdapter.DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {

        ListItem item = listData.get(position);

        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubTitle());
        holder.thumbnail.setImageResource(item.getImageResId());
        holder.offPercent.setText(item.getOff_percent());
        holder.detail.setText(item.getDetail());

        if (item.isFavourite()){
            holder.secondaryIcon.setImageResource(R.drawable.background_state_drawable);
        }

        else {
            //holder.secondaryIcon.setImageResource(android.R.drawable.ic_popup_reminder);

        }
    }

    public void setListData(ArrayList<ListItem> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView thumbnail;
        ImageView secondaryIcon;
        TextView title;
        TextView subTitle;
        View container;
        TextView offPercent ;
        TextView detail ;
        ImageView isFavourite_img ;

        Boolean isFavourite = false;
        String wishlist_url = "enter ther url here";



        public DerpHolder(final View itemView) {
            super(itemView);
            thumbnail     = itemView.findViewById(R.id.im_item_icon);

            isFavourite_img   = itemView.findViewById(R.id.im_item_favourite);
            isFavourite_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    isFavourite = !isFavourite;

                    Log.d("janitha",isFavourite+"");

                    if(isFavourite==true){
                        isFavourite_img.setImageResource(R.drawable.ic_favorite_black_36dp);
                    }
                    else isFavourite_img.setImageResource(R.drawable.ic_favorite_border_black_36dp);


                    JSONObject jsonBody = new JSONObject();

                    try {
                        jsonBody.put("isFavourite",isFavourite);
                        jsonBody.put("item id",isFavourite);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("janitha2",jsonBody+"");
                    JsonObjectRequest req = new JsonObjectRequest(

                        Request.Method.POST,

                        wishlist_url,

                        jsonBody,

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    String server_version = response.getString("version");

                                } catch (JSONException e) {
                                    e.printStackTrace();
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
                }
            });

            subTitle     = itemView.findViewById(R.id.lbl_item_sub_title);

            title        = itemView.findViewById(R.id.lbl_item_text);

            offPercent   = itemView.findViewById(R.id.lbl_off_percent_card);

            detail       = itemView.findViewById(R.id.lbl_item_description);

            container    = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cont_item_root){
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                itemClickCallback.onSecondaryIconClick(getAdapterPosition());
            }
        }


    }
}