package com.bigmacdev.all_med;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class CreatePersonalInfo extends AppCompatActivity {

    private Spinner daySpin, monthSpin, yearSpin;
    private EditText firstName, middleName, lastName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal_info);
    }
}
