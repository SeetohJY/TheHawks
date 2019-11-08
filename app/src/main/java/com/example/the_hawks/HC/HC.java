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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.the_hawks.HawkerCentre;
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
    private int spinnerPos;
    private static final String[] paths = {"Hygiene (Best to Worst)","Hygiene (Worst to Best)","Name (A to Z)"};
   // private static final String[] paths2 = {"All", "HC", "MHC"};

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


        final SearchView sv = findViewById(R.id.searchView);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            // do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
            // do something when text changes
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
        //spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HC.this,
                android.R.layout.simple_spinner_item,paths);

//        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(HC.this,
//                android.R.layout.simple_spinner_item,paths2);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        //spinner2.setAdapter(adapter2);

//        JSONObject data = createData(jString);

        final ImageButton hc = findViewById(R.id.back_button);
        hc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backtoHome();
            }
        });

//        String HCName = new String();
//        JSONArray hcdata = new JSONArray();
//
//        try {
//            JSONArray sdata = new JSONArray(data.getString("hc"));
//            JSONObject index = sdata.getJSONObject(0);
//
//            HCName = data.getString("hcname");
//            hcdata = new JSONArray(data.getString("hc"));
//
//        } catch(JSONException err){
//            Log.e("Error", err.toString());
//        }

        if (findViewById(R.id.hc_layout) != null) {
            createDynamicHCFragment(savedInstanceState);
        }

//        TextView hcname = findViewById(R.id.HCName);
//        hcname.append(HCName);

    }

    public void backtoHome () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createDynamicHCFragment(Bundle savedInstanceState){
        HCFragment hc = HCFragment.newInstance();
        // adding fragment to relative layout by using layout id
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
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
 //       HawkerMgr.updateHCActivity(this);
//        Intent i = new Intent(HC.this, HCRecyclerViewAdapter.class);
        filteredHCList = searchHawkerCentre(pos);
        spinnerPos = pos;
//       Intent i = new Intent(this,
        filteredHCList = this.getData();
        HCFragment.updateActivity(this);
        Log.e("a", "filteredHCList is called by listener");
        Log.e("a", filteredHCList.toString());
//        i.putExtra("list", searchHawkerCentre(pos));
//        startActivity(i);
        //HawkerMgr.searchHawkerCentre(pos);
        fragmentChange();

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

    public static Comparator<HawkerCentre> HCaggwtb = new Comparator<HawkerCentre>() {
        public int compare(HawkerCentre h1, HawkerCentre h2) {
            double h1agg = h1.getAggregate();
            double h2agg = h2.getAggregate();


            return Double.compare(h1agg, h2agg);
        }
    };

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
        else if (choice == 1) { //to filter by agg
            Log.d("a", "filter by agg");

            Collections.sort(input, HC.HCaggwtb);
            Log.d("a", input.toString());
            return input;
        }
        else if (choice == 2) { // to filter by name
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

