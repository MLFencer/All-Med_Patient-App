package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;

public class Conditions extends AppCompatActivity {

    private CheckedTextView diabetes, kidney, stroke, tuberculosis, arrythmia, eye, blood, hepatitis, depression, artery, std, asthma, thyroid, emphysema, heart, hAttack, seizures, cancer, other;
    private EditText cancers, stds, eyes, others;
    private Button back, edit;

    private Patient patient;

    private boolean editing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);

        editing=false;

        Bundle bundle = getIntent().getExtras();
        patient = (Patient)bundle.getSerializable("patient");

        diabetes = (CheckedTextView)findViewById(R.id.diabetes);
        kidney = (CheckedTextView)findViewById(R.id.kidneyDisease);
        stroke = (CheckedTextView)findViewById(R.id.stroke);
        tuberculosis = (CheckedTextView)findViewById(R.id.tuberculosis);
        arrythmia = (CheckedTextView)findViewById(R.id.arrythmia);
        eye = (CheckedTextView)findViewById(R.id.eyeproblem);
        blood = (CheckedTextView)findViewById(R.id.highBlood);
        hepatitis = (CheckedTextView)findViewById(R.id.hepatitis);
        depression = (CheckedTextView)findViewById(R.id.depression);
        artery = (CheckedTextView)findViewById(R.id.coronaryDisease);
        std = (CheckedTextView)findViewById(R.id.std);
        asthma = (CheckedTextView)findViewById(R.id.asthma);
        thyroid = (CheckedTextView)findViewById(R.id.thyroidDisease);
        emphysema = (CheckedTextView)findViewById(R.id.emphysema);
        heart = (CheckedTextView)findViewById(R.id.heartFailure);
        hAttack = (CheckedTextView)findViewById(R.id.heartAttack);
        seizures = (CheckedTextView)findViewById(R.id.seizuresList);
        cancer = (CheckedTextView)findViewById(R.id.cancer);
        other = (CheckedTextView)findViewById(R.id.otherConditions);

        cancers = (EditText)findViewById(R.id.cancers);
        stds = (EditText)findViewById(R.id.stds);
        eyes = (EditText)findViewById(R.id.eyeProblems);
        others = (EditText)findViewById(R.id.othersConditions);

        back = (Button)findViewById(R.id.backConditions);
        edit = (Button)findViewById(R.id.editConditions);

        back.setOnClickListener(backClick);
        edit.setOnClickListener(editClick);

        loadData();
        setDisabled();
    }

    private View.OnClickListener backClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Conditions.this, PatientHistoryMenu.class);
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("patient", patient);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
    };

    private View.OnClickListener editClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editing){
                edit.setText("Edit");
                editing=false;
                setDisabled();
                //Todo: check for changes, if changes then record what changed and who changed it.
                //Todo: put changes into patient and upload all to server.
            }else{
                editing=true;
                edit.setText("Submit");
                //Todo: set all to enabled except for eyes, cancers, stds, and others
                //Todo: make listeners for eye, cancer, std, and other to enable or disable eyes, cancers, stds, and others
            }
        }
    };

    private void loadData(){
        diabetes.setChecked(patient.isDiabetes());
        kidney.setChecked(patient.isKidneyDisease());
        stroke.setChecked(patient.isStroke());
        tuberculosis.setChecked(patient.isTuberculosis());
        arrythmia.setChecked(patient.isArrythmia());
        blood.setChecked(patient.isHighBloodPressure());
        hepatitis.setChecked(patient.isHepatitis());
        depression.setChecked(patient.isDepression());
        artery.setChecked(patient.isCoronaryArteryDisease());
        asthma.setChecked(patient.isAsthma());
        thyroid.setChecked(patient.isThyroidDisease());
        emphysema.setChecked(patient.isEmphasyma());
        heart.setChecked(patient.isCongestiveHeartFailure());
        hAttack.setChecked(patient.isHeartAttack());
        seizures.setChecked(patient.isSeizures());
        eye.setChecked(patient.isEyeProblem());
        if(patient.isEyeProblem()){
            String eyeText="";
            for(int i=0; i<patient.getEyeProblems().size(); i++){
                eyeText=eyeText+", "+patient.getEyeProblems().get(i);
            }
            eyes.setText(eyeText);
        }
        std.setChecked(patient.isStd());
        if(patient.isStd()){
            String stdText="";
            for(int i=0; i<patient.getStds().size(); i++){
                stdText=stdText+", "+patient.getStds().get(i);
            }
            stds.setText(stdText);
        }
        cancer.setChecked(patient.isCancer());
        if(patient.isCancer()){
            String cancerText="";
            for (int i=0; i<patient.getCancers().size(); i++){
                cancerText=cancerText+", "+patient.getCancers().get(i);
            }
            cancers.setText(cancerText);
        }
        other.setChecked(patient.isOther());
        if(patient.isOther()){
            String otherText="";
            for(int i=0; i<patient.getOthers().size(); i++){
                otherText=otherText+", "+patient.getOthers().get(i);
            }
            others.setText(otherText);
        }
    }

    private void setDisabled(){
        diabetes.setEnabled(false);
        kidney.setEnabled(false);
        stroke.setEnabled(false);
        tuberculosis.setEnabled(false);
        arrythmia.setEnabled(false);
        eye.setEnabled(false);
        blood.setEnabled(false);
        hepatitis.setEnabled(false);
        depression.setEnabled(false);
        artery.setEnabled(false);
        std.setEnabled(false);
        asthma.setEnabled(false);
        thyroid.setEnabled(false);
        emphysema.setEnabled(false);
        heart.setEnabled(false);
        hAttack.setEnabled(false);
        seizures.setEnabled(false);
        cancer.setEnabled(false);
        other.setEnabled(false);
        eyes.setEnabled(false);
        cancers.setEnabled(false);
        stds.setEnabled(false);
        others.setEnabled(false);
    }
}
