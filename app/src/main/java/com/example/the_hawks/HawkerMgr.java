package com.example.the_hawks;

import java.util.ArrayList;

public class HawkerMgr {
    private ArrayList<HawkerCenter> HCList = new ArrayList<>();
    private ArrayList<HawkerCenter> filteredList = new ArrayList<>();

    public HawkerMgr(HCList hclist){
        HCList = hclist.getHCList();
    }
    //choice is the filter type decided by user
    public ArrayList<HawkerCenter> searchHawkerCentre(Object input, int choice) {
        ArrayList<HawkerCenter> filteredHCList = new ArrayList<>();
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
    private ArrayList<HawkerCenter> filterByType(Object input_type) {
        HawkerCenter hc;
        for (int i=0; i < HCList.size();i++){
            hc = HCList.get(i);
            if (input_type.equals(hc.enumType)){
                filteredList.add(hc);
            }
        }
        return filteredList;
    }


    private ArrayList<HawkerCenter> filterByHygieneAggregate(float aggregate) {
        HawkerCenter hc;
        for (int i=0; i < HCList.size();i++){
            hc = HCList.get(i);
            if (aggregate == hc.getAggregate()){
                filteredList.add(hc);
            }
        }
        return filteredList;

    }
    private ArrayList<HawkerCenter> filterByName(String name) {
        HawkerCenter hc;
        for (int i=0; i < HCList.size();i++){
            hc = HCList.get(i);
            //case sensitive
            if (name.toLowerCase().contains(hc.getName().toLowerCase())){
                filteredList.add(hc);
            }
        }
        return filteredList;
    }
}
