package com.riverview.hackthon.mixandmatch.activities;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.riverview.hackthon.mixandmatch.DbUtil.DatabaseHandler;
import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.adapter.ClothItemAdapter;
import com.riverview.hackthon.mixandmatch.adapter.TopItemAdapter;
import com.riverview.hackthon.mixandmatch.model.BeanClothSelector;

import java.util.HashMap;
import java.util.Map;

public class StyleSelectActivity extends AppCompatActivity {

    private RecyclerView rvTop,rvCenter,rvBottom;

    private HashMap<Integer,BeanClothSelector> topClothItem = new HashMap();

    private HashMap<Integer,BeanClothSelector> mediumClothItem = new HashMap();

    private HashMap<Integer,BeanClothSelector> bottomClothItem = new HashMap();

    private TopItemAdapter topItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_select);

        dataLoad();

        rvTop = (RecyclerView) findViewById(R.id.rvTop);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTop.setLayoutManager(llm);

        topItemAdapter = new TopItemAdapter(this, topClothItem);
        rvTop.setAdapter(topItemAdapter);

        rvCenter = (RecyclerView) findViewById(R.id.rvCenter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTop.setLayoutManager(llm1);

        rvBottom = (RecyclerView) findViewById(R.id.rvBottom);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTop.setLayoutManager(llm2);
    }


    private void dataLoad(){
        topClothItem = getClothListItem("'T','TT','TM'");
        mediumClothItem = getClothListItem("'M','TM'");
        bottomClothItem = getClothListItem("'B'");

       /* for (Map.Entry<Integer, BeanClothSelector> entry : topClothItem.entrySet()) {
            topClothIDList.add(entry.getKey());

        }

        for (Map.Entry<Integer, BeanClothSelector> entry : mediumClothItem.entrySet()) {
            mediumClothIDList.add(entry.getKey());
        }

        for (Map.Entry<Integer, BeanClothSelector> entry : bottomClothItem.entrySet()) {
            bottomClothIDList.add(entry.getKey());
        }*/



    }

    public HashMap<Integer,BeanClothSelector> getClothListItem(String condition){

        HashMap<Integer,BeanClothSelector> topClothList;
        DatabaseHandler db = new DatabaseHandler(this);
        topClothList = db.getClothItem(condition) ;

        return topClothList;

    }
}
