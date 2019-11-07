package com.example.the_hawks.HC;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;

import com.example.the_hawks.MainActivity;
import com.example.the_hawks.R;

import org.json.JSONArray;
import org.json.JSONException;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONObject;


public class HC extends FragmentActivity implements AdapterView.OnItemSelectedListener{
    String jString = new String(
            "{\"hcname\" : \"Jurong West Hawker Centre & Market\",\"hc\":[{\"ic\":\"01\",\"name\":\"Kim Keat Market\",\"address\":\"22A Lor 7 Toa Pa Yoh\",\"cleanliness\":\"C\"},{\"ic\":\"02\",\"name\":\"Toh Kim Food Court\",\"address\":\"4 Jelapang Drive\",\"cleanliness\":\"A\"}]}");
    private Spinner spinner;
    private Spinner spinner2;
    private static final String[] paths = {"Name (A to Z)", "Name (Z to A)", "Hygiene (Best to Worst)", "Hygiene (Best to Worst)"};
    private static final String[] paths2 = {"All", "HC", "MHC"};

    private ArrayList<HawkerCentre> hcList = new ArrayList<>();
    private ArrayList<HawkerCentre> filteredHCList = new ArrayList<>();
    private static WeakReference<MainActivity> mActivityRef;
    public static void updateActivity(MainActivity activity) {
        mActivityRef = new WeakReference<>(activity);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity = mActivityRef.get();
        hcList = activity.getData();
        HCFragment.updateActivity(this);
        setContentView(R.layout.activity_hc);

        spinner = (Spinner)findViewById(R.id.spinner);
        //spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HC.this,
                android.R.layout.simple_spinner_item,paths);

        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(HC.this,
                android.R.layout.simple_spinner_item,paths2);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        //spinner2.setAdapter(adapter2);

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
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
 //       HawkerMgr.updateHCActivity(this);
//        Intent i = new Intent(HC.this, HCRecyclerViewAdapter.class);
        filteredHCList = searchHawkerCentre(pos);
//        i.putExtra("list", searchHawkerCentre(pos));
//        startActivity(i);
        //HawkerMgr.searchHawkerCentre(pos);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }



    public static Comparator<HawkerCentre> HCname = new Comparator<HawkerCentre>() {
        public int compare(HawkerCentre h1, HawkerCentre h2) {
            String h1name = h1.getName().toLowerCase();
            String h2name = h2.getName().toLowerCase();

            //ascending order A-Z
            return h1name.compareTo(h2name);
        }
    };

    public static Comparator<HawkerCentre> HCagg = new Comparator<HawkerCentre>() {
        public int compare(HawkerCentre h1, HawkerCentre h2) {
            double h1agg = h1.getAggregate();
            double h2agg = h2.getAggregate();

            return Double.compare(h1agg, h2agg);
        }
    };

    //choice is the filter type decided by user
    public ArrayList<HawkerCentre> searchHawkerCentre(int choice) {
        ArrayList<HawkerCentre> input = this.hcList;
        ArrayList<HawkerCentre> filteredHCList = new ArrayList<>();
        if (choice == 1) { //to filter by agg
            Collections.sort(input, HCagg);
            return input;
        }
        if (choice == 2) { // to filter by name
            Collections.sort(input, HCname);
            return input;
        }
        else return input;
    }


    public ArrayList<HawkerCentre> getData() {
        return hcList;
    }
}

