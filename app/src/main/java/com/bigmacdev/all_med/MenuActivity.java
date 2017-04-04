package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button personalInfo, medHist;
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bundle = this.getIntent().getExtras();

        medHist = (Button)findViewById(R.id.medicalHistoryBtn);
        personalInfo = (Button)findViewById(R.id.personalInfoBtn);

        medHist.setOnClickListener(medHistClick);

        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(MenuActivity.this, ViewPersonalInfo.class);
                startActivity(intent);
            }
        });

    }

    private View.OnClickListener medHistClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtras(bundle);
            intent.setClass(MenuActivity.this, PatientHistoryMenu.class);
            startActivity(intent);
        }
    };
}
