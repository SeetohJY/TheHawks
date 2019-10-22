package com.example.the_hawks;

import org.json.JSONObject;

public class GoogleMapsAPI {
    private String currentLocation;
    private String destination;

    public JSONObject getLocation(String currentLocation) {
        JSONObject location = new JSONObject();
        return location;
    }

    public JSONObject searchNavigation(String address) {
        JSONObject navigation = new JSONObject();
        return navigation;

    }
}
