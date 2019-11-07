package com.example.the_hawks.NearbyHC;


import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.FragmentActivity;


import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.the_hawks.NearbyHC.NearbyHC;
import com.example.the_hawks.NearbyHC.NearbyHCFragment;
import com.example.the_hawks.MainActivity;
import com.example.the_hawks.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class NearbyHC extends FragmentActivity {
    String jString = new String(
            "{\"nearbyhcname\" : \"Jurong West Hawker Centre & Market\",\"nearbyhc\":[{\"ic\":\"01\",\"name\":\"Kim Keat Market\",\"address\":\"22A Lor 7 Toa Pa Yoh\",\"cleanliness\":\"C\"},{\"ic\":\"02\",\"name\":\"Toh Kim Food Court\",\"address\":\"4 Jelapang Drive\",\"cleanliness\":\"A\"}]}");
    private Spinner spinner;
    private Spinner spinner2;
    private static final String[] paths = {"Name (A to Z)", "Name (Z to A)", "Hygiene (Best to Worst)", "Hygiene (Best to Worst)"};
    private static final String[] paths2 = {"All", "HC", "MHC"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_hc);

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NearbyHC.this,
                android.R.layout.simple_spinner_item,paths);

        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(NearbyHC.this,
                android.R.layout.simple_spinner_item,paths2);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        JSONObject data = createData(jString);

        final ImageButton nearbyhc = findViewById(R.id.back_button);
        nearbyhc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backtoHome();
            }
        });

        String NearbyHCName = new String();
        JSONArray nearbyhcdata = new JSONArray();

        try {
            JSONArray sdata = new JSONArray(data.getString("nearbyhc"));
            JSONObject index = sdata.getJSONObject(0);

            NearbyHCName = data.getString("nearbyhcname");
            nearbyhcdata = new JSONArray(data.getString("nearbyhc"));

        } catch(JSONException err){
            Log.e("Error", err.toString());
        }

        Bundle dataBundle = new Bundle();
        dataBundle.putString("nearbyhctext", nearbyhcdata.toString());

        nearbyhcFragmentCreate(savedInstanceState, dataBundle);

//        TextView hcname = findViewById(R.id.HCName);
//        hcname.append(HCName);

    }

    public void backtoHome () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void nearbyhcFragmentCreate (Bundle savedInstanceState, Bundle nearbyhcData){
        if (findViewById(R.id.nearby_hc_layout) != null) {
            if (savedInstanceState != null) {
                return;
            }
            NearbyHCFragment firstFragment = new NearbyHCFragment();

//            firstFragment.setArguments(getIntent().getExtras());
            firstFragment.setArguments(nearbyhcData);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.nearby_hc_layout, firstFragment).commit();
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
