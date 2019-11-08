package com.example.the_hawks.HC;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.the_hawks.HawkerCentre;
import com.example.the_hawks.MainActivity;
import com.example.the_hawks.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HC extends FragmentActivity implements AdapterView.OnItemSelectedListener{


    private Spinner spinner;
    private Spinner spinner2;
    private int spinnerPos;
    private static final String[] paths = {"Hygiene (Best to Worst)","Name (A to Z)"};

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
        filteredHCList = activity.getData();
        HCFragment.updateActivity(this);
        setContentView(R.layout.activity_hc);


        //Search bar implementation in HC List
        final SearchView sv = findViewById(R.id.searchView);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("Query",sv.getQuery().toString());
                ArrayList<HawkerCentre> tempList = new ArrayList<>();
                Log.e("original",hcList.toString());
                for(HawkerCentre HC: hcList){
                    if (HC.getName().toLowerCase().contains(sv.getQuery().toString().toLowerCase())){
                        tempList.add(HC);
                    }
                }
                filteredHCList = tempList;
                filteredHCList = searchHawkerCentre(spinnerPos);
                Log.e("After", filteredHCList.toString());

                fragmentChange();

                return false;
            }
        });

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HC.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final ImageButton hc = findViewById(R.id.back_button);

        hc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backtoHome();
            }
        });

        if (findViewById(R.id.hc_layout) != null) {
            createDynamicHCFragment(savedInstanceState);
        }

    }

    public void backtoHome () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createDynamicHCFragment(Bundle savedInstanceState){
        HCFragment hc = HCFragment.newInstance();
        //Add fragment to relative layout by using layout id
        getSupportFragmentManager().beginTransaction().add(R.id.hc_layout, hc).commit();
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

    //Selecting an item on dropdown filter
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        filteredHCList = searchHawkerCentre(pos);
        spinnerPos = pos;
        filteredHCList = this.getData();
        HCFragment.updateActivity(this);
        Log.e("a", "filteredHCList is called by listener");
        Log.e("a", filteredHCList.toString());
        fragmentChange();

    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    //Sorting items by Alphabetical order on dropdown filter
    public static Comparator<HawkerCentre> HCname = new Comparator<HawkerCentre>() {
        public int compare(HawkerCentre h1, HawkerCentre h2) {
            String h1name = h1.getName().toLowerCase();
            String h2name = h2.getName().toLowerCase();

            //Sort HCList by ascending order A-Z
            return h1name.compareTo(h2name);
        }
    };

    //Sorting items by Hygiene Aggregate (best to worst) on dropdown filter
    public static Comparator<HawkerCentre> HCaggbtw = new Comparator<HawkerCentre>() {
        public int compare(HawkerCentre h1, HawkerCentre h2) {
            double h1agg = h1.getAggregate();
            double h2agg = h2.getAggregate();

            if (Double.compare(h1agg, h2agg) == 0){
                return 0;
            } else {
                return -Double.compare(h1agg, h2agg);
            }
        }
    };

    //choice is the filter type decided by user
    public ArrayList<HawkerCentre> searchHawkerCentre(int choice) {
        ArrayList<HawkerCentre> input = filteredHCList;
        Log.d("a", filteredHCList.toString());

        if (choice == 0) { //to filter by agg
            Log.d("a", "filter by agg");

            Collections.sort(input, HC.HCaggbtw);
            Log.d("a", input.toString());
            return input;
        }

        else if (choice == 1) { // to filter by name
            Log.d("a", "filter by name");
            Collections.sort(input, HC.HCname);
            Log.d("a", input.toString());
            return input;
        }

        else
        Log.d("a", "no filter applied");
        return input;
    }


    public ArrayList<HawkerCentre> getData() {
        Log.e("a", filteredHCList.toString());
        return filteredHCList;

    }

    //Replacing of fragments (i.e. individual HC cards in the HC List) to sort
    public void fragmentChange(){
        HCFragment newFragment = new HCFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.hc_layout, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

}

