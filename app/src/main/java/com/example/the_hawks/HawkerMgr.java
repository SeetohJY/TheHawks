package com.example.the_hawks;

import java.util.ArrayList;

public class HawkerMgr {
    private ArrayList<HawkerCentre> HCList = new ArrayList<>();

    public HawkerMgr(ArrayList<HawkerCentre> hclist){
        this.HCList = hclist;
    }
    //choice is the filter type decided by user
    public ArrayList<HawkerCentre> searchHawkerCentre(Object input, int choice) {
        ArrayList<HawkerCentre> filteredHCList = new ArrayList<>();
        if(choice == 1){
            filteredHCList = filterByType((Enum)input);
        }
        if (choice == 2){
            filteredHCList = filterByHygieneAggregate((Float)input);
        }
        if (choice == 3){
            filteredHCList = filterByName((String)input);
        }
        return filteredHCList;
    }
    private ArrayList<HawkerCentre> filterByType(Object input_type) {
        HawkerCentre hc;
        ArrayList<HawkerCentre> filteredList = new ArrayList<>();
        for (int i=0; i < HCList.size();i++){
            hc = HCList.get(i);
            if (input_type.equals(hc.enumType)){
                filteredList.add(hc);
            }
        }
        return filteredList;
    }


    private ArrayList<HawkerCentre> filterByHygieneAggregate(float aggregate) {
        HawkerCentre hc;
        ArrayList<HawkerCentre> filteredList = new ArrayList<>();
        for (int i=0; i < HCList.size();i++){
            hc = HCList.get(i);
            if (aggregate == hc.getAggregate()){
                filteredList.add(hc);
            }
        }
        return filteredList;

    }
    private ArrayList<HawkerCentre> filterByName(String name) {
        HawkerCentre hc;
        ArrayList<HawkerCentre> filteredList = new ArrayList<>();
        for (int i=0; i < HCList.size();i++){
            hc = HCList.get(i);
            //case sensitive
            if (hc.getName().toLowerCase().contains(name.toLowerCase())){
                filteredList.add(hc);
            }
        }
        return filteredList;
    }
}
