package com.example.the_hawks;

import java.util.ArrayList;

public class SearchMgr {
    private ArrayList<HawkerCenter> hcList = new ArrayList<HawkerCenter>();
    //  private HawkerCentre hawkerCentre;
    public void SearchManager(){
        this.hcList = hcList.getHCList();
    }


    public HCList getLocation(float xcoord, float ycoord){
        return hcList;
    }

    public HCList getName(String name){
        return hcList;
    }

    public HCList filterList(float rating, float xcoord, float ycoord){
        return hcList;
    }

    public HawkerCenter selectCentre(String name){
        return hcList;
    }

    public void selectNavigation(boolean walk, boolean transit, boolean drive){

    }
}
