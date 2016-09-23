package com.riverview.hackthon.mixandmatch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.adapter.ClothItemAdapter;
import com.riverview.hackthon.mixandmatch.model.BeanItem;

import java.util.ArrayList;

public class ItemDisplayActivity extends AppCompatActivity {

    private RecyclerView recycleViewItem;
    private ClothItemAdapter clothItemAdapter;
    private ArrayList<BeanItem> clothItemList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recycleViewItem = (RecyclerView) findViewById(R.id.recycleViewItem);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycleViewItem.setLayoutManager(llm);

        clothItemAdapter = new ClothItemAdapter(this, clothItemList);
        recycleViewItem.setAdapter(clothItemAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =  new Intent(getApplicationContext(),AddItemActivity.class);
                startActivity(intent);
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

}
