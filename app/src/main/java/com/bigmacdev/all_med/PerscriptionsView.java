package com.bigmacdev.all_med;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bigmacdev.all_med.dummy.DummyContent;

public class PerscriptionsView extends AppCompatActivity implements perscriptionFragment.OnListFragmentInteractionListener{

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perscriptions_view);

        back = (Button)findViewById(R.id.backPerscriptionsList);

        back.setOnClickListener(backClick);
    }

    @Override
    public void onListFragmentInteraction(Perscription item) {

    }

    private View.OnClickListener backClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(PerscriptionsView.this, MenuActivity.class);
            startActivity(intent);
        }
    };
}
