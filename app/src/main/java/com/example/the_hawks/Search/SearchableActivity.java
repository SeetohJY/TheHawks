package com.example.the_hawks.Search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_hawks.MainActivity;
import com.example.the_hawks.Maps.MapsActivity;
import com.example.the_hawks.R;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchableActivity extends AppCompatActivity {
    private ListView listView;
    private CustomSearchAdapter searchAdapter;

    private static HashMap<String, LatLng> centresToCoord;
    private static List<String> centresList;

    private static WeakReference<MainActivity> mActivityRef;
    public static LatLng clickedLocation = null;
    public static String HCclicked = null;

    public static void updateActivity(MainActivity activity){
        mActivityRef = new WeakReference<>(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To retrieve JSON Object from file
        if (mActivityRef != null) {
            MainActivity activity = mActivityRef.get();

            String geoJSONString = activity.getGeoJSON();
            centresToCoord = createLocationHashMap(geoJSONString);
            centresList = createLocationList(geoJSONString);
        }




        setContentView(R.layout.searchable_layout);


        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {
                //execution come here when an item is clicked from
                //the search results displayed after search form is submitted
                //you can continue from here with user clicked search item
                Toast.makeText(SearchableActivity.this,
                        "clicked search result item is"+((TextView)view).getText(),
                        Toast.LENGTH_SHORT).show();
                HCclicked = ((TextView)view).getText().toString();
                LatLng latlngHC = centresToCoord.get(HCclicked);

                clickedLocation = latlngHC;
                Intent intent = new Intent(SearchableActivity.this, MapsActivity.class);
                intent.putExtra("hashMap", centresToCoord);
                startActivity(intent);
            }
        });
        // search
        handleSearch();
    }

    public static HashMap<String, LatLng> createLocationHashMap(String JSONString) {
        HashMap<String, LatLng> result = new HashMap<String, LatLng>();
        try {

            JSONObject object = new JSONObject(JSONString);
            JSONArray mArray = object.getJSONArray("features");


            for (int i = 0; i < mArray.length(); i++) {

                JSONObject obj = mArray.getJSONObject(i);

                String strToSearch = obj.getJSONObject("properties").getString("Description");
                Pattern pattern = Pattern.compile("<th>NAME</th> <td>(.*?)</td>", Pattern.DOTALL);
                Matcher matcher = pattern.matcher(strToSearch);
                if (matcher.find()) {
                }

                String HCname = matcher.group(1);


                JSONObject geometryObject = obj.getJSONObject("geometry");
                JSONArray locationJSON_Array = geometryObject.getJSONArray("coordinates");
                LatLng HCcoord = new LatLng(locationJSON_Array.getDouble(1), locationJSON_Array.getDouble(0));
                Log.i("HCcoord", HCcoord.toString());
                result.put(HCname, HCcoord);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
    public static List<String> createLocationList(String JSONString) {
        List<String> result = new ArrayList<>();
        try {

            JSONObject object = new JSONObject(JSONString);
            JSONArray mArray = object.getJSONArray("features");


            for (int i = 0; i < mArray.length(); i++) {

                JSONObject obj = mArray.getJSONObject(i);

                String strToSearch = obj.getJSONObject("properties").getString("Description");
                Pattern pattern = Pattern.compile("<th>NAME</th> <td>(.*?)</td>", Pattern.DOTALL);
                Matcher matcher = pattern.matcher(strToSearch);
                if (matcher.find()) {
                    Log.d("matcher", matcher.toString());

                }
                String HCname = matcher.group(1);

                result.add(HCname);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            //handle Exception
        }

        return result;
    }

    private void handleSearch() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);

            CustomSearchAdapter adapter = new CustomSearchAdapter(this,
                    android.R.layout.simple_dropdown_item_1line,
                    filterData(searchQuery));
            listView.setAdapter(adapter);

        }else if(Intent.ACTION_VIEW.equals(intent.getAction())) {
            String selectedSuggestionRowId =  intent.getDataString();
            //execution comes here when an item is selected from search SUGGESTIONS
            //you can continue from here with user selected search item
            Toast.makeText(this, "selected search suggestion "+selectedSuggestionRowId,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public static List<String> filterData(String searchString){
        List<String> searchResults =  new ArrayList<>();
        if(searchString != null){
            searchString = searchString.toLowerCase();

            for(String rec :  centresList){
                if(rec.toLowerCase().contains(searchString)){
                    searchResults.add(rec);
                }
            }
        }
        return searchResults;
    }
}
