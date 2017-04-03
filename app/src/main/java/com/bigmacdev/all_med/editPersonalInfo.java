package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editPersonalInfo extends AppCompatActivity {

    private EditText firstName, middleName, lastName, email, home, cell, address, city, state, zip, contact, contactHome, contactCell, contactRelation;
    private Button back, save;
    private Bundle bundle;
    private Patient patient;

    private Boolean done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);

        bundle = this.getIntent().getExtras();
        patient = (Patient) bundle.getSerializable("patient");

        back = (Button)findViewById(R.id.backEditPersonal);
        save = (Button)findViewById(R.id.saveEditPersonal);
        firstName=(EditText)findViewById(R.id.firstNameEditPersonal);
        middleName=(EditText)findViewById(R.id.middleNameEditPersonal);
        lastName=(EditText)findViewById(R.id.lastNameEditPersonal);
        email=(EditText)findViewById(R.id.emailEditPersonal);
        home=(EditText)findViewById(R.id.homeEditPersonal);
        cell=(EditText)findViewById(R.id.cellEditPersonal);
        contact=(EditText)findViewById(R.id.contactEditPersonal);
        contactHome=(EditText)findViewById(R.id.contactHomeEditPersonal);
        contactCell=(EditText)findViewById(R.id.contactCellEditPersonal);
        address=(EditText)findViewById(R.id.addressEditPersonal);
        city=(EditText)findViewById(R.id.cityEditPersonal);
        state=(EditText)findViewById(R.id.stateEditPersonal);
        zip=(EditText)findViewById(R.id.zipEditPersonal);
        contactRelation=(EditText)findViewById(R.id.relationshipEditPersonal);

        firstName.setText(patient.getfName());
        middleName.setText(patient.getmName());
        lastName.setText(patient.getlName());
        email.setText(patient.getEmail());
        home.setText(patient.getHomeNumber());
        cell.setText(patient.getCellNumber());
        address.setText(patient.getAddress());
        city.setText(patient.getCity());
        state.setText(patient.getState());
        zip.setText(patient.getZip());
        contact.setText(patient.getEmergencyContact());
        contactRelation.setText(patient.getEmergencyContactRelationship());
        contactCell.setText(patient.geteCCellNumber());
        contactHome.setText(patient.geteCHomeNumber());

        back.setOnClickListener(backClick);
        save.setOnClickListener(saveClick);

    }

    private View.OnClickListener backClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(editPersonalInfo.this, ViewPersonalInfo.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener saveClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            if (checkForChanges()){
                Toast.makeText(editPersonalInfo.this, "Change Detected", Toast.LENGTH_SHORT).show();
                Log.d("Save", "Change Detected");
                patient.setChangedBy(patient.getFullName());
                Log.d("Save", "Change made by: "+patient.getChangedBy());
                done =  null;
                new Thread(){
                    @Override
                    public void run() {
                        Log.d("Save", "Thread Started");
                        System.out.println("Thread Started");
                        done = updatePerson();
                    }
                }.start();
                Log.d("Save", "After Thread");
                while (done==null){}
                Log.d("Save", "After While");
                Bundle bundle1 = new Bundle();
                Log.d("Save1", patient.getmName());
                bundle1.putSerializable("patient", patient);
                intent.putExtras(bundle1);
                intent.setClass(editPersonalInfo.this, ViewPersonalInfo.class);
                Log.d("Save2", patient.getmName());
                startActivity(intent);
            }
        }
    };

    private boolean updatePerson(){
        Client client = new Client();
        System.out.println("Update Called");
        String output = client.runRequest("update:"+client.encryptData(patient.toJsonString()));
        return Boolean.parseBoolean(output);
    }

    private boolean checkForChanges(){
        boolean fNameChanged, lNameChanged, mNameChanged, homeChanged, cellChanged, addressChanged, cityChanged, zipChanged, stateChanged, emailChanged, contactChanged, cHomeChanged, cCellChanged, cRelationshipChanged;
        lNameChanged = patient.getlName().equalsIgnoreCase(lastName.getText().toString());
        fNameChanged = patient.getfName().equalsIgnoreCase(firstName.getText().toString());
        mNameChanged = patient.getmName().equalsIgnoreCase(middleName.getText().toString());
        homeChanged = patient.getHomeNumber().equalsIgnoreCase(home.getText().toString());
        cellChanged = patient.getCellNumber().equalsIgnoreCase(cell.getText().toString());
        addressChanged = patient.getAddress().equalsIgnoreCase(address.getText().toString());
        cityChanged = patient.getCity().equalsIgnoreCase(city.getText().toString());
        zipChanged = patient.getZip().equalsIgnoreCase(zip.getText().toString());
        stateChanged = patient.getState().equalsIgnoreCase(state.getText().toString());
        emailChanged = patient.getEmail().equalsIgnoreCase(email.getText().toString());
        contactChanged = patient.getEmergencyContact().equalsIgnoreCase(contact.getText().toString());
        cHomeChanged = patient.geteCHomeNumber().equalsIgnoreCase(contactHome.getText().toString());
        cCellChanged = patient.geteCCellNumber().equalsIgnoreCase(contactCell.getText().toString());
        cRelationshipChanged = patient.getEmergencyContactRelationship().equalsIgnoreCase(contactRelation.getText().toString());

        if(!fNameChanged){
            patient.addChange("First Name");
            patient.setfName(firstName.getText().toString());
        }
        if(!lNameChanged){
            patient.addChange("Last Name");
            patient.setlName(lastName.getText().toString());
        }
        if (!mNameChanged){
            patient.addChange("Middle Name");
            patient.setmName(middleName.getText().toString());
        }
        if(!homeChanged){
            patient.addChange("Home Phone Number");
            patient.setHomeNumber(home.getText().toString());
        }
        if(!cellChanged){
            patient.addChange("Cell Phone Number");
            patient.setCellNumber(cell.getText().toString());
        }
        if(!addressChanged){
            patient.addChange("Street Address");
            patient.setAddress(address.getText().toString());
        }
        if(!cityChanged){
            patient.addChange("City");
            patient.setCity(city.getText().toString());
        }
        if(!zipChanged){
            patient.addChange("Zip");
            patient.setZip(zip.getText().toString());
        }
        if(!stateChanged){
            patient.addChange("State");
            patient.setState(state.getText().toString());
        }
        if(!emailChanged){
            patient.addChange("Email");
            patient.setEmail(email.getText().toString());
        }
        if(!contactChanged){
            patient.addChange("Emergency Contact");
            patient.setEmergencyContact(contact.getText().toString());
        }
        if (!cHomeChanged){
            patient.addChange("Emergency Contact Home Phone Number");
            patient.seteCHomeNumber(contactHome.getText().toString());
        }
        if(!cCellChanged){
            patient.addChange("Emergency Contact Cell Phone Number");
            patient.seteCCellNumber(contactCell.getText().toString());
        }
        if(!cRelationshipChanged){
            patient.addChange("Emergency Contact Relationship");
            patient.setEmergencyContactRelationship(contactRelation.getText().toString());
        }
        Log.d("Changes", patient.getmName());
        return fNameChanged || lNameChanged || mNameChanged || homeChanged || cellChanged || addressChanged || cityChanged || zipChanged || stateChanged || emailChanged || contactChanged || cHomeChanged || cCellChanged || cRelationshipChanged;
    }


}
