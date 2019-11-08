package com.example.the_hawks.HC;

public class HCItem {
    private String mHCName;
    private String mHCAddress;
    private String mHCCleanliness;

    public HCItem(String hcname, String hcaddress, String hccleanliness) {
        mHCName = hcname;
        mHCAddress = hcaddress;
        mHCCleanliness = hccleanliness;
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
