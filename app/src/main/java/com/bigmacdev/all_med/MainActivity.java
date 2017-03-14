package com.bigmacdev.all_med;

import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bigmacdev.all_med.model.Person;

import org.jasypt.util.text.BasicTextEncryptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class MainActivity extends AppCompatActivity {

    private Button submitBtn;
    private Spinner mSpin, dSpin, ySpin;
    private EditText fNameTxt, lNameTxt;
    private int year, month, day;
    private String firstName, lastName;
    private boolean exist;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        month=0;
        day=0;
        year=0;

        submitBtn = (Button) findViewById(R.id.submitBtn);
        fNameTxt = (EditText) findViewById(R.id.firstNameTxt);
        lNameTxt = (EditText) findViewById(R.id.lastNameTxt);
        mSpin = (Spinner) findViewById(R.id.monthSpin);
        dSpin = (Spinner) findViewById(R.id.daySpin);
        ySpin = (Spinner) findViewById(R.id.yearSpin);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((fNameTxt.equals("")||fNameTxt.equals(null))){
                  // Toast.makeText(MainActivity.this, "First Name must be entered!", Toast.LENGTH_SHORT).show();
                } else {
                    if((lNameTxt.equals("")||lNameTxt.equals(null))){
                       // Toast.makeText(MainActivity.this, "Last Name must be entered!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (month!=0 && day!=0 && year!=0){
                            Toast.makeText(MainActivity.this, "Checking for your Profile", Toast.LENGTH_LONG).show();
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
                                exist = checkPerson(serialize(person));
                            }catch (IOException e){
                                Log.e("Main",e.getLocalizedMessage());
                            }
                            if (exist){
                                Toast.makeText(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                            }else{
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Record Alert")
                                        .setMessage("Your record doesn't exist.\nWould you like to create it?")
                                        .setPositiveButton("Create Record", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

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

        Log.d("MainActivity", "1");

        months.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        longMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shortMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        febMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        febMonthLeap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        years.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Log.d("MainActivity", "2");

        mSpin.setAdapter(months);
        ySpin.setAdapter(years);
        spinSet();

        Log.d("MainActivity", "3");

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

        Log.d("MainActivity", "4");
    }

    private boolean checkPerson(String request){
        //Person person = new Person(firstName,lastName,year,month,day);
        Client client = new Client("9.9.9.126", 8088);
        String output= client.runRequest("patient:check:"+encryptData(request));
        Log.d("Main", output);
        return Boolean.parseBoolean(output);
    }

    private void spinSet(){
        dSpin.setEnabled(false);
        mSpin.setEnabled(false);
    }

    private static Object deserialize(String s) throws IOException, ClassNotFoundException{
        byte [] data = Base64.decode(s,0);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    private static String serialize(Serializable o)throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        String output = Base64.encodeToString(baos.toByteArray(),Base64.NO_WRAP|Base64.URL_SAFE);
        Log.d("Main", output);
        return output;
    }

    private String encryptData(String data){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(encryptionKey());
        return textEncryptor.encrypt(data);
    }
    private String decryptData(String data){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(encryptionKey());
        return textEncryptor.decrypt(data);
    }

    private static String encryptionKey(){
        Long unixTime = System.currentTimeMillis()/10000000;
        System.out.println(""+unixTime);
        String keyGenSeed = unixTime+"";
        String output="";
        String keyGenSeedStart=keyGenSeed;
        while (keyGenSeed.length()>0) {
            char letter = keyGenSeed.charAt(0);
            keyGenSeed=keyGenSeed.substring(1, keyGenSeed.length());
            switch (letter){
                case '1':
                    output+="a";
                    break;
                case '2':
                    output+="b";
                    break;
                case '3':
                    output+="c";
                    break;
                case '4':
                    output+="d";
                    break;
                case '5':
                    output+="e";
                    break;
                case '6':
                    output+="f";
                    break;
                case '7':
                    output+="g";
                    break;
                case '8':
                    output+="h";
                    break;
                case '9':
                    output+="j";
                    break;
                case '0':
                    output+="k";
                    break;
            }
        }
        return output+keyGenSeedStart+output;
    }

}
