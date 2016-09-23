package com.riverview.hackthon.mixandmatch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.riverview.hackthon.mixandmatch.DbUtil.DatabaseHandler;
import com.riverview.hackthon.mixandmatch.R;
import com.riverview.hackthon.mixandmatch.model.BeanItem;
import com.riverview.hackthon.mixandmatch.model.BeanUserData;

public class UserRegistrationActivity extends AppCompatActivity {

    EditText etUserName,etContactNo,etEmail,et_city,et_country;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        etUserName = (EditText) findViewById(R.id.et_user_name);
        etContactNo = (EditText) findViewById(R.id.et_contact_no);
        etEmail = (EditText) findViewById(R.id.et_email);
        et_city = (EditText) findViewById(R.id.et_city);
        et_country = (EditText) findViewById(R.id.et_country);


        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserData();
                Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void saveUserData(){
        DatabaseHandler db = new DatabaseHandler(this);
        BeanUserData userData = new BeanUserData();
        userData.setName("");
        userData.setEmail("");
        userData.setCity("");
        userData.setCantact_number("");
        userData.setCountry("");

        long returnValue =  db.addUserData(userData);

        if(returnValue > 0){
            Toast.makeText(this,"Data are saved sucessfully",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
