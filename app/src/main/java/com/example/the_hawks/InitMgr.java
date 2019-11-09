package com.example.the_hawks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitMgr {
    private static JSONArray jsonList1 = new JSONArray();
    private static JSONArray jsonList2 = new JSONArray();
    private static ArrayList<HawkerCentre> HawkerCentreList = new ArrayList<>();

    public InitMgr(JSONArray array1, JSONArray array2) throws JSONException, IOException {
        jsonList1 = array1;
        jsonList2 = array2;

        Log.e("jsonList1:", jsonList1.toString());
        Log.e("jsonList2:", jsonList2.toString());
        createHCList();
        Log.e("HCList:", HawkerCentreList.toString());
    }


    private static void createHCList() throws JSONException{
        for (int i =0; i<jsonList1.length(); i++) {
            String name = jsonList1.getJSONObject(i).getString("name_of_centre");
            //Log.e("objects:", name);
            if (i==0) {
                HawkerCentreList.add(createHawkerCentre(i));
            }
            else if (!name.equals(jsonList1.getJSONObject(i-1).getString("name_of_centre"))) {
                HawkerCentreList.add(createHawkerCentre(i));
            }
            //Log.e("List1:", HawkerCentreList.toString());
        }
    }

    public static ArrayList<HawkerCentre> getHCList(){
        return HawkerCentreList;
    }

    private static HawkerCentre createHawkerCentre(int i) throws JSONException{
        String name = jsonList1.getJSONObject(i).getString("name_of_centre");
        String addr;
        int stallCount;
        double aggregate = calculateAggregate(i);
        HawkerCentre hawkerCentre;

        addr = jsonList1.getJSONObject(i).getString("location_of_centre");
        stallCount = Integer.parseInt(jsonList1.getJSONObject(i).getString("no_of_stalls"));
        hawkerCentre = new HawkerCentre(name, addr, stallCount, aggregate, createHawkerStallList(i, name));
        hawkerCentre.setEnum(jsonList1.getJSONObject(i).getString("type_of_centre"));
        return hawkerCentre;

    }

    private static ArrayList<HawkerStall> createHawkerStallList(int i, String name) throws JSONException{
        ArrayList<HawkerStall> HawkerCentreStalls = new ArrayList<>();
        while(jsonList1.getJSONObject(i).getString("name_of_centre").equals(name)) {
            HawkerCentreStalls.add(createHawkerStall(i));
            i++;
            if (i == jsonList1.length()) {
                break;
            }
        }
        return HawkerCentreStalls;
    }

    private static HawkerStall createHawkerStall(int i) throws JSONException{
        HawkerStall hawkerstall;

        String name = getStallName(jsonList2.getJSONObject(i).getString("premises_address"));
        String rating = jsonList2.getJSONObject(i).getString("grade");
        hawkerstall = new HawkerStall(name, rating);
        return hawkerstall;

    }

    private static String getStallName(String name){
        String result = null;
        Pattern stallname_pattern = Pattern.compile("Stall No .+");
        Matcher match = stallname_pattern.matcher(name);
        if (match.find()){
            result = match.group(0);
        }
        return result;
    }


    private static double calculateAggregate(int i) throws JSONException{
        double agg = 0;
        int count = 0;
        String grade;
        String name = jsonList1.getJSONObject(i).getString("name_of_centre");
        while(jsonList1.getJSONObject(i).getString("name_of_centre").equals(name)) {
            grade = jsonList2.getJSONObject(i).getString("grade");
            agg += getScore(grade);
            count++;
            i++;
            if (i == jsonList1.length()) {
                break;
            }
        }
        agg /= count;
        BigDecimal bd = new BigDecimal(agg).setScale(2, RoundingMode.HALF_UP);
        double newAgg = bd.doubleValue();
        return newAgg;

    }

    //Assigning scores depending on grade
    private static double getScore(String grade) {
        double score = 0;
        switch(grade)
        {
            case "A":
                score = 10;
                break;
            case "B":
                score = 7.5;
                break;
            case "C":
                score = 5;
                break;
            case "D":
                score = 2.5;
                break;
        }
        return score;
    }


}

