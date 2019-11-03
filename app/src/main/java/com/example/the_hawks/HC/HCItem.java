package com.example.the_hawks.HC;

public class HCItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public HCItem(String text1, String text2) {
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
