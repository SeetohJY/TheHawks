package com.example.the_hawks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import java.util.ArrayList;
import android.widget.EditText;

public class SearchResultsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExampleItem> mExampleList;

    private Spinner spinner;
    private Spinner spinner2;
    private static final String[] paths = {"Name (A to Z)", "Name (Z to A)", "Hygiene (Best to Worst)", "Hygiene (Best to Worst)"};
    private static final String[] paths2 = {"All", "HC", "MHC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        createExampleList();
        buildRecyclerView();
//
//        ArrayList<ExampleItem> exampleList = new ArrayList<>();
//        exampleList.add(new ExampleItem("Line 1", "Line 2"));
//        exampleList.add(new ExampleItem("Line 3", "Line 4"));
//        exampleList.add(new ExampleItem("Line 5", "Line 6"));
//        exampleList.add(new ExampleItem("Line 7", "Line 8"));
//        exampleList.add(new ExampleItem("Line 9", "Line 10"));
//        exampleList.add(new ExampleItem("Line 11", "Line 12"));
//        exampleList.add(new ExampleItem("Line 13", "Line 14"));
//        exampleList.add(new ExampleItem("Line 15", "Line 16"));
//        exampleList.add(new ExampleItem("Line 17", "Line 18"));
//        exampleList.add(new ExampleItem("Line 19", "Line 20"));
//        exampleList.add(new ExampleItem("Line 21", "Line 22"));
//        exampleList.add(new ExampleItem("Line 23", "Line 24"));
//        exampleList.add(new ExampleItem("Line 25", "Line 26"));
//        exampleList.add(new ExampleItem("Line 27", "Line 28"));
//        exampleList.add(new ExampleItem("Line 29", "Line 30"));



        // Dropdown List
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(SearchResultsActivity.this,
                android.R.layout.simple_spinner_item,paths);

        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(SearchResultsActivity.this,
                android.R.layout.simple_spinner_item,paths2);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
//
//        spinner.setOnItemSelectedListener(this);
    }


    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    public void insertItem(int position) {
        mExampleList.add(position, new ExampleItem("New Item At Position" + position, "This is Line 2"));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem("Line 1", "Line 2"));
        mExampleList.add(new ExampleItem("Line 3", "Line 4"));
        mExampleList.add(new ExampleItem("Line 5", "Line 6"));
    }

    public void changeItem(int position, String text) {
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
//            public void onItemClick(int position) {
//                changeItem(position, "Clicked");
//            }
//        });
    }


}
