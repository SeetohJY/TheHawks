package com.example.the_hawks;

public class StallsItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public StallsItem(String text1, String text2) {
        mText1 = text1;
        mText2 = text2;
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
}