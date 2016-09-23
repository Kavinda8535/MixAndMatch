package com.riverview.hackthon.mixandmatch.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.model.BeanItem;
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
       /* BeanOffers offersItem = clothItemList.get(position);

        holder.newsTitle.setText(offersItem.getTitle());
        holder.newsDate.setText(AppUtil.convertDate(context, offersItem.getStart_date(), false) + " - " + AppUtil.convertDate(context, offersItem.getEnd_date(), false));
        AppUtil.loadImageWithPlaceholder(context, offersItem.getImage(), holder.newsItemImage, R.drawable.placeholder);

        holder.mArrow.setBackground(getDrawable(Iconify.IconValue.tbsm_forward, Color.parseColor("#7D7F87"), context));

        BeanPromotionItem temp = new BeanPromotionItem(offersItem.getId(), offersItem.getTitle());
        temp.setDescription(offersItem.getDescription());
        temp.setStartdate(offersItem.getStart_date());
        temp.setEnddate(offersItem.getEnd_date());
        temp.setImageurl(offersItem.getImage());
        temp.setRedemption_method(offersItem.getRedemption_method());
        temp.setTerms(offersItem.getTerms_and_conditions());

        holder.view.setOnClickListener(v -> ((MainActivity) context).loadFragment(SingleOfferNewFragment.newInstance(temp), true));*/
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

}
