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
import com.example.the_hawks.Stalls.SearchMgr;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


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
                filteredHCList = SearchMgr.searchHawkerCentre(spinnerPos, filteredHCList);
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

    private void backtoHome () {
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
        filteredHCList = SearchMgr.searchHawkerCentre(pos, filteredHCList);;
        spinnerPos = pos;
        filteredHCList = this.getData();
        HCFragment.updateActivity(this);
        Log.e("a", "filteredHCList is called by listener");
        Log.e("a", filteredHCList.toString());
        fragmentChange();

    }

    public void onNothingSelected(AdapterView<?> parent) {
    }


    public ArrayList<HawkerCentre> getData() {
        Log.e("a", filteredHCList.toString());
        return filteredHCList;

    }

    //Replacing of fragments (i.e. individual HC cards in the HC List) to sort
    private void fragmentChange(){
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

