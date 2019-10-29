package com.example.the_hawks;
import java.util.*;

    enum type{
        MHC,
        HC
    }

public class HawkerCentre {
    String name;
    String address;
    int stallCount;
    float aggregate;
    type type;



    //constructor
    public HawkerCentre(String name, String address, int stallCount, float aggregate, type type){
        this.name = name;
        this.address = address;
        this.stallCount = stallCount;
        this.aggregate = aggregate;
        this.type = type;
    }



    int getStallCount(){
        return stallCount;
    }

    String getName(){
        return name;
    }

    String getAddress(){
        return address;
    }

    type getType(){
        return type;
    }

    float getAggregate(){
        return aggregate;
    }



}
