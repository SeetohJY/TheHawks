package com.example.the_hawks;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchMgr {
    public SearchMgr(){
    }
    private static Comparator<HawkerCentre> HCname = new Comparator<HawkerCentre>() {
        public int compare(HawkerCentre h1, HawkerCentre h2) {
            String h1name = h1.getName().toLowerCase();
            String h2name = h2.getName().toLowerCase();

            //Sort HCList by ascending order A-Z
            return h1name.compareTo(h2name);
        }
    };

    //Sorting items by Hygiene Aggregate (best to worst) on dropdown filter
    private static Comparator<HawkerCentre> HCaggbtw = new Comparator<HawkerCentre>() {
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
    public static ArrayList<HawkerCentre> searchHawkerCentre(int choice,ArrayList<HawkerCentre> filteredHCList) {
        ArrayList<HawkerCentre> input = filteredHCList;
        Log.d("a", filteredHCList.toString());

        if (choice == 0) { //to filter by agg
            Log.d("a", "filter by agg");

            Collections.sort(input, HCaggbtw);
            Log.d("a", input.toString());
            return input;
        }

        else if (choice == 1) { // to filter by name
            Log.d("a", "filter by name");
            Collections.sort(input, HCname);
            Log.d("a", input.toString());
            return input;
        }

        else
            Log.d("a", "no filter applied");
        return input;
    }
}
