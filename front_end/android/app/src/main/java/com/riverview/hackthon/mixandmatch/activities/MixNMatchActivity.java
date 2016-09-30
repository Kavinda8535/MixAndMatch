package com.riverview.hackthon.mixandmatch.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.riverview.hackthon.mixandmatch.DbUtil.DatabaseHandler;
import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.model.BeanClothSelector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MixNMatchActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imgTop,imgMedium,imgBottom;

    private ImageView imgBack_1,imgBack_2,imgBack_3;

    private ImageView imgNext_1,imgNext_2,imgNext_3;

    private Button imgBtnLike,imgBtnDisLike;

    private HashMap<Integer,BeanClothSelector> topClothItem = new HashMap();

    private HashMap<Integer,BeanClothSelector> mediumClothItem = new HashMap();

    private HashMap<Integer,BeanClothSelector> bottomClothItem = new HashMap();

    private ArrayList<Integer> topClothIDList = new ArrayList<>();
    private ArrayList<Integer> mediumClothIDList = new ArrayList<>();
    private ArrayList<Integer> bottomClothIDList = new ArrayList<>();

    private int imgTopClothID = 0;
    private int imgMediumClothID = 0;
    private int imgBottomClothID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_nmatch);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        dataLoad();

    }

    private  void init(){

        imgTop = (ImageView) findViewById(R.id.imgTop);
        imgMedium = (ImageView) findViewById(R.id.imgMedium);
        imgBottom = (ImageView) findViewById(R.id.imgBottom);
        imgBack_1 = (ImageView) findViewById(R.id.imgBack_1);
        imgBack_2 = (ImageView) findViewById(R.id.imgBack_2);
        imgBack_3 = (ImageView) findViewById(R.id.imgBack_3);
        imgNext_1 = (ImageView) findViewById(R.id.imgNext_1);
        imgNext_2 = (ImageView) findViewById(R.id.imgNext_2);
        imgNext_3 = (ImageView) findViewById(R.id.imgNext_3);

        imgBtnLike = (Button) findViewById(R.id.imgBtnLike);
        imgBtnDisLike = (Button) findViewById(R.id.imgBtnDisLike);

        imgNext_1.setOnClickListener(this);
        imgNext_2.setOnClickListener(this);
        imgNext_3.setOnClickListener(this);
        imgBack_1.setOnClickListener(this);
        imgBack_2.setOnClickListener(this);
        imgBack_3.setOnClickListener(this);

       /* imgNext_1.setOnTouchListener(this);
        imgNext_2.setOnTouchListener(this);
        imgNext_3.setOnTouchListener(this);
        imgBack_1.setOnTouchListener(this);
        imgBack_2.setOnTouchListener(this);
        imgBack_3.setOnTouchListener(this);*/



    }

    private void dataLoad(){
        topClothItem = getClothListItem("'T','TT','TM'");
        mediumClothItem = getClothListItem("'M','TM'");
        bottomClothItem = getClothListItem("'B'");

        for (Map.Entry<Integer, BeanClothSelector> entry : topClothItem.entrySet()) {
            topClothIDList.add(entry.getKey());

        }

        for (Map.Entry<Integer, BeanClothSelector> entry : mediumClothItem.entrySet()) {
            mediumClothIDList.add(entry.getKey());
        }

        for (Map.Entry<Integer, BeanClothSelector> entry : bottomClothItem.entrySet()) {
            bottomClothIDList.add(entry.getKey());
        }

        imgTop.setImageDrawable(Drawable.createFromPath(topClothItem.get(topClothIDList.get(0)).getImage()));
        imgMedium.setImageDrawable(Drawable.createFromPath(mediumClothItem.get(mediumClothIDList.get(0)).getImage()));
        imgBottom.setImageDrawable(Drawable.createFromPath(bottomClothItem.get(bottomClothIDList.get(0)).getImage()));





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgTop:
                break;
            case R.id.imgMedium:

                break;
            case R.id.imgBottom:

                break;

            case R.id.imgBack_1:
                imgBack_1();
                break;
            case R.id.imgBack_2:
                imgBack_2();
                break;
            case R.id.imgBack_3:

                break;
            case R.id.imgNext_1:
                imgNext_1();
                break;
            case R.id.imgNext_2:
                imgNext_2();
                break;
            case R.id.imgNext_3:

                break;

            case R.id.imgBtnLike:

                break;

            case R.id.imgBtnDisLike:

                break;
            default:
                break;

        }
    }


    public HashMap<Integer,BeanClothSelector> getClothListItem(String condition){

        HashMap<Integer,BeanClothSelector> topClothList;
        DatabaseHandler db = new DatabaseHandler(this);
        topClothList = db.getClothItem(condition) ;

        return topClothList;

    }

    public void imgBack_1(){

        if(imgTopClothID > 0){
            imgTopClothID--;
            int topSelector = topClothIDList.get(imgTopClothID);
            imgTop.setImageDrawable(Drawable.createFromPath(topClothItem.get(topSelector).getImage()));

        }

        /*imgTopClothID++;
        int topSelector = topClothIDList.get(imgTopClothID);
        imgTop.setImageDrawable(Drawable.createFromPath(topClothItem.get(topSelector).getImage()));*/

    }

    public void imgBack_2(){
        if(imgMediumClothID > 0){
            imgMediumClothID--;
            int topSelector = mediumClothIDList.get(imgMediumClothID);
            imgMedium.setImageDrawable(Drawable.createFromPath(mediumClothItem.get(topSelector).getImage()));

        }
    }

    public void imgBack_3(){
        if(imgBottomClothID > 0){
            imgBottomClothID--;
            int topSelector = bottomClothIDList.get(imgBottomClothID);
            imgBottom.setImageDrawable(Drawable.createFromPath(bottomClothItem.get(topSelector).getImage()));

        }

    }

    public void imgNext_1(){
        if(imgTopClothID < topClothIDList.size()){
            int topSelector = topClothIDList.get(imgTopClothID);
            imgTop.setImageDrawable(Drawable.createFromPath(topClothItem.get(topSelector).getImage()));
            imgTop.setTag(topSelector);
            imgTopClothID++;
        }

    }

    public void imgNext_2(){
        if(imgMediumClothID < mediumClothIDList.size()){
            int topSelector = mediumClothIDList.get(imgMediumClothID);
            imgMedium.setImageDrawable(Drawable.createFromPath(mediumClothItem.get(topSelector).getImage()));
            imgMedium.setTag(topSelector);
            imgMediumClothID++;
        }

    }

    public void imgNext_3(){
        if(imgBottomClothID < bottomClothIDList.size()){
            int topSelector = bottomClothIDList.get(imgBottomClothID);
            imgBottom.setImageDrawable(Drawable.createFromPath(bottomClothItem.get(topSelector).getImage()));
            imgBottomClothID++;
        }

    }

    public static void loadImageWithPlaceholder(Context context, String url, ImageView imageView, int placeHolder){
        Picasso.with(context).load(url).placeholder(placeHolder).into(imageView);
    }

   /* @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){
            case R.id.imgTop:
                break;
            case R.id.imgMedium:

                break;
            case R.id.imgBottom:

                break;

            case R.id.imgBack_1:
                imgBack_1();
                break;
            case R.id.imgBack_2:

                break;
            case R.id.imgBack_3:

                break;
            case R.id.imgNext_1:
                imgNext_1();
                break;
            case R.id.imgNext_2:

                break;
            case R.id.imgNext_3:

                break;

            case R.id.imgBtnLike:

                break;

            case R.id.imgBtnDisLike:

                break;
            default:
                break;

        }
        return false;
    }*/
}
