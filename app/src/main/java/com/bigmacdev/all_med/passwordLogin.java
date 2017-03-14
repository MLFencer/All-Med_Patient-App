package com.bigmacdev.all_med;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bigmacdev.all_med.model.Person;

public class passwordLogin extends AppCompatActivity {


    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);

        Bundle bundle = this.getIntent().getExtras();
        person = (Person) bundle.getSerializable("personObject");


    }
}
