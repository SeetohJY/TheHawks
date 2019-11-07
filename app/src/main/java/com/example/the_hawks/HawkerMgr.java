package com.example.the_hawks;

import android.content.Intent;

import com.example.the_hawks.HC.HC;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;


public class HawkerMgr {
    private ArrayList<HawkerCentre> HCList = new ArrayList<>();
    private static WeakReference<MainActivity> mActivityRef;
    private static WeakReference<HC> HCActivityRef;

    public static void updateActivity(MainActivity activity) {
        mActivityRef = new WeakReference<>(activity);
    }

    public static void updateHCActivity(HC activity) {
        HCActivityRef = new WeakReference<>(activity);
    }

    public HawkerMgr() {
        MainActivity activity = mActivityRef.get();
        this.HCList = activity.getData();
        Collections.addAll(this.HCList);

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
        ArrayList<HawkerCentre> input = this.HCList;
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
//    private ArrayList<HawkerCentre> filterByType(Collection<HawkerCentre>){
//        HawkerCentre hc;
//        ArrayList<HawkerCentre> filteredList = new ArrayList<>();
//        for (int i=0; i < HCList.size();i++){
//            hc = HCList.get(i);
//            if (input_type.equals(hc.enumType)){
//                filteredList.add(hc);
//            }
//        }
//        return filteredList;
//    }


//    private ArrayList<HawkerCentre> filterByHygieneAggregate(Collection<HawkerCentre> hygiene) {
//        HawkerCentre hc;
//        ArrayList<HawkerCentre> filteredList = new ArrayList<>();
//        for (int i=0; i < HCList.size();i++){
//            hc = HCList.get(i);
//            if (aggregate == hc.getAggregate()){
//                filteredList.add(hc);
//            }
//        }
//        return filteredList;
//
//    }
//    private ArrayList<HawkerCentre> filterByName(String name) {
//        HawkerCentre hc;
//        ArrayList<HawkerCentre> filteredList = new ArrayList<>();
//        for (int i=0; i < HCList.size();i++){
//            hc = HCList.get(i);
//            //case sensitive
//            if (hc.getName().toLowerCase().contains(name.toLowerCase())){
//                filteredList.add(hc);
//            }
//        }
//        return filteredList;
}
