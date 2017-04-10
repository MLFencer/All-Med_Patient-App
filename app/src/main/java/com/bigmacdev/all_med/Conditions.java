package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Conditions extends AppCompatActivity {

    private CheckedTextView diabetes, kidney, stroke, tuberculosis, arrythmia, eye, blood, hepatitis, depression, artery, std, asthma, thyroid, emphysema, heart, hAttack, seizures, cancer, other;
    private EditText cancers, stds, eyes, others;
    private Button back, edit;

    private Patient patient;

    private boolean editing;
    private Boolean done;
    private ArrayList<String> changes = new ArrayList<String>();
    private ArrayList<String> typesCancer = new ArrayList<String>();
    private ArrayList<String> typesStd = new ArrayList<String>();
    private ArrayList<String> typesEye = new ArrayList<String>();
    private ArrayList<String> typeOther = new ArrayList<String>();


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

        cancer.setOnClickListener(cancerClick);
        std.setOnClickListener(stdClick);
        eye.setOnClickListener(eyeClick);
        other.setOnClickListener(otherClick);
        diabetes.setOnClickListener(diabetesClick);
        kidney.setOnClickListener(kidneyClick);
        tuberculosis.setOnClickListener(tuberClick);
        arrythmia.setOnClickListener(arryClick);
        blood.setOnClickListener(bloodClick);
        hepatitis.setOnClickListener(hepClick);
        depression.setOnClickListener(depClick);
        artery.setOnClickListener(artClick);
        asthma.setOnClickListener(astmaClick);
        thyroid.setOnClickListener(thyroidClick);
        emphysema.setOnClickListener(empClick);
        heart.setOnClickListener(heartClick);
        hAttack.setOnClickListener(attackClick);
        seizures.setOnClickListener(seizuresClick);

        loadData();
        setDisabled();
    }

    private View.OnClickListener seizuresClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            seizures.setChecked(!seizures.isChecked());
        }
    };
    private View.OnClickListener attackClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hAttack.setChecked(!hAttack.isChecked());
        }
    };
    private View.OnClickListener heartClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            heart.setChecked(!heart.isChecked());
        }
    };
    private View.OnClickListener empClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            emphysema.setChecked(!emphysema.isChecked());
        }
    };
    private View.OnClickListener thyroidClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            thyroid.setChecked(!thyroid.isChecked());
        }
    };
    private View.OnClickListener astmaClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            asthma.setChecked(!asthma.isChecked());
        }
    };
    private View.OnClickListener artClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            artery.setChecked(!artery.isChecked());
        }
    };
    private View.OnClickListener depClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            depression.setChecked(!depression.isChecked());
        }
    };
    private View.OnClickListener hepClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hepatitis.setChecked(!hepatitis.isChecked());
        }
    };
    private View.OnClickListener bloodClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            blood.setChecked(!blood.isChecked());
        }
    };
    private View.OnClickListener kidneyClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            kidney.setChecked(!kidney.isChecked());
        }
    };
    private View.OnClickListener tuberClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tuberculosis.setChecked(!tuberculosis.isChecked());
        }
    };
    private View.OnClickListener arryClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            arrythmia.setChecked(!arrythmia.isChecked());
        }
    };

    private View.OnClickListener diabetesClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            diabetes.setChecked(!diabetes.isChecked());
        }
    };
    private View.OnClickListener cancerClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cancer.setChecked(!cancer.isChecked());
            if(cancer.isChecked()){
                cancers.setEnabled(true);
            }else{
                cancers.setEnabled(false);
            }

        }
    };

    private View.OnClickListener stdClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            std.setChecked(!std.isChecked());
            if(std.isChecked()){
                stds.setEnabled(true);
            }else{
                stds.setEnabled(false);
            }
        }
    };

    private View.OnClickListener eyeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            eye.setChecked(!eye.isChecked());
            if(eye.isChecked()){
                eyes.setEnabled(true);
            }else{
                eyes.setEnabled(false);
            }
        }
    };

    private View.OnClickListener otherClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            other.setChecked(!other.isChecked());
            if(other.isChecked()){
                others.setEnabled(true);
            }else{
                others.setEnabled(false);
            }
        }
    };

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
                if(eye.isChecked()){
                    String string = eyes.getText().toString();
                    string=string.replaceAll("  "," ");
                    string=string.replaceAll(", ",",");
                    string=string.replaceAll(" ,",",");
                    int x = string.indexOf(",");
                    while(string.length()!=x){
                        x = string.indexOf(",");
                        if(x<=0){
                            x=string.length();
                        }
                        typesEye.add(string.substring(0,x));
                        if(x!=string.length()){
                            string=string.substring(x+1, string.length());
                        }
                    }
                }
                if(cancer.isChecked()){
                    String string = cancers.getText().toString();
                    string=string.replaceAll("  "," ");
                    string=string.replaceAll(", ",",");
                    string=string.replaceAll(" ,",",");
                    int x = string.indexOf(",");
                    while(string.length()!=x){
                        x = string.indexOf(",");
                        if(x<=0){
                            x=string.length();
                        }
                        typesCancer.add(string.substring(0,x));
                        if(x!=string.length()){
                            string=string.substring(x+1, string.length());
                        }
                    }
                }
                if(std.isChecked()){
                    String string = stds.getText().toString();
                    string=string.replaceAll("  "," ");
                    string=string.replaceAll(", ",",");
                    string=string.replaceAll(" ,",",");
                    int x = string.indexOf(",");
                    while(string.length()!=x){
                        x = string.indexOf(",");
                        if(x<=0){
                            x=string.length();
                        }
                        typesStd.add(string.substring(0,x));
                        if(x!=string.length()){
                            string=string.substring(x+1, string.length());
                        }
                    }
                }
                if(other.isChecked()){
                    String string = others.getText().toString();
                    string=string.replaceAll("  "," ");
                    string=string.replaceAll(", ",",");
                    string=string.replaceAll(" ,",",");
                    int x = string.indexOf(",");
                    while(string.length()!=x){
                        x = string.indexOf(",");
                        if(x<=0){
                            x=string.length();
                        }
                        typeOther.add(string.substring(0,x));
                        if(x!=string.length()){
                            string=string.substring(x+1, string.length());
                        }
                    }
                }
                
                if(!changeCheck()){
                    patient.setChangedBy(patient.getFullName());
                    patient.setChanges(changes);
                    done =  null;
                    new Thread(){
                        @Override
                        public void run() {
                            done = updatePerson();
                        }
                    }.start();
                    while (done==null){}
                    patient.clearChanges();

                }
            }else{
                editing=true;
                edit.setText("Submit");
                setEnabled();
            }
        }
    };

    private boolean updatePerson(){
        Client client = new Client();
        String output = client.runRequest("update:"+client.encryptData(patient.toJsonString()));
        return Boolean.parseBoolean(output);
    }

    private boolean changeCheck(){
        boolean a1,a2,a3,a4,a5,a6,a7,a8,a9,b1,b2,b3,b4,b5,b6,b7,b8,b9,c1,c2,c3,c4,c5;
        c2=c3=c4=c5=true;
        a1 = patient.isArrythmia()==arrythmia.isChecked();
        if(!a1){
            changes.add("Arrythmia");
            patient.setArrythmia(arrythmia.isChecked());
        }
        a2 = patient.isAsthma()==asthma.isChecked();
        if(!a2){
            changes.add("Asthma");
            patient.setAsthma(asthma.isChecked());
        }
        a3 = patient.isCongestiveHeartFailure()==heart.isChecked();
        if(!a3){
            changes.add("Congestive Heart Failure");
            patient.setCongestiveHeartFailure(heart.isChecked());
        }
        a4 = patient.isCoronaryArteryDisease()==artery.isChecked();
        if(!a4){
            changes.add("Coronary Artery Disease");
            patient.setCoronaryArteryDisease(artery.isChecked());
        }
        a5 = patient.isDepression()==depression.isChecked();
        if(!a5){
            changes.add("Depression");
            patient.setDepression(depression.isChecked());
        }
        a6 = patient.isDiabetes()==diabetes.isChecked();
        if(!a6){
            changes.add("Diabetes");
            patient.setDiabetes(diabetes.isChecked());
        }
        a7 = patient.isEmphasyma()==emphysema.isChecked();
        if(!a7){
            changes.add("Emphasyma");
            patient.setEmphasyma(emphysema.isChecked());
        }
        a8 = patient.isHeartAttack()==hAttack.isChecked();
        if(!a8){
            changes.add("Heart Attack");
            patient.setHeartAttack(hAttack.isChecked());
        }
        a9 = patient.isHepatitis()==hepatitis.isChecked();
        if(!a9){
            changes.add("Hepatitis");
            patient.setHepatitis(hepatitis.isChecked());
        }
        b1 = patient.isHighBloodPressure()==blood.isChecked();
        if(!b1){
            changes.add("High Blood Pressure");
            patient.setHighBloodPressure(blood.isChecked());
        }
        b2 = patient.isKidneyDisease()==kidney.isChecked();
        if(!b2){
            changes.add("Kidney Disease");
            patient.setKidneyDisease(kidney.isChecked());
        }
        b3 = patient.isSeizures()==seizures.isChecked();
        if(!b3){
            changes.add("Seizures");
            patient.setSeizures(seizures.isChecked());
        }
        b4 = patient.isStroke()==stroke.isChecked();
        if(!b4){
            changes.add("Stroke");
            patient.setStroke(stroke.isChecked());
        }
        b5 = patient.isThyroidDisease()==thyroid.isChecked();
        if(!b5){
            changes.add("Thyroid Disease");
            patient.setThyroidDisease(thyroid.isChecked());
        }
        b6 = patient.isTuberculosis()==tuberculosis.isChecked();
        if(!b6){
            changes.add("Tuberculosis");
            patient.setTuberculosis(tuberculosis.isChecked());
        }
        b7 = patient.isEyeProblem()==eye.isChecked();
        if(!b7){
            changes.add("Eye Problem");
            patient.setEyeProblem(eye.isChecked());
        }
        b8 = patient.isCancer()==cancer.isChecked();
        if(!b8){
            changes.add("Cancer");
            patient.setCancer(cancer.isChecked());
        }
        b9 = patient.isStd()==std.isChecked();
        if(!b9){
            changes.add("STD");
            patient.setStd(std.isChecked());
        }
        c1 = patient.isOther()==other.isChecked();
        if(!c1){
            changes.add("Other");
            patient.setOther(other.isChecked());
        }
        if(other.isChecked()) {
            c2 = patient.getOthers().equals(typeOther);
            if(!c2){
                changes.add("Others");
                patient.setOthers(typeOther);
            }
        }
        if(cancer.isChecked()) {
            c3 = patient.getCancers().equals(typesCancer);
            if(!c3){
                changes.add("Cancers");
                patient.setCancers(typesCancer);
            }
        }
        if(eye.isChecked()) {
            c4 = patient.getEyeProblems().equals(typesEye);
            if(!c4){
                changes.add("Eye Problems");
                patient.setEyeProblems(typesEye);
            }
        }
        if(std.isChecked()) {
            c5 = patient.getStds().equals(typesStd);
            if(!c5){
                changes.add("STDs");
                patient.setStds(typesStd);
            }
        }
        return a1 && a2 && a3 && a4 && a5 && a6 && a7 && a8 && a9  && b1 && b2 && b3 && b4 && b5  && b6 && b7 && b8 && b9 && c1  && c2 && c3 && c4 && c5;
    }

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
                if(!eyeText.equals("")) {
                    eyeText = eyeText + ", " + patient.getEyeProblems().get(i);
                }else{
                    eyeText=patient.getEyeProblems().get(i);
                }
            }
            eyes.setText(eyeText);
        }
        std.setChecked(patient.isStd());
        if(patient.isStd()){
            String stdText="";
            for(int i=0; i<patient.getStds().size(); i++){
                if(stdText!="") {
                    stdText = stdText + ", " + patient.getStds().get(i);
                }else{
                    stdText= patient.getStds().get(i);
                }
            }
            stds.setText(stdText);
        }
        cancer.setChecked(patient.isCancer());
        if(patient.isCancer()){
            String cancerText="";
            for (int i=0; i<patient.getCancers().size(); i++){
                if(!cancerText.equals("")) {
                    cancerText = cancerText + ", " + patient.getCancers().get(i);
                }else {
                    cancerText= patient.getCancers().get(i);
                }
            }
            cancers.setText(cancerText);
        }
        other.setChecked(patient.isOther());
        if(patient.isOther()){
            String otherText="";
            for(int i=0; i<patient.getOthers().size(); i++){
                if (!otherText.equals("")) {
                    otherText = otherText + ", " + patient.getOthers().get(i);
                }else{
                    otherText=patient.getOthers().get(i);
                }
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

    private void setEnabled(){
        diabetes.setEnabled(true);
        kidney.setEnabled(true);
        stroke.setEnabled(true);
        tuberculosis.setEnabled(true);
        arrythmia.setEnabled(true);
        eye.setEnabled(true);
        blood.setEnabled(true);
        hepatitis.setEnabled(true);
        depression.setEnabled(true);
        artery.setEnabled(true);
        std.setEnabled(true);
        asthma.setEnabled(true);
        thyroid.setEnabled(true);
        emphysema.setEnabled(true);
        heart.setEnabled(true);
        hAttack.setEnabled(true);
        seizures.setEnabled(true);
        cancer.setEnabled(true);
        other.setEnabled(true);
        if(eye.isChecked()){
            eyes.setEnabled(true);
        }
        if(cancer.isChecked()){
            cancers.setEnabled(true);
        }
        if(std.isChecked()){
            stds.setEnabled(true);
        }
        if(other.isChecked()){
            others.setEnabled(true);
        }
    }
}
