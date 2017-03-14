package com.bigmacdev.all_med;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigmacdev.all_med.model.Patient;
import com.bigmacdev.all_med.model.Person;

public class createPassword extends AppCompatActivity {

    private EditText password, passwordConfirm;
    private Button submit;
    private TextView passwordLength;

    private Person person;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bundle bundle = this.getIntent().getExtras();
        person = (Person) bundle.getSerializable("personObject");

        password=(EditText)findViewById(R.id.passwordCreate);
        passwordConfirm=(EditText)findViewById(R.id.passwordConfirmCreate);
        submit=(Button)findViewById(R.id.submitBtnCreate);
        passwordLength=(TextView)findViewById(R.id.passwordLengthTxtCreate);

        submit.setEnabled(false);
        passwordConfirm.setEnabled(false);

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (password.getText().toString().length()<8){
                    password.setBackgroundColor(getResources().getColor(R.color.colorInEqual));
                } else {
                    password.setBackgroundColor(getResources().getColor(R.color.colorEqual));
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
                    submit.setEnabled(true);
                } else{
                    passwordConfirm.setBackgroundColor(getResources().getColor(R.color.colorInEqual));
                    submit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setEnabled(false);
                passwordConfirm.setEnabled(false);
                patient=new Patient(person);
                Client client = new Client();
                patient.setPassword(client.hashPassword(password.getText().toString()));
                try {
                    client.runRequest("new:"+client.encryptData(client.serialize(patient)));
                }catch (Exception e){
                    Log.e("CreatePassword", e.getLocalizedMessage());
                }
            }
        });
    }
}
