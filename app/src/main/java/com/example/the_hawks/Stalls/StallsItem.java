package com.example.the_hawks.Stalls;

public class StallsItem {
    private String mStallName;
    private String mStallCleanliness;

    public StallsItem(String stallname, String stallcleanliness) {
        mStallName = stallname;
        mStallCleanliness = stallcleanliness;
    }

    public String getStallName() {
        return mStallName;
    }

    public String getStallCleanliness() {
        return mStallCleanliness;
    }
}