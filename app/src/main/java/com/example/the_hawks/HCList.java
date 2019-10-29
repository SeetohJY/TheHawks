package com.example.the_hawks;


import java.util.ArrayList;

/**
 * Contains the list of Hawker Centres and all additional related information after
 * data cleaning.
 * All details about hawker centres will be called from this class.
 */
public class HCList {
    private ArrayList<HawkerCenter> HawkerCenterList = new ArrayList<>();
    public HCList(){

    }

    public void addHawkerCentre(ArrayList<HawkerCenter> HawkerCentreList, HawkerCenter HCDetails){
    }

    public ArrayList<HawkerCenter> getHCList(){
        HawkerCenter hc1 = new HawkerCenter("A", "Addr1", 10, 10);
        hc1.setEnum("HC");
        HawkerCenter hc2 = new HawkerCenter("B", "Addr2", 20, 20);
        hc2.setEnum("HC");
        HawkerCenter hc3 = new HawkerCenter("C", "Addr3", 30, 30);
        hc3.setEnum("HC");
        HawkerCenterList.add(hc1);
        HawkerCenterList.add(hc2);
        HawkerCenterList.add(hc3);
        return HawkerCenterList;

    }
}
