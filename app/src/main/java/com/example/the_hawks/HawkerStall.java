package com.example.the_hawks;

public class HawkerStall {
    private String stallName;
    private String hygieneRating;
    private int demeritPoints;
    private String address;
    //private float googleRating;

    //constructor
    public HawkerStall(String stallName, String hygieneRating, int demeritPoints, String address){
        this.stallName = stallName;
        this.hygieneRating = hygieneRating;
        this.demeritPoints = demeritPoints;
        this.address = address;
        //float googleRating;
    }

    public String getHygieneRating() { return hygieneRating;}

    public int getDemeritPoints() { return demeritPoints;}

    public String getAddress() { return address;}

    // public float getGoogleRating(){ return googleRating;}

    public String getStallName() { return stallName;}

}