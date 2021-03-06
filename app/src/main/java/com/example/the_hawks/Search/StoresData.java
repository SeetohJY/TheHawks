//package com.example.the_hawks.Search;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class StoresData {
//    private static List<String> stores ;
//    static {
//        stores =  new ArrayList<String>();
//        stores.add("Jurong West Hawker Centre");//, new LatLng(1.3412229999999998, 103.697374));
//        stores.add("Jurong West Street 52 Blk 505"); //, new LatLng(1.34969997,103.7184601)
//
//    }
//
//    public static List<String> getStores(){
//        return stores;
//    }
//
//    public static List<String> filterData(String searchString){
//        List<String> searchResults =  new ArrayList<String>();
//        if(searchString != null){
//            searchString = searchString.toLowerCase();
//
//            for(String rec :  stores){
//                if(rec.toLowerCase().contains(searchString)){
//                    searchResults.add(rec);
//                }
//            }
//        }
//        return searchResults;
//    }
//}

package com.example.the_hawks.Search;

import android.content.Context;
import android.util.Log;

import com.example.the_hawks.MainActivity;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StoresData {
    private static Context context;
    private static HashMap<String, LatLng> centres;
    private static WeakReference<MainActivity> mActivityRef;
    public StoresData() {

    }
    public void onCreate() {
        this.centres = createLocationHashMap();
        MainActivity activity = mActivityRef.get();
        String geoJSONString = activity.getGeoJSON();
        Log.e("done", geoJSONString);

    }

    static {
        centres = new HashMap<String, LatLng>();
        createLocationHashMap();
    }
//    protected String getJSONString(){
//        String json = null;
//        try {
//
//            InputStream is = getResources().openRawResource(
//                    getResources().getIdentifier("hawkercentre",
//                            "raw", getPackageName()));
//
//            int size = is.available();
//
//            byte[] buffer = new byte[size];
//
//            is.read(buffer);
//
//            is.close();
//
//            json = new String(buffer, "UTF-8");
//
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            Log.e("failure", ex.toString());
//            return null;
//        }
//        return json;
//    }
    public static void loadJSONFromAsset() {
        String json = null;
        try {
            //File file = new File("../../../res/raw/hc_geojson.geojson");
            InputStream is = context.getResources().openRawResource(
                    context.getResources().getIdentifier("hc_geojson",
                            "raw", context.getPackageName()));

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (Exception ex) {

            ex.printStackTrace();
            return;
        }




    }


    public static HashMap<String, LatLng> createLocationHashMap() {
        HashMap<String, LatLng> result = new HashMap<String, LatLng>();
        try {
            //String JSON_String = loadJSONFromAsset(context);
            JSONObject object = null;
            JSONArray mArray = object.getJSONArray("features");

            for (int i = 0; i < mArray.length(); i++) {

                JSONObject obj = mArray.getJSONObject(i);

                String strToSearch = obj.getJSONObject("properties").getString("Description");
                Pattern pattern = Pattern.compile("<th>NAME<\\/th> <td>(.*?)<\\/td> <\\/tr>", Pattern.DOTALL);
                Matcher matcher = pattern.matcher(strToSearch);
                String HCname = matcher.group(1);



                JSONObject geometryObject = obj.getJSONObject("geometry");
                JSONArray locationJSON_Array = geometryObject.getJSONArray("coordinates");
                LatLng HCcoord = new LatLng(locationJSON_Array.getDouble(0), locationJSON_Array.getDouble(1));

                result.put(HCname, HCcoord);
            }

        } catch (Exception ex) {
           // Log.d("ash123", "failed");
            ex.printStackTrace();
            //handle Exception
        }
        return result;
    }



    public static HashMap<String, LatLng> getCentres(){
        return centres;
    }

    public static List<String> filterData(String searchString){
        List<String> searchResults =  new ArrayList<>();
        if(searchString != null){
            searchString = searchString.toLowerCase();

            for(String rec :  centres.keySet()){
                if(rec.toLowerCase().contains(searchString)){
                    searchResults.add(rec);
                }
            }
        }
        return searchResults;
    }
}



//    public static List<String> filterData(String searchString){
//        HashMap<String, LatLng> searchResults =  new HashMap<String, LatLng>();
//        if(searchString != null){
//            searchString = searchString.toLowerCase();
//
//            for(String rec :  centres.keySet()){
//                if(rec.toLowerCase().contains(searchString)){
//                    searchResults.put(rec, centres.get(rec));
//                }
//            }
//        }
//        return searchResults;
//    }
