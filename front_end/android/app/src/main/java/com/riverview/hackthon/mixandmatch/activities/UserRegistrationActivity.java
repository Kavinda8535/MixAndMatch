package com.riverview.hackthon.mixandmatch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.riverview.hackthon.mixandmatch.R;

public class UserRegistrationActivity extends AppCompatActivity {

    EditText etUserName,etContactNo,etEmail;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        etUserName = (EditText) findViewById(R.id.et_user_name);
        etContactNo = (EditText) findViewById(R.id.et_contact_no);
        etEmail = (EditText) findViewById(R.id.et_email);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =  new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
