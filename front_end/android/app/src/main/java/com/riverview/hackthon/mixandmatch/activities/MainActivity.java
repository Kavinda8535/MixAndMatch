package com.riverview.hackthon.mixandmatch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.activities.ItemDisplayActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAddItem,btnMixMatch,btnShedule,btnWeekArrange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnMixMatch = (Button) findViewById(R.id.btnMixMatch);
        btnShedule = (Button) findViewById(R.id.btnShedule);
        btnWeekArrange = (Button) findViewById(R.id.btnAddItem);

        btnAddItem.setOnClickListener(this);
        btnMixMatch.setOnClickListener(this);
        btnShedule.setOnClickListener(this);
        btnWeekArrange.setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){
            case R.id.btnAddItem:
                intent =  new Intent(getApplicationContext(),ItemDisplayActivity.class);
                startActivity(intent);
                break;
            case R.id.btnMixMatch:
                intent =  new Intent(getApplicationContext(),ItemDisplayActivity.class);
                startActivity(intent);
                break;
            case R.id.btnShedule:
                intent =  new Intent(getApplicationContext(),ItemDisplayActivity.class);
                startActivity(intent);
                break;

            case R.id.btnWeekArrange:
                intent =  new Intent(getApplicationContext(),ItemDisplayActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }

    }
}
