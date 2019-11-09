package com.example.the_hawks;

import android.os.Parcel;
import android.os.Parcelable;

public class HawkerStall implements Parcelable {
    private String stallName;
    private String hygieneRating;
    private int demeritPoints;
    private String address;

    //constructor
    public HawkerStall(String stallName, String hygieneRating){
        this.stallName = stallName;
        this.hygieneRating = hygieneRating;
        this.demeritPoints = demeritPoints;
        this.address = address;
    }

    protected HawkerStall(Parcel in) {
        stallName = in.readString();
        hygieneRating = in.readString();
        demeritPoints = in.readInt();
        address = in.readString();
    }

    public static final Creator<HawkerStall> CREATOR = new Creator<HawkerStall>() {
        @Override
        public HawkerStall createFromParcel(Parcel in) {
            return new HawkerStall(in);
        }

        @Override
        public HawkerStall[] newArray(int size) {
            return new HawkerStall[size];
        }
    };

    public String getHygieneRating() { return hygieneRating;}


    public String getStallName() { return stallName;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stallName);
        dest.writeString(hygieneRating);
        dest.writeInt(demeritPoints);
        dest.writeString(address);
    }
}