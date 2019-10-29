package com.example.the_hawks;

public class HawkerCenter {
    String name;
    String address;
    int stallCount;
    float aggregate;
    type enumType;

    //constructor
    public HawkerCenter(String name, String address, int stallCount, float aggregate){
        this.name = name;
        this.address = address;
        this.stallCount = stallCount;
        this.aggregate = aggregate;
    }

    public enum type{
        MHC,
        HC;
    }

    public int getStallCount(){
        return stallCount;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }


    public void setEnum(String value){
        if(value.matches("MHC")){
            enumType = type.MHC;
        }
        if(value.matches("HC")){
            enumType = type.HC;
        }
    }

    float getAggregate(){
        return aggregate;
    }



}
