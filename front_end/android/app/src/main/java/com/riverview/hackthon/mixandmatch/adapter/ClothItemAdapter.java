package com.riverview.hackthon.mixandmatch.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.Utils.AppUtil;
import com.riverview.hackthon.mixandmatch.model.BeanItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 9/8/16.
 */
public class ClothItemAdapter extends RecyclerView.Adapter<ClothItemAdapter.ClothItemViewHolder> {
    public static final String TAG = ClothItemAdapter.class.getSimpleName();

    private Activity context;
    private ArrayList<BeanItem> clothItemList;

    public ClothItemAdapter(Activity context, ArrayList<BeanItem> list) {
        this.context = context;
        this.clothItemList = list;
    }

    @Override
    public ClothItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cloth_row_item, viewGroup, false);
        return new ClothItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ClothItemViewHolder holder, int position) {
        BeanItem cloItem = clothItemList.get(position);

        holder.newsTitle.setText(cloItem.getBrand());
        holder.newsDate.setText(cloItem.getColor());

        if(cloItem.getImage() != null && !cloItem.getImage().isEmpty()){
            loadImageWithPlaceholder(context, cloItem.getImage(), holder.newsItemImage, R.drawable.placeholder);
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
        protected TextView newsTitle;
        protected TextView newsDate;
        protected TextView mArrow;
        protected View view;

        public ClothItemViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            newsItemImage = (ImageView) itemView.findViewById(R.id.imgNews);
            newsTitle = (TextView) itemView.findViewById(R.id.tvNewsTitle);
            newsDate = (TextView) itemView.findViewById(R.id.tvNewsDate);
            mArrow = (TextView) itemView.findViewById(R.id.tvArrow);


        }
    }

    public static void loadImageWithPlaceholder(Context context, String url, ImageView imageView, int placeHolder){
        Picasso.with(context).load(url).placeholder(placeHolder).into(imageView);
    }

}
