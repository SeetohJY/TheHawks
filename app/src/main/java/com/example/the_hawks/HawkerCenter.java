package com.example.the_hawks;

public class HawkerCenter {
    String name;
    String address;
    int stallCount;
    float aggregate;

    //constructor
    public HawkerCenter(String name, String address, int stallCount, float aggregate){
        this.name = name;
        this.address = address;
        this.stallCount = stallCount;
        this.aggregate = aggregate;
    }

    enum type{
        MHC,
        HC
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

    }

    float getAggregate(){
        return aggregate;
    }



}
