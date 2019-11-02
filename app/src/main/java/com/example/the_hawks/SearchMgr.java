package com.example.the_hawks;


import java.util.ArrayList;

public class SearchMgr {
    private ArrayList<HawkerCentre> HCList = new ArrayList<>();

    //constructor

    public void SearchManager(HCList hclist) {
        HCList = hclist.getHCList();
    }


    public ArrayList<HawkerCentre> getLocation(float xcoord, float ycoord) {
        return HCList;
    }


    public HCList getName(String name){
        new HCList();
        HCList byName;
        for(String HCname: hcList){
            if (HCname.contains(name)){
                byName = (HCList)((HCList)hcList).clone();
                return byName;
            }
        }

    }

    public HCList filterList(float rating, float xcoord, float ycoord){
        return hcList;
    }

    public HawkerCentre selectCentre(String name){
        for(String HCname: hcList){
            if (HCname.contains(name)){
                return hcList.HawkerCentre;
            }
        }
    }

    public HCList getName(String name) {
        new HCList();
        HCList byName;
        for (String HCname : hcList) {
            if (HCname.contains(name)) {
                byName = (HCList) ((HCList) hcList).clone();
                return byName;
            }
        }
    }

    public HCList filterList(float rating, float xcoord, float ycoord) {
        return hcList;
    }

    public HawkerCentre selectCentre(String name) {
        for (String HCname : hcList) {
            if (HCname.contains(name)) {
                return hcList.HawkerCentre;
            }
        }

        public void selectNavigation(boolean walk, boolean transit, boolean drive){
            
        }
    }
}
