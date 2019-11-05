package com.example.the_hawks.HC;


import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.FragmentActivity;


import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.the_hawks.MainActivity;
import com.example.the_hawks.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class HC extends FragmentActivity {
    String jString = new String(
            "{\"hcname\" : \"Jurong West Hawker Centre & Market\",\"hc\":[{\"ic\":\"01\",\"name\":\"Kim Keat Market\",\"address\":\"22A Lor 7 Toa Pa Yoh\",\"cleanliness\":\"C\"},{\"ic\":\"02\",\"name\":\"Toh Kim Food Court\",\"address\":\"4 Jelapang Drive\",\"cleanliness\":\"A\"}]}");
    private Spinner spinner;
    private Spinner spinner2;
    private static final String[] paths = {"Name (A to Z)", "Name (Z to A)", "Hygiene (Best to Worst)", "Hygiene (Best to Worst)"};
    private static final String[] paths2 = {"All", "HC", "MHC"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hc);

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
        dataBundle.putString("hctext", hcdata.toString());

        hcFragmentCreate(savedInstanceState, dataBundle);

//        TextView hcname = findViewById(R.id.HCName);
//        hcname.append(HCName);

    }

    public void backtoHome () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void hcFragmentCreate (Bundle savedInstanceState, Bundle hcData){
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
