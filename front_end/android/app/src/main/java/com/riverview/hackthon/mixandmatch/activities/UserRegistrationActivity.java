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
import com.riverview.hackthon.mixandmatch.Utils.SharedPref;
import com.riverview.hackthon.mixandmatch.model.BeanItem;
import com.riverview.hackthon.mixandmatch.model.BeanUserData;

public class UserRegistrationActivity extends AppCompatActivity {

    EditText etUserName,etContactNo,etEmail,etCity,etCountry;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Boolean userRegister = SharedPref.getBoolean(this,SharedPref.USER_REGISTER_KEY,false);

        if(!userRegister){
            setContentView(R.layout.activity_user_registration);
            etUserName = (EditText) findViewById(R.id.et_user_name);
            etContactNo = (EditText) findViewById(R.id.et_contact_no);
            etEmail = (EditText) findViewById(R.id.et_email);
            etCity = (EditText) findViewById(R.id.et_city);
            etCountry = (EditText) findViewById(R.id.et_country);


            btnSubmit = (Button) findViewById(R.id.btnSubmit);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submit();
                }
            });
        }else {
            Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void saveUserData(){
        DatabaseHandler db = new DatabaseHandler(this);
        BeanUserData userData = new BeanUserData();
        userData.setName(etUserName.getText().toString());
        userData.setEmail(etEmail.getText().toString());
        userData.setCity(etCity.getText().toString());
        userData.setCantact_number(etContactNo.getText().toString());
        userData.setCountry(etCountry.getText().toString());

        long returnValue =  db.addUserData(userData);

        if(returnValue > 0){
            Toast.makeText(this,"Data are saved sucessfully",Toast.LENGTH_SHORT).show();
            Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public  void submit(){

        if(etUserName.getText().toString().isEmpty()){
            etUserName.setError("Name is reqired");
        }else if(etContactNo.getText().toString().isEmpty()){
            etContactNo.setError("Name is reqired");
        }else if(etEmail.getText().toString().isEmpty()){
            etEmail.setError("Name is reqired");
        }else if(etCity.getText().toString().isEmpty()){
            etCity.setError("Name is reqired");
        }else if(etCountry.getText().toString().isEmpty()){
            etCountry.setError("Name is reqired");
        }else {
            saveUserData();
            SharedPref.saveBoolean(this, SharedPref.USER_REGISTER_KEY,
                    true);

        }
    }
}
