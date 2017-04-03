package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PatientHistoryMenu extends AppCompatActivity {

    private Button conditions, hospitalizations, problems, physicals, medications, doctors, imunizations, tests, family, habits, dental, personal, sexual, female, back;
    private Bundle bundle;
    private Intent intent;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history_menu);

        bundle = this.getIntent().getExtras();
        intent = new Intent();
        intent.putExtras(bundle);
        patient = (Patient)bundle.getSerializable("patient");

        if (patient.getGender().equalsIgnoreCase("male")){
            female.setEnabled(false);
        }

        conditions = (Button)findViewById(R.id.conditionsHistMenu);
        hospitalizations = (Button)findViewById(R.id.hospitalizationsHistMenu);
        problems = (Button)findViewById(R.id.problemsHistMenu);
        physicals = (Button)findViewById(R.id.physicalsHistMenu);
        medications = (Button)findViewById(R.id.medicationsHistMenu);
        doctors = (Button)findViewById(R.id.healthcareHistMenu);
        imunizations = (Button)findViewById(R.id.imunizationsHistMenu);
        tests = (Button)findViewById(R.id.testsHistMenu);
        family = (Button)findViewById(R.id.familyHistMenu);
        habits = (Button)findViewById(R.id.habitsHistMenu);
        dental = (Button)findViewById(R.id.dentalHistMenu);
        personal = (Button)findViewById(R.id.personalHistMenu);
        sexual = (Button)findViewById(R.id.sexualHistMenu);
        female = (Button)findViewById(R.id.womenHistMenu);
        back = (Button)findViewById(R.id.backHistMenu);

        conditions.setOnClickListener(conditionsListener);
        hospitalizations.setOnClickListener(hospitalizationsListener);
        problems.setOnClickListener(problemsListener);
        physicals.setOnClickListener(physicalsListener);
        medications.setOnClickListener(medicationsListener);
        doctors.setOnClickListener(doctorsListener);
        imunizations.setOnClickListener(imunizationsListener);
        tests.setOnClickListener(testsListener);
        family.setOnClickListener(familyListener);
        habits.setOnClickListener(habitsListener);
        dental.setOnClickListener(dentalListener);
        personal.setOnClickListener(personalListener);
        sexual.setOnClickListener(sexualListener);
        female.setOnClickListener(femaleListener);
        back.setOnClickListener(backListener);

    }

    private View.OnClickListener conditionsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Conditions", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener hospitalizationsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Hospitalizations", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener problemsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Other Medical Problems", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener physicalsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Physicals", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener medicationsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Medications", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener doctorsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Doctors", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener imunizationsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Imunizations", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener testsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Tests", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener familyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Family History", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener habitsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Habits", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener dentalListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Dental History", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener personalListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Personal History", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener sexualListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Sexual History", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener femaleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(PatientHistoryMenu.this, "Go to Female Specific History", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            intent.setClass(PatientHistoryMenu.this, MenuActivity.class);
            startActivity(intent);

        }
    };

}
