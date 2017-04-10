package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button personalInfo, medHist, logout, perscriptions, share;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bundle = this.getIntent().getExtras();

        perscriptions = (Button)findViewById(R.id.perscriptionsMainMenu);
        share = (Button)findViewById(R.id.shareMainMenu);
        logout = (Button)findViewById(R.id.logOutBtn);
        medHist = (Button)findViewById(R.id.medicalHistoryBtn);
        personalInfo = (Button)findViewById(R.id.personalInfoBtn);

        medHist.setOnClickListener(medHistClick);
        logout.setOnClickListener(logoutClick);
        share.setOnClickListener(shareClick);
        perscriptions.setOnClickListener(perscriptionsClick);

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

    private View.OnClickListener shareClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
        }
    };

    private View.OnClickListener perscriptionsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MenuActivity.this, PerscriptionsView.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    private View.OnClickListener logoutClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };

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
