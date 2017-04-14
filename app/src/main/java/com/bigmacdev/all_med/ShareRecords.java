package com.bigmacdev.all_med;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.UiThread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;

public class ShareRecords extends AppCompatActivity {

    private ListView clinics;
    private Button back, web;
    private ArrayList<String> clinicList;
    private Boolean done;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_records);

        Bundle bundle = this.getIntent().getExtras();
        patient = (Patient)bundle.getSerializable("patient");

        clinics = (ListView)findViewById(R.id.clinicList);
        back = (Button)findViewById(R.id.backShare);
        web = (Button)findViewById(R.id.webShare);

        getClinicList();
        ArrayList<String> clean = new ArrayList<String>();
        for (int i=0; i<clinicList.size(); i++){
            clean.add(clinicList.get(i).substring(0,clinicList.get(i).indexOf('_')));
        }
        ArrayAdapter<String> clinicsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clean);

        clinics.setAdapter(clinicsAdapter);
        clinics.setOnItemClickListener(clinicClick);
        back.setOnClickListener(backClick);
        web.setOnClickListener(webClick);
    }

    private View.OnClickListener webClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Thread(){
                @Override
                public void run() {
                    Client client = new Client();
                    Random rand = new Random();
                    final int i =rand.nextInt(100000);
                    client.runRequest("web:"+i);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(ShareRecords.this).setTitle("Alert!")
                                    .setMessage("Your unique temporary code is: "+i)
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //do nothing
                                        }
                                    }).show();
                        }
                    });
                }
            }.start();
        }
    };

    private View.OnClickListener backClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            Bundle b = new Bundle();
            b.putSerializable("patient", patient);
            intent.putExtras(b);
            intent.setClass(ShareRecords.this, MenuActivity.class);
            startActivity(intent);
        }
    };

    private AdapterView.OnItemClickListener clinicClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            done =null;
            final int pos = position;
            new Thread(){
                @Override
                public void run() {
                    Client client = new Client();
                    client.runRequest("access:"+patient.getPath()+";"+clinicList.get(pos));
                    done = true;
                }
            }.start();
            while(done==null){}
        }
    };





    private void getClinicList(){
        done = null;
        new Thread(){
            @Override
            public void run() {
                Client client = new Client();
                String s = client.runRequest("clinics");
                JsonObject jo = Json.createReader(new StringReader(s)).readObject();
                int i =0;
                clinicList= new ArrayList<String>();
                while (true){
                    if(jo.containsKey("file"+i)){
                        clinicList.add(jo.getString("file"+i));
                    }else{
                        break;
                    }
                    i++;
                }
                done = true;
            }
        }.start();
        while (done==null){}
    }
}
