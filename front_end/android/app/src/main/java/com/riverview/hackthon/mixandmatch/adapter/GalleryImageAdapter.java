package com.riverview.hackthon.mixandmatch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.riverview.hackthon.mixandmatch.R;

/**
 * Created by anupamchugh on 24/10/15.
 */
public class GalleryImageAdapter extends BaseAdapter
{
    private Context mContext;



    public GalleryImageAdapter(Context context)
    {
        mContext = context;
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


    // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        // TODO Auto-generated method stub
        ImageView i = new ImageView(mContext);
        //sdpath="/storage/extSdCard/";

        //i.setImageResource(mImageIds[index]);
        i.setLayoutParams(new Gallery.LayoutParams(200, 200));

        i.setScaleType(ImageView.ScaleType.FIT_XY);


        return i;
    }

   public String[] mImageIds = {
           "style_one",
            "style_two",
            "style_two",

    };

}

