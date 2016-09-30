package com.riverview.hackthon.mixandmatch.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.model.BeanClothSelector;
import com.riverview.hackthon.mixandmatch.model.BeanItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rumesha on 27/09/2016.
 */

public class TopItemAdapter extends RecyclerView.Adapter<TopItemAdapter.ClothItemViewHolder>{

    public static final String TAG = ClothItemAdapter.class.getSimpleName();

    private Activity context;
    private HashMap<Integer,BeanClothSelector> clothItemList;
    private List<BeanClothSelector> topItemList = new ArrayList<>();

    public TopItemAdapter(Activity context, HashMap<Integer,BeanClothSelector> list) {
        this.context = context;
        this.clothItemList = list;

        for (Map.Entry<Integer, BeanClothSelector> entry : clothItemList.entrySet()) {
            topItemList.add(entry.getValue());

        }
    }

    @Override
    public TopItemAdapter.ClothItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cloth__top_item_row, viewGroup, false);
        return new TopItemAdapter.ClothItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ClothItemViewHolder holder, int position) {
        BeanClothSelector cloItem = topItemList.get(position);

       // holder.newsTitle.setText(cloItem.getBrand());
       // holder.newsDate.setText(cloItem.getColor());

        if(cloItem.getImage() != null && !cloItem.getImage().isEmpty()){
            holder.newsItemImage.setImageDrawable(Drawable.createFromPath(cloItem.getImage()));
        } else{
            loadImageWithPlaceholder(context, null,  holder.newsItemImage, R.drawable.placeholder);
        }
    }


    @Override
    public int getItemCount() {
        return clothItemList.size();

    }

    public class ClothItemViewHolder extends RecyclerView.ViewHolder {

        protected ImageView newsItemImage;

        protected View view;

        public ClothItemViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            newsItemImage = (ImageView) itemView.findViewById(R.id.imgTop);



        }
    }

    public static void loadImageWithPlaceholder(Context context, String url, ImageView imageView, int placeHolder){
        Picasso.with(context).load(url).placeholder(placeHolder).into(imageView);
    }
}
