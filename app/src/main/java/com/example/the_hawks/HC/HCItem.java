package com.example.the_hawks.HC;

public class HCItem {
    private int mImageResource;
    private String mHCName;
    private String mHCAddress;
    private String mHCCleanliness;

    public HCItem(String hcname, String hcaddress, String hccleanliness) {
        mHCName = hcname;
        mHCAddress = hcaddress;
        mHCCleanliness = hccleanliness;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getHCName() {
        return mHCName;
    }

    public String getHCAddress() {
        return mHCAddress;
    }

    public String getHCCleanliness() {
        return mHCCleanliness;
    }
}
