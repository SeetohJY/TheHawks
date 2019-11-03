package com.example.the_hawks;

import android.content.Intent;
import android.os.Bundle;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Stalls extends FragmentActivity {

    String jString = new String("{\"hcname\" : \"Jurong West Hawker Centre & Market\",\"stalls\":[{\"ic\":\"01\",\"name\":\"Chicken Rice\",\"rating\":\"3.5\"},{\"ic\":\"02\",\"name\":\"Western\",\"rating\":\"3.0\"}]}");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stalls);

        JSONObject data = createData(jString);

        final ImageButton stalls = findViewById(R.id.back_button);
        stalls.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backtoHome();
            }
        });

        String HCName = new String();
        JSONArray stallsdata = new JSONArray();

        try {
            JSONArray sdata = new JSONArray(data.getString("stalls"));
            JSONObject index = sdata.getJSONObject(0);

            HCName = data.getString("hcname");
            stallsdata = new JSONArray(data.getString("stalls"));

        } catch(JSONException err){
            Log.e("Error", err.toString());
        }

        Bundle dataBundle = new Bundle();
        dataBundle.putString("stallstext", stallsdata.toString());

        stallsFragmentCreate(savedInstanceState, dataBundle);

        TextView hcname = findViewById(R.id.HCName);
        hcname.append(HCName);

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

//            firstFragment.setArguments(getIntent().getExtras());
            firstFragment.setArguments(stallsData);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.stall_layout, firstFragment).commit();
        }
    }

    protected JSONObject createData(String jString){
        try {
            JSONObject data = new JSONObject(jString);
            return data;
        } catch(JSONException err){
            Log.d("Error", err.toString());
        }

        return null;
    }



}
