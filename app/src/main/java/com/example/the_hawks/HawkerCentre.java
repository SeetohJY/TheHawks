
package com.example.the_hawks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class HawkerCentre implements Parcelable {
    private String name;
    private String address;
    private int stallCount;
    private double aggregate;
    public type enumType;
    private ArrayList <HawkerStall> hawkerStalls;

    //constructor
    public HawkerCentre(String name, String address, int stallCount, double aggregate, ArrayList <HawkerStall> hawkerStalls) {
        this.name = name;
        this.address = address;
        this.stallCount = stallCount;
        this.aggregate = aggregate;
        this.hawkerStalls = hawkerStalls;
    }


    private HawkerCentre(Parcel in) {
        hawkerStalls = new ArrayList<>();

        name = in.readString();
        address = in.readString();
        stallCount = in.readInt();
        aggregate = in.readDouble();
        in.readTypedList(hawkerStalls, HawkerStall.CREATOR);
    }

    public static final Creator<HawkerCentre> CREATOR = new Creator<HawkerCentre>() {
        @Override
        public HawkerCentre createFromParcel(Parcel in) {
            return new HawkerCentre(in);
        }

        @Override
        public HawkerCentre[] newArray(int size) {
            return new HawkerCentre[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeInt(stallCount);
        dest.writeDouble(aggregate);
        dest.writeTypedList(hawkerStalls);
    }

    public enum type{
        MHC,
        HC
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

    public double getAggregate(){
        return aggregate;
    }


    public ArrayList<HawkerStall> getList(){
        return hawkerStalls;
    }


}
