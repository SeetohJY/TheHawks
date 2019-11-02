
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


    public ArrayList<HawkerCentre> getName(String str){
        ArrayList<HawkerCentre> byName = new ArrayList<>();
        for(HawkerCentre HC: HCList){
            if (str.toLowerCase().contains(HC.getName().toLowerCase())){
                byName.add(HC);
            }
        }
        return (byName);
    }

    public ArrayList<HawkerCentre> filterList(float rating, float xcoord, float ycoord){
        ArrayList<HawkerCentre> temp = new ArrayList<>();
        for(HawkerCentre HC: HCList) {
            if(HC.getAggregate()>= rating) {
                temp.add(HC);
            }
            //add things for coords
        }
        return temp;
    }

    public HawkerCentre selectCentre(String str){
        for(HawkerCentre HC: HCList){
            if (str.toLowerCase().contains(HC.getName().toLowerCase())){
                return HC;
            }
        }
    }


        public void selectNavigation(boolean walk, boolean transit, boolean drive){
            
        }
    }
}

