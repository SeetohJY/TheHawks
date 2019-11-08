package com.example.the_hawks.Stalls;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.fragment.app.FragmentActivity;

import com.example.the_hawks.HawkerStall;
import com.example.the_hawks.MainActivity;
import com.example.the_hawks.R;

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

    private void backtoHome () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void stallsFragmentCreate (Bundle savedInstanceState, Bundle stallsData){
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
