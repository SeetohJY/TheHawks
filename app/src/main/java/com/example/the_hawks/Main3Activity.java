package com.example.the_hawks;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


import com.example.the_hawks.HC.HC;
import com.example.the_hawks.Stalls.Stalls;
import com.example.the_hawks.NearbyHC.NearbyHC;
import com.example.the_hawks.Main2Activity;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.MenuItem;
import android.util.Log;

import com.example.the_hawks.Maps.MapsActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static com.google.android.material.navigation.NavigationView.*;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.nav_hc:{
                        Log.d("A", "steve");
                        openHC();
                        break;
                    }

                    case R.id.nav_map:{
                        openMapsActivity();
                        break;
                    }
                }

                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.e("E", "stfaeve");
        switch(menuItem.getItemId()) {

            case R.id.nav_hc:{
                Log.d("A", "steve");
                openHC();
                break;
            }

            case R.id.nav_map:{
                openMapsActivity();
                break;
            }
        }

        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openHC() {
        Intent intent = new Intent(this, HawkerCentreActivity.class);
        startActivity(intent);
    }
}
