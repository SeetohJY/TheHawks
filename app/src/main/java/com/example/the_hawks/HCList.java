package com.example.the_hawks;


import java.util.ArrayList;

/**
 * Contains the list of Hawker Centres and all additional related information after
 * data cleaning.
 * All details about hawker centres will be called from this class.
 */
public class HCList {
    private ArrayList<HawkerCentre> HawkerCentreList = new ArrayList<>();
    public HCList(){

    }

    public void addHawkerCentre(ArrayList<HawkerCentre> HawkerCentreList, HawkerCentre HCDetails){
    }

    public ArrayList<HawkerCentre> getHCList(){
        HawkerCentre hc1 = new HawkerCentre("A", "Addr1", 10, 10);
        hc1.setEnum("HC");
        HawkerCentre hc2 = new HawkerCentre("B", "Addr2", 20, 20);
        hc2.setEnum("HC");
        HawkerCentre hc3 = new HawkerCentre("C", "Addr3", 30, 30);
        hc3.setEnum("HC");
        HawkerCentreList.add(hc1);
        HawkerCentreList.add(hc2);
        HawkerCentreList.add(hc3);
        return HawkerCentreList;

    }
}
