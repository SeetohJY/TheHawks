package com.example.the_hawks.Search;

import java.util.ArrayList;
import java.util.List;

public class StoresData {
    private static List<String> stores ;
    static {
        stores =  new ArrayList<String>();
        stores.add("Jurong West Hawker Centre");//, new LatLng(1.3412229999999998, 103.697374));
        stores.add("Jurong West Street 52 Blk 505"); //, new LatLng(1.34969997,103.7184601)

    }

    public static List<String> getStores(){
        return stores;
    }

    public static List<String> filterData(String searchString){
        List<String> searchResults =  new ArrayList<String>();
        if(searchString != null){
            searchString = searchString.toLowerCase();

            for(String rec :  stores){
                if(rec.toLowerCase().contains(searchString)){
                    searchResults.add(rec);
                }
            }
        }
        return searchResults;
    }
}
//package com.example.the_hawks.Search;
//
//import com.google.android.gms.maps.model.LatLng;
//
//import java.util.HashMap;
//
//public class StoresData {
//    private static HashMap<String, LatLng> centres = new HashMap<String, LatLng>();
//    static {
//        centres.put("Jurong West Hawker Centre", new LatLng(1.3412229999999998, 103.697374));
//        centres.put("Jurong West Street 52 Blk 505", new LatLng(1.34969997,103.7184601));
//
//    }
//
//    public static HashMap<String, LatLng> getCentres(){
//        return centres;
//    }
//
//    public static HashMap<String, LatLng> filterData(String searchString){
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
//}