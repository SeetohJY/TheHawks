package com.example.the_hawks.Search;

import java.util.ArrayList;
import java.util.List;

public class StoresData {
    private static List<String> stores ;
    static {
        stores =  new ArrayList<String>();
        stores.add("Pioneer Hawker Centre");
        stores.add("Pioneer Hawker Centre 2");
        stores.add("Pioneer Hawker Centre 3");

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