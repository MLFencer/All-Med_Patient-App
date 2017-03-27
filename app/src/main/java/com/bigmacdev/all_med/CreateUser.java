package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUser extends AppCompatActivity {

    private EditText password, passwordConfirm, username;
    private Button next;
    private Login login=new Login();
    private Client client = new Client();

    private boolean pReady=false, pcReady=false, uReady=false;
    private Boolean bool = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        username=(EditText)findViewById(R.id.createUsername);
        password=(EditText)findViewById(R.id.createPassword);
        passwordConfirm=(EditText)findViewById(R.id.createPasswordConfirm);
        next=(Button)findViewById(R.id.createSubmit);

        next.setEnabled(false);
        password.setEnabled(false);
        passwordConfirm.setEnabled(false);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(username.getText().toString().length()>=6){
                    uReady=true;
                    if(pReady && pcReady){
                        next.setEnabled(true);
                    }else{
                        password.setEnabled(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (password.getText().toString().length()<8){
                    password.setBackgroundColor(getResources().getColor(R.color.colorInEqual));
                    pReady=false;
                } else {
                    password.setBackgroundColor(getResources().getColor(R.color.colorEqual));
                    pReady=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                passwordConfirm.setEnabled(true);
            }
        });

        passwordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (password.getText().toString().equals(passwordConfirm.getText().toString())){
                    passwordConfirm.setBackgroundColor(getResources().getColor(R.color.colorEqual));
                    pcReady=true;
                    if(uReady&&pReady){
                        next.setEnabled(true);
                    }
                } else{
                    passwordConfirm.setBackgroundColor(getResources().getColor(R.color.colorInEqual));
                    next.setEnabled(false);
                    pcReady=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bool=null;
                username.setEnabled(false);
                password.setEnabled(false);
                passwordConfirm.setEnabled(false);
                next.setEnabled(false);
                login.setLogin(username.getText().toString(), password.getText().toString());
                new Thread(){
                    @Override
                    public void run() {
                        bool = checkUser(login);
                    }
                }.start();
                while(bool==null){}
                if (bool){
                    Toast.makeText(CreateUser.this, "Username already exists. Please try another one.", Toast.LENGTH_SHORT).show();
                    username.setEnabled(true);
                    uReady=false;
                }else {
                    Intent intent = new Intent();
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("login", login);
                    intent.putExtras(bundle1);
                    intent.setClass(CreateUser.this, CreatePersonalInfo.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkUser(Login l){
        String output = client.runRequest("check:"+client.encryptData(l.toJson()));
        Log.d("Check User", output);
        return Boolean.parseBoolean(output);
    }

}
