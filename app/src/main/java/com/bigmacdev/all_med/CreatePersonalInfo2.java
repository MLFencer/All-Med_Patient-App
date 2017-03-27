package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreatePersonalInfo2 extends AppCompatActivity {

    private EditText ssn, email, home, cell, address, city, zip, contact, contactRelation, state, contactCell, contactHome;
    private Spinner gender;
    private Button save;

    private Patient patient;
    private Login login;
    private Client client = new Client();
    private Boolean bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal_info2);

        Bundle bundle = this.getIntent().getExtras();
        patient = (Patient)bundle.getSerializable("patient");
        login = (Login)bundle.getSerializable("login");

        save = (Button)findViewById(R.id.savePersonalInfo2Btn);
        ssn = (EditText)findViewById(R.id.createSSN);
        email = (EditText)findViewById(R.id.createEmail);
        home = (EditText)findViewById(R.id.createHomePhone);
        cell = (EditText)findViewById(R.id.createCellPhone);
        address = (EditText)findViewById(R.id.createAddress);
        city = (EditText)findViewById(R.id.createCity);
        state = (EditText)findViewById(R.id.createState);
        zip = (EditText)findViewById(R.id.createZip);
        contact = (EditText)findViewById(R.id.createEmergencyContact);
        contactRelation = (EditText)findViewById(R.id.createEmergencyRelationship);
        contactCell = (EditText)findViewById(R.id.createEmergencyCellPhone);
        contactHome = (EditText)findViewById(R.id.createEmergencyHomePhone);
        gender = (Spinner) findViewById(R.id.createGender);

        final ArrayAdapter<CharSequence>genders = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        genders.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genders);

        save.setOnClickListener(saveClick);

    }

    private View.OnClickListener saveClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bool = null;
           if (checkAll()){
               new Thread(){
                   @Override
                   public void run() {
                       bool=go();
                   }
               }.start();
               while (bool==null){}
               if(bool){
                   Bundle bundle1 = new Bundle();
                   bundle1.putSerializable("patient", patient);
                   Intent intent = new Intent();
                   intent.putExtras(bundle1);
                   intent.setClass(CreatePersonalInfo2.this, MenuActivity.class);
                   startActivity(intent);
               }else{
                   Toast.makeText(CreatePersonalInfo2.this, "Error!", Toast.LENGTH_SHORT).show();
               }

           }else{
               Toast.makeText(CreatePersonalInfo2.this, "You must finish filling out the form.", Toast.LENGTH_SHORT).show();
           }
        }
    };

    private boolean go(){
        patient.setAddress(address.getText().toString());
        patient.setSsn(ssn.getText().toString());
        patient.setCity(city.getText().toString());
        patient.setState(city.getText().toString());
        patient.setGender(gender.getSelectedItem().toString());
        patient.setZip(zip.getText().toString());
        patient.setEmergencyContact(contact.getText().toString());
        patient.setHomeNumber(home.getText().toString());
        patient.setCellNumber(cell.getText().toString());
        patient.setEmergencyContactRelationship(contactRelation.getText().toString());
        patient.seteCHomeNumber(contactHome.getText().toString());
        patient.seteCCellNumber(contactCell.getText().toString());
        patient.setEmail(email.getText().toString());
        patient.setUsername(login.getUsername());
        patient.setPassword(client.hashPassword(login.getPassword()));

        String output=client.runRequest("new:"+client.encryptData(patient.toJsonString()));
        return Boolean.parseBoolean(output);
    }

    private boolean checkAll(){
        return checkGender()&&checkSSN()&&checkEmail()&&checkHome()&&checkCell()&&checkStreet()&&checkCity()&&checkState()&&checkZip()&&checkContact()&&checkRelationship()&&checkContactHome()&&checkContactCell();
    }

    private boolean checkGender(){
        String gen = gender.getSelectedItem().toString();
        return gen.equalsIgnoreCase("male")||gen.equalsIgnoreCase("female");
    }

    private boolean checkSSN(){
        return ssn.getText().toString().length()==9;
    }

    private boolean checkEmail(){
        return email.getText().toString().length()>0;
    }

    private boolean checkHome(){
        return home.getText().toString().length()==10;
    }

    private boolean checkCell(){
        return cell.getText().toString().length()==10;
    }

    private boolean checkStreet(){
        return address.getText().toString().length()>0;
    }

    private boolean checkCity(){
        return city.getText().toString().length()>0;
    }

    private boolean checkState(){
        return state.getText().toString().length()>0;
    }

    private boolean checkZip(){
        return zip.getText().toString().length()>0;
    }

    private boolean checkContact(){
        return contact.getText().toString().length()>0;
    }

    private boolean checkRelationship(){
        return contactRelation.getText().toString().length()>0;
    }

    private boolean checkContactHome(){
        return contactHome.getText().toString().length()>0;
    }

    private boolean checkContactCell(){
        return contactCell.getText().toString().length()>0;
    }
}
