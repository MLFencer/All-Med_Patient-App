package com.bigmacdev.all_med;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigmacdev.all_med.model.Patient;
import com.bigmacdev.all_med.model.Person;

import net.maritimecloud.internal.core.javax.json.Json;

import java.io.StringReader;

public class passwordLogin extends AppCompatActivity {

    private EditText password;
    private Button submit;

    private String pass;
    private boolean login;

    private Person person;
    private Patient patient;
    private Patient patientData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);

        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bundle bundle = this.getIntent().getExtras();
        person = (Person) bundle.getSerializable("personObject");
        patient = new Patient(person);

        password = (EditText)findViewById(R.id.passwordLogin);
        submit = (Button)findViewById(R.id.submitBtnLogin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass=password.getText().toString();
                if(pass.length()==0){
                    Toast.makeText(passwordLogin.this,"You must enter your password.",Toast.LENGTH_SHORT);
                } else{
                    login = runPassword(patient, pass);
                    if(login){
                        Toast.makeText(passwordLogin.this,"Correct", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(passwordLogin.this, "Your password is incorrect", Toast.LENGTH_SHORT).show();
                        password.setText("");
                    }
                }
            }
        });


    }

    private boolean runPassword(Patient p, String pword){
        Client client = new Client();
        try {
            String request = "login:" + client.encryptData(p.toJson());
            String x = client.runRequest(request);
            if (x.equals("false")){
                return false;
            }else{
                x=client.decryptData(x);
                Log.d("Login Recieved: ",x);
                patientData= new Patient (Json.createReader(new StringReader(x)).readObject());
                String tempPass = client.deHashPassword(patientData.getPassword(),pword);
                if (tempPass!=null){
                    Log.d("Login",tempPass);
                }
                if (pword.equals(tempPass)){
                    return true;
                }else{
                    return false;
                }
            }
        }catch(Exception e){
            Log.e("Login", "Error in runPassword");
            return false;
        }
    }
}
