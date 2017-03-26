package com.bigmacdev.all_med;

import android.app.Dialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import net.maritimecloud.internal.core.javax.json.Json;

import java.io.IOException;
import java.io.StringReader;


public class MainActivity extends AppCompatActivity {

    private Button enter, register;
    private EditText username, password;
    private Client client = new Client();
    private Login credentials = new Login();
    private Login login = new Login();
    private Patient patient = new Patient();
    private Boolean bool = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load Balancer---------------
        //Client connects to load balancer then gets the proper port
        //-------------------------

        enter = (Button) findViewById(R.id.loginSubmit);
        register = (Button) findViewById(R.id.loginRegister);
        username = (EditText) findViewById(R.id.loginUsername);
        password = (EditText) findViewById(R.id.loginPassword);

        register.setOnClickListener(registerButton);
        enter.setOnClickListener(enterButton);

        //submitBtn = (Button) findViewById(R.id.submitBtn);
       // fNameTxt = (EditText) findViewById(R.id.firstNameTxt);
       // lNameTxt = (EditText) findViewById(R.id.lastNameTxt);
      //  mSpin = (Spinner) findViewById(R.id.monthSpin);
      //  dSpin = (Spinner) findViewById(R.id.daySpin);
      //  ySpin = (Spinner) findViewById(R.id.yearSpin);

        /*submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((fNameTxt.equals("")||fNameTxt.equals(null))){
                  // Toast.makeText(MainActivity.this, "First Name must be entered!", Toast.LENGTH_SHORT).show();
                } else {
                    if((lNameTxt.equals("")||lNameTxt.equals(null))){
                       // Toast.makeText(MainActivity.this, "Last Name must be entered!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (month!=0 && day!=0 && year!=0){
                            Toast.makeText(MainActivity.this, "Checking for your Profile", Toast.LENGTH_SHORT).show();
                            submitBtn.setEnabled(false);
                            fNameTxt.setEnabled(false);
                            lNameTxt.setEnabled(false);
                            mSpin.setEnabled(false);
                            dSpin.setEnabled(false);
                            ySpin.setEnabled(false);
                            firstName=fNameTxt.getText().toString();
                            lastName=lNameTxt.getText().toString();
                            person = new Person(firstName,lastName,year,month,day);
                            try {
                                exist = checkPerson(person);
                            }catch (IOException e){
                                Log.e("Main",e.getLocalizedMessage());
                            }
                            if (exist){
                                Bundle bundle = new Bundle();
                                Intent intent = new Intent();
                                bundle.putSerializable("personObject", person);
                                intent.putExtras(bundle);
                                intent.setClass(MainActivity.this, passwordLogin.class);
                                startActivity(intent);
                            }else{
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Record Alert")
                                        .setMessage("Your record doesn't exist.\nWould you like to create it?")
                                        .setPositiveButton("Create Record", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Bundle bundle = new Bundle();
                                                Intent intent = new Intent();
                                                bundle.putSerializable("personObject", person);
                                                intent.putExtras(bundle);
                                                intent.setClass(MainActivity.this, createPassword.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                fNameTxt.setText("");
                                                fNameTxt.setEnabled(true);
                                                lNameTxt.setText("");
                                                lNameTxt.setEnabled(true);
                                                ySpin.setEnabled(true);
                                                ySpin.setSelection(0);
                                                mSpin.setSelection(0);
                                                dSpin.setSelection(0);
                                                spinSet();
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                        }
                    }
                }
            }
        });

        final ArrayAdapter<CharSequence> months = ArrayAdapter.createFromResource(this,R.array.months, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> longMonth = ArrayAdapter.createFromResource(this, R.array.long_month, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> shortMonth = ArrayAdapter.createFromResource(this, R.array.short_month, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> febMonth = ArrayAdapter.createFromResource(this, R.array.feb_month, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> febMonthLeap = ArrayAdapter.createFromResource(this, R.array.feb_leap_month, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> years = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);

       // Log.d("MainActivity", "1");

        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        longMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shortMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        febMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        febMonthLeap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        years.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // Log.d("MainActivity", "2");

        mSpin.setAdapter(months);
        ySpin.setAdapter(years);
        spinSet();

       // Log.d("MainActivity", "3");

        mSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    month = position;
                    //Toast.makeText(parent.getContext(), "Month Pos is: " + position, Toast.LENGTH_SHORT).show();
                    switch (position){
                        case 1:
                            dSpin.setAdapter(longMonth);
                            break;
                        case 2:
                            if (year%4==0){
                                dSpin.setAdapter(febMonthLeap);
                            } else {
                                dSpin.setAdapter(febMonth);
                            }
                            break;
                        case 3:
                            dSpin.setAdapter(longMonth);
                            break;
                        case 4:
                            dSpin.setAdapter(shortMonth);
                            break;
                        case 5:
                            dSpin.setAdapter(longMonth);
                            break;
                        case 6:
                            dSpin.setAdapter(shortMonth);
                            break;
                        case 7:
                            dSpin.setAdapter(longMonth);
                            break;
                        case 8:
                            dSpin.setAdapter(longMonth);
                            break;
                        case 9:
                            dSpin.setAdapter(shortMonth);
                            break;
                        case 10:
                            dSpin.setAdapter(longMonth);
                            break;
                        case 11:
                            dSpin.setAdapter(shortMonth);
                            break;
                        case 12:
                            dSpin.setAdapter(longMonth);
                            break;
                    }
                    dSpin.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    String items[] = getResources().getStringArray(R.array.years);
                    year = Integer.parseInt(items[position]);
                    //Toast.makeText(parent.getContext(), "Year is: " + year, Toast.LENGTH_SHORT).show();
                    mSpin.setEnabled(true);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    day = position;
                    //Toast.makeText(parent.getContext(), "Day is:" + day, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Log.d("MainActivity", "4");*/
    }


    private View.OnClickListener registerButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("client", client);
            intent.putExtras(bundle);
            intent.setClass(MainActivity.this, CreateUser.class);
            startActivity(intent);
        }
    };

    private  View.OnClickListener enterButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            credentials.setLogin(username.getText().toString(), password.getText().toString());
            bool=null;
            register.setEnabled(false);
            enter.setEnabled(false);
            new Thread(){
                public void run(){
                    bool=checkPerson(credentials);
                }
            }.start();
            while (bool==null){

            }
            if(bool){
                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "Incorrect Username or Password.", Toast.LENGTH_SHORT).show();
                enter.setEnabled(true);
                register.setEnabled(true);
                username.setText("");
                password.setText("");
            }
        }
    };


    private boolean checkPerson(Login creds){
        String output= client.runRequest("login:"+client.encryptData(creds.toJson()));
        Log.d("Main", output);
        if(output.startsWith("false")){
            return false;
        }else{
            output = client.decryptData(output);
            login.loadLoginData(Json.createReader(new StringReader(output)).readObject());
            if(creds.getPassword().equals(client.deHashPassword(login.getPassword(), creds.getPassword()))){
                return true;
            }
        }
        return false;
    }
}
