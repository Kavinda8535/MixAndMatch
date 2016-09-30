package com.riverview.hackthon.mixandmatch.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.model.BeanClothSelector;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Rumesha on 27/09/2016.
 */

public class CenterItemAdapter extends RecyclerView.Adapter<CenterItemAdapter.ClothItemViewHolder>{



    public static final String TAG = CenterItemAdapter.class.getSimpleName();

    private Activity context;
    private HashMap<Integer,BeanClothSelector> clothItemList;

    public CenterItemAdapter(Activity context, HashMap<Integer,BeanClothSelector> list) {
        this.context = context;
        this.clothItemList = list;
    }

    @Override
    public CenterItemAdapter.ClothItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cloth__top_item_row, viewGroup, false);
        return new CenterItemAdapter.ClothItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CenterItemAdapter.ClothItemViewHolder holder, int position) {
        BeanClothSelector cloItem = clothItemList.get(position);

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
