package com.example.the_hawks.Stalls;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.FragmentActivity;


import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.the_hawks.HawkerCentre;
import com.example.the_hawks.HawkerStall;
import com.example.the_hawks.MainActivity;
import com.example.the_hawks.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

public class Stalls extends FragmentActivity {

    String jString = new String("{\"stallsname\" : \"Jurong West Hawker Centre & Market\",\"stalls\":[{\"ic\":\"01\",\"name\":\"Chicken Rice\",\"rating\":\"3.5\"},{\"ic\":\"02\",\"name\":\"Western\",\"rating\":\"3.0\"}]}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stalls);

        final ImageButton stalls = findViewById(R.id.back_button);
        stalls.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backtoHome();
            }
        });

        final ArrayList<HawkerStall> hawkerStall = getIntent().getParcelableArrayListExtra("HawkerStalls");
        Bundle dataBundle = new Bundle();
        dataBundle.putParcelableArrayList("HawkerStalls", hawkerStall);
        stallsFragmentCreate(savedInstanceState, dataBundle);
    }

    public void backtoHome () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void stallsFragmentCreate (Bundle savedInstanceState, Bundle stallsData){
        if (findViewById(R.id.stall_layout) != null) {
            if (savedInstanceState != null) {
                return;
            }
            stallsFragment firstFragment = new stallsFragment();
            firstFragment.setArguments(stallsData);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.stall_layout, firstFragment).commit();
        }
    }
}
