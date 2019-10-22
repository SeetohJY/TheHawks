package com.example.the_hawks;
public class SearchMgr {
    private HCList hcList;
    //  private HawkerCentre hawkerCentre;
    public void SearchManager(){
        HCList temp = new HCList();
        this.hcList = temp.getHCList();
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

    public HawkerCentre selectCentre(String name){
        return hcList;
    }

    public void selectNavigation(boolean walk, boolean transit, boolean drive){

    }
}
