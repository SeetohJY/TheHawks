package com.example.the_hawks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.the_hawks.Stalls.Stalls;

import java.util.ArrayList;

public class HawkerCentreActivity extends AppCompatActivity {

    //Delete after use


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hawker_centre);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        final ImageButton back = findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backtoHome();
            }
        });



        final HawkerCentre hc = getIntent().getExtras().getParcelable("HawkerCentreActivity");

        TextView tv1 = findViewById(R.id.hcTitle);
        tv1.setText(hc.getName());

        TextView tv2 = findViewById((R.id.CRating));
        tv2.setText(Double.toString(hc.getAggregate()));

        TextView tv3 = findViewById((R.id.Addr));
        tv3.setText(hc.getAddress());

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

    public void backtoHome () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
