package com.example.the_hawks;

public class SearchMgr {
    private HCList hcList;

    //  private HawkerCentre hawkerCentre;

    //constructor
    public void SearchManager(){
        HCList temp = new HCList();
        this.hcList = temp.getHCList();
    }


    public HCList getLocation(float xcoord, float ycoord){
        return hcList;
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

    public void selectNavigation(boolean walk, boolean transit, boolean drive){

    }
}
