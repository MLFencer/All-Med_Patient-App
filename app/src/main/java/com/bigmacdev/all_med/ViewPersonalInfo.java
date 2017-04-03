package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewPersonalInfo extends AppCompatActivity {

    private TextView ssn, email, home, cell, address, city, zip, contact, contactRelation, state, contactCell, contactHome;
    private Button back, edit;

    private Patient patient = new Patient();
    private Login login = new Login();
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_personal_info);

        bundle = this.getIntent().getExtras();
        patient = (Patient)bundle.getSerializable("patient");
        login = (Login)bundle.getSerializable("login");

        Log.d("View", patient.getmName());

        edit = (Button)findViewById(R.id.editViewPersonal);
        back = (Button)findViewById(R.id.backViewPersonal);
        ssn = (TextView) findViewById(R.id.ssnViewPersonal);
        email = (TextView) findViewById(R.id.emailViewPersonal);
        home = (TextView) findViewById(R.id.homeViewPersonal);
        cell = (TextView) findViewById(R.id.cellViewPersonal);
        address = (TextView) findViewById(R.id.addressViewPersonal);
        city = (TextView) findViewById(R.id.cityViewPersonal);
        state = (TextView) findViewById(R.id.stateViewPersonal);
        zip = (TextView) findViewById(R.id.zipViewPersonal);
        contact = (TextView) findViewById(R.id.contactViewPersonal);
        contactRelation = (TextView) findViewById(R.id.relationshipViewPersonal);
        contactCell = (TextView) findViewById(R.id.contactCellViewPersonal);
        contactHome = (TextView) findViewById(R.id.contactHomeViewPersonal);

        Log.d("View", patient.getShortSsn());
        ssn.setText("#####"+patient.getShortSsn());
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
        edit.setOnClickListener(editClick);
    }

    View.OnClickListener backClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(ViewPersonalInfo.this, MenuActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener editClick =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(ViewPersonalInfo.this, editPersonalInfo.class);
            startActivity(intent);
        }
    };
}
