package com.example.the_hawks;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.the_hawks.HC.HC;
import com.example.the_hawks.Maps.MapsActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


public class Main2Activity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dl = (DrawerLayout)findViewById(R.id.activity_main2);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.go_to_maps:
                        Toast.makeText(Main2Activity.this, "Maps",Toast.LENGTH_SHORT).show();
                    case R.id.go_to_hawker_centres:
                        Toast.makeText(Main2Activity.this, "List of Hawker Centres",Toast.LENGTH_SHORT).show();

                    default:
                        return true;
                }




            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.go_to_maps:
                openMaps();
                return true;
            case R.id.go_to_hawker_centres:
                openHC();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
//        if(t.onOptionsItemSelected(item))
//            return true;
//
//        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    public void openHC() {
        Intent intent = new Intent(this, HC.class);
        startActivity(intent);
    }
    public void openMaps() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
