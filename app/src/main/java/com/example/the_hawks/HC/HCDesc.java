package com.example.the_hawks.HC;

import android.content.Intent;
import android.os.Bundle;

import com.example.the_hawks.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Activity class for information on a specific Hawker Centre,
 * on click of a specific Hawker Centre fragment from the HCList implemented in the RecyclerView
 */
public class HCDesc extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcdesc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");

        TextView text = findViewById(R.id.hcTitle);
        text.setText(Name);

        String Cleanliness = intent.getStringExtra("Cleanliness");
        String Address = intent.getStringExtra("Address");

        TextView cleanlinessText = findViewById(R.id.hcCleanliness);
        cleanlinessText.setText(Cleanliness);

        TextView addressText = findViewById(R.id.hcAddress);
        addressText.setText(Address);

    }

}
