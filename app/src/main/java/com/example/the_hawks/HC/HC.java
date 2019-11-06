package com.example.the_hawks.HC;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.gson.Gson;

import androidx.fragment.app.FragmentActivity;


import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.the_hawks.HawkerCentre;
import com.example.the_hawks.HawkerStall;
import com.example.the_hawks.MainActivity;
import com.example.the_hawks.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class HC extends FragmentActivity {
    String jString = new String(
            "{\"hcname\" : \"Jurong West Hawker Centre & Market\",\"hc\":[{\"ic\":\"01\",\"name\":\"Kim Keat Market\",\"address\":\"22A Lor 7 Toa Pa Yoh\",\"cleanliness\":\"C\"},{\"ic\":\"02\",\"name\":\"Toh Kim Food Court\",\"address\":\"4 Jelapang Drive\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"02\",\"name\":\"Toh Kim Food Court\",\"address\":\"4 Jelapang Drive\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"03\",\"name\":\"Bedok Food Centre\",\"address\":\"1, Bedok Road, S(469572)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"04\",\"name\":\"Maxwell Food Centre\",\"address\":\"1, Kadayanallur Street, S(069184)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"05\",\"name\":\"Holland Village Market & Food Centre\",\"address\":\"1, Lorong Mambong, S(277700)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"06\",\"name\":\"Pasir Panjang Food Centre\",\"address\":\"121, Pasir Panjang Road, S(118543)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"07\",\"name\":\"East Coast Lagoon Food Village\",\"address\":\"1220, East Coast Parkway, S(468960)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"08\",\"name\":\"Berseh Food Centre\",\"address\":\"166, Jalan Besar, S(208877)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"09\",\"name\":\"Kallang Estate Market\",\"address\":\"17, Old Airport Road, S(397972)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"10\",\"name\":\"Chomp Chomp Food Centre\",\"address\":\"20, Kensington Park Road, S(557269)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"11\",\"name\":\"Dunman Food Centre\",\"address\":\"271, Onan Road, S(424768)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"12\",\"name\":\"Taman Jurong Market & Food Centre\",\"address\":\"3, Yung Sheng Road, S(618499)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"13\",\"name\":\"Tiong Bahru Market\",\"address\":\"30, Seng Poh Road, S(168898)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"14\",\"name\":\"Commonwealth Crescent Market\",\"address\":\"31, Commonwealth Crescent, S(149644)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"15\",\"name\":\"Beo Crescent Market\",\"address\":\"38A, Beo Crescent, S(169982)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"16\",\"name\":\"Tanglin Halt Market\",\"address\":\"48A, Tanglin Halt Road, S(148813)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"17\",\"name\":\"Serangoon Garden Market\",\"address\":\"49A, Serangoon Garden Way, S(555945)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"18\",\"name\":\"49A, Serangoon Garden Way, S(555945)\",\"address\":\"50, Market Street, Golden Shoe Multi-Storey Car Park, 2nd/3rd Storey, S(048940)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"19\",\"name\":\"Golden Mile Food Centre\",\"address\":\"505, Beach Road, S(199583)\",\"cleanliness\":\"A\"}" +
                    ",{\"ic\":\"20\",\"name\":\"Bukit Timah Market\",\"address\":\"51, Upper Bukit Timah Road, S(588215)\",\"cleanliness\":\"A\"}]}");
    private Spinner spinner;
    private Spinner spinner2;
    private static final String[] paths = {"Name (A to Z)", "Name (Z to A)", "Hygiene (Best to Worst)", "Hygiene (Best to Worst)"};
    private static final String[] paths2 = {"All", "HC", "MHC"};
    public ArrayList<HawkerCentre> hcList = new ArrayList<>();
    private static WeakReference<MainActivity> mActivityRef;
    public static void updateActivity(MainActivity activity) {
        mActivityRef = new WeakReference<>(activity);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hc);

        MainActivity activity = mActivityRef.get();
        hcList = activity.getData();
        Log.e("Weird Test", hcList.toString());

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HC.this,
                android.R.layout.simple_spinner_item,paths);

        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(HC.this,
                android.R.layout.simple_spinner_item,paths2);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        JSONObject data = createData(jString);

        final ImageButton hc = findViewById(R.id.back_button);
        hc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backtoHome();
            }
        });

        String HCName = new String();
        JSONArray hcdata = new JSONArray();

        try {
            JSONArray sdata = new JSONArray(data.getString("hc"));
            JSONObject index = sdata.getJSONObject(0);

            HCName = data.getString("hcname");
            hcdata = new JSONArray(data.getString("hc"));

        } catch(JSONException err){
            Log.e("Error", err.toString());
        }

        Bundle dataBundle = new Bundle();
        dataBundle.putParcelableArrayList("hclist", hcList);

        HCFragmentCreate(savedInstanceState, dataBundle);

//        TextView hcname = findViewById(R.id.HCName);
//        hcname.append(HCName);

    }

    public void backtoHome () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void HCFragmentCreate (Bundle savedInstanceState, Bundle hcData){
        if (findViewById(R.id.hc_layout) != null) {
            if (savedInstanceState != null) {
                return;
            }
            HCFragment firstFragment = new HCFragment();

//            firstFragment.setArguments(getIntent().getExtras());
            firstFragment.setArguments(hcData);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.hc_layout, firstFragment).commit();
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
