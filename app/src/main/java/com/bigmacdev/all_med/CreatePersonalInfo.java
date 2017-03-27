package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;


public class CreatePersonalInfo extends AppCompatActivity {

    private EditText firstName, middleName, lastName, month, day, year;
    private Button next;

    private Login login;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal_info);

        Bundle bundle2 = new Bundle();
        bundle2 = this.getIntent().getExtras();
        login = (Login)bundle2.getSerializable("login");

        firstName = (EditText)findViewById(R.id.createFirstName);
        middleName = (EditText)findViewById(R.id.createMiddleName);
        lastName = (EditText)findViewById(R.id.createLastName);
        month = (EditText)findViewById(R.id.createDOBMonth);
        day = (EditText)findViewById(R.id.createDOBDay);
        year = (EditText)findViewById(R.id.createDOBYear);
        next = (Button)findViewById(R.id.createPINext);

        next.setOnClickListener(nextButton);

    }

    private View.OnClickListener nextButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean fN, lN, dobM, dobD, dobY;
            if(firstName.getText().toString()!=""){
                fN=true;
            } else {
                fN=false;
            }
            if (lastName.getText().toString()!=""){
                lN=true;
            }else{
                lN=false;
            }
            int m, d, y;
            m=Integer.parseInt(month.getText().toString());
            d=Integer.parseInt(day.getText().toString());
            y=Integer.parseInt(year.getText().toString());
            dobD= d>0 && d<=31;
            dobM= m>0 && m<=12;
            int thisYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
            int thisMonth = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
            int thisDay = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
            dobY=y>1900 && y<=thisYear;
            if (y==thisYear){
                dobM=m>0&&m<=thisMonth;
                if(m==thisMonth){
                    dobD=d>0&&d<=thisDay+1;
                }
            }
            if(dobD&&dobM&&dobY){
                //Create Patient
                patient = new Patient(firstName.getText().toString(), lastName.getText().toString(), y, m, d);
                patient.setmName(middleName.getText().toString());
                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                bundle.putSerializable("patient", patient);
                bundle.putSerializable("login", login);
                intent.putExtras(bundle);
                intent.setClass(CreatePersonalInfo.this, CreatePersonalInfo2.class);
                startActivity(intent);

            }else{
                Toast.makeText(CreatePersonalInfo.this, "Incorrect Birth Date!", Toast.LENGTH_SHORT).show();
                month.setText("");
                day.setText("");
                year.setText("");
            }
        }
    };
}
