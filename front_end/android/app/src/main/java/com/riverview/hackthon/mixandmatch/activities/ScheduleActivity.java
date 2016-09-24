package com.riverview.hackthon.mixandmatch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.riverview.hackthon.mixandmatch.R;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUserName,etContactNo,etEmail,et_city,et_country;
    Button btnUpdate;
    ImageView imgMonday, imgTuesday, imgWednesday, imgThursday, imgFriday, imgSaturday, imgSunday;
    boolean chkMonday,chkTuesday, chkWednesday, chkThursday, chkFriday, chkSaturday, chkSunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        btnUpdate = (Button) findViewById(R.id.btnSubmit);

        imgSunday = (ImageView) findViewById(R.id.imgSunday);
        imgSunday.setOnClickListener(this);

        imgMonday = (ImageView) findViewById(R.id.imgMonday);
        imgMonday.setOnClickListener(this);

        imgTuesday = (ImageView) findViewById(R.id.imgTuesday);
        imgTuesday.setOnClickListener(this);

        imgWednesday = (ImageView) findViewById(R.id.imgWednesday);
        imgWednesday.setOnClickListener(this);

        imgThursday = (ImageView) findViewById(R.id.imgThursday);
        imgThursday.setOnClickListener(this);

        imgFriday = (ImageView) findViewById(R.id.imgFriday);
        imgFriday.setOnClickListener(this);

        imgSaturday= (ImageView) findViewById(R.id.imgSaturday);
        imgSaturday.setOnClickListener(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgSunday:
                sunday();
                break;
            case R.id.imgMonday:
                monday();
                break;
            case R.id.imgTuesday:
                tuesday();
                break;
            case R.id.imgWednesday:
                wednesday();
                break;
            case R.id.imgThursday:
                thursday();
                break;
            case R.id.imgFriday:
                friday();
                break;
            case R.id.imgSaturday:
                saturday();
                break;

        }
    }

    private void saturday() {

        if(chkSaturday) {
            chkSaturday = false;
            imgSaturday.setImageResource(R.drawable.saturday_selected);
        }
        else {
            chkSaturday = true;
            imgSaturday.setImageResource(R.drawable.saturday);
        }
    }

    private void friday() {

        if(chkFriday) {
            chkFriday = false;
            imgFriday.setImageResource(R.drawable.friday_selected);
        }
        else {
            chkFriday = true;
            imgFriday.setImageResource(R.drawable.friday);
        }
    }

    private void wednesday() {

        if(chkWednesday) {
            chkWednesday = false;
            imgWednesday.setImageResource(R.drawable.wednesday_selected);
        }
        else {
            chkWednesday = true;
            imgWednesday.setImageResource(R.drawable.wednesday);
        }

    }

    private void thursday() {

        if(chkThursday) {
            chkThursday = false;
            imgThursday.setImageResource(R.drawable.thursday_selected);
        }
        else {
            chkThursday = true;
            imgThursday.setImageResource(R.drawable.thursday);
        }

    }

    private void tuesday() {

        if(chkTuesday) {
            chkTuesday = false;
            imgTuesday.setImageResource(R.drawable.tuesday_selected);
        }
        else {
            chkTuesday = true;
            imgTuesday.setImageResource(R.drawable.tuesday);
        }

    }

    private void monday() {

        if(chkMonday) {
            chkMonday = false;
            imgMonday.setImageResource(R.drawable.monday_selected);
        }
        else {
            chkMonday = true;
            imgMonday.setImageResource(R.drawable.monday);
        }

    }

    private void sunday() {

        if(chkSunday) {
            chkSunday = false;
            imgSunday.setImageResource(R.drawable.sunday_selected);
        }
        else {
            chkSunday = true;
            imgSunday.setImageResource(R.drawable.sunday);
        }

    }
}
