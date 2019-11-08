package com.example.the_hawks.Search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_hawks.Maps.MapsActivity;
import com.example.the_hawks.R;


public class SearchableActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                String TempListViewClickedValue = listValue[position].toString();

                Intent intent = new Intent(SearchableActivity.this, MapsActivity.class);

                // Sending value to another activity using intent.
                intent.putExtra("Latitude", TempListViewClickedValue);
                intent.putExtra("Longitude", TempListViewClickedValue);
                startActivity(intent);
            }
        });
        // search
        handleSearch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent();
        setIntent(intent);
        handleSearch();
    }
    private void handleSearch() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);

            CustomSearchAdapter adapter = new CustomSearchAdapter(this,
                    android.R.layout.simple_dropdown_item_1line,
                    StoresData.filterData(searchQuery));
            listView.setAdapter(adapter);

        }else if(Intent.ACTION_VIEW.equals(intent.getAction())) {
            String selectedSuggestionRowId =  intent.getDataString();
            //execution comes here when an item is selected from search SUGGESTIONS
            //you can continue from here with user selected search item
            Toast.makeText(this, "selected search suggestion "+selectedSuggestionRowId,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
