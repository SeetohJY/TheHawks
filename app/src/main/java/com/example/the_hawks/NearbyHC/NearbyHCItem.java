package com.example.the_hawks.NearbyHC;

public class NearbyHCItem {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private String mText4;

    public NearbyHCItem(String text1, String text2, String text4) {
        mText1 = text1;
        mText2 = text2;
        mText4 = text4;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public String getText4() {
        return mText4;
    }
}
