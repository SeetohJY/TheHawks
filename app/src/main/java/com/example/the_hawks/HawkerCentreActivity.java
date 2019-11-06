package com.example.the_hawks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.the_hawks.Stalls.Stalls;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HawkerCentreActivity extends AppCompatActivity {

    //Delete after use


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hawker_centre);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final HawkerCentre hc = getIntent().getExtras().getParcelable("HawkerCentreActivity");

        TextView tv1 = findViewById(R.id.hcTitle);
        tv1.setText(hc.getName());

        TextView tv2 = findViewById((R.id.CRating));
        tv2.setText(Double.toString(hc.getAggregate()));

        Button stalls = findViewById(R.id.startStalls);
        stalls.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startStalls(hc.getList());
            }
        });

    }

    public void startStalls (ArrayList<HawkerStall> stalls) {

        Intent intent = new Intent(this, Stalls.class).putParcelableArrayListExtra("HawkerStalls",stalls);

        startActivity(intent);

    }
}
