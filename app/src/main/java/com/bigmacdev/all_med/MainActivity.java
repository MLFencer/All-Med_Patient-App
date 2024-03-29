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
import net.maritimecloud.internal.core.javax.json.JsonReader;

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

    }


    private View.OnClickListener registerButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
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
                bool=null;
                new Thread(){
                    @Override
                    public void run() {
                        bool=getRecord(login);
                    }
                }.start();
                while(bool==null){}
                if(bool){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("patient",patient);
                    bundle.putSerializable("login", login);
                    intent.putExtras(bundle);
                    intent.setClass(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            }else {
                Toast.makeText(MainActivity.this, "Incorrect Username or Password.", Toast.LENGTH_SHORT).show();
                enter.setEnabled(true);
                register.setEnabled(true);
                username.setText("");
                password.setText("");
            }
        }
    };


    private boolean getRecord(Login creds){
        String output = client.runRequest("get:"+client.encryptData(login.toJson()));
        if(output.contains("false")){
            return false;
        } else{
            output=client.decryptData(output);
            patient.loadData(Json.createReader(new StringReader(output)).readObject());
            return true;
        }
    }


    private boolean checkPerson(Login creds){
        String output= client.runRequest("login:"+client.encryptData(creds.toJson()));
        if(output.startsWith("false")){
            return false;
        }else{
            output = client.decryptData(output);
            login.loadLoginData(Json.createReader(new StringReader(output)).readObject());
            String x = client.deHashPassword(login.getPassword(), creds.getPassword());
            if(creds.getPassword().equals(client.deHashPassword(login.getPassword(), creds.getPassword()))){
                return true;
            }
        }
        return false;
    }
}
