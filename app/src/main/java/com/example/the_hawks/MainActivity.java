package com.example.the_hawks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.the_hawks.HC.HC;
import com.example.the_hawks.Maps.MapsActivity;
import com.example.the_hawks.Stalls.Stalls;
import com.example.the_hawks.NearbyHC.NearbyHC;
import com.example.the_hawks.Main2Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button buttonToHCList;
    private Button buttonToMap;
    public ArrayList<HawkerCentre> HCList = new ArrayList<HawkerCentre>();
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("count1", Integer.toString(count));
//        setContentView(R.layout.activity_main);

//        initialiseTempData();



//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_recents:
//                        Toast.makeText(MainActivity.this, "Recents", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.action_favorites:
//                        Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.action_nearby:
//                        Toast.makeText(MainActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }
//        });

        Intent test = new Intent(this, LoadingActivity.class);
        startActivity(test);
        Context context = this.getApplicationContext();


        if (HCList.isEmpty()){
            RetrieveDataTask AsyncInitialiseData = new RetrieveDataTask(context);
            AsyncInitialiseData.execute();
            Intent intent = new Intent(this, LoadingActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);

//        Button stalls2 = findViewById(R.id.startStalls2);
//        stalls2.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                startStalls2();
//            }
//        });


//
//        Button nearbyHC = findViewById(R.id.nearby_hc);
//        nearbyHC.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                nearbyHC();
//            }
//        });


//        Button HawkerCentreActivity = findViewById(R.id.HawkerCentreActivity);
//        HawkerCentreActivity.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                Log.e("1st element:", HCList.toString());
//                HawkerCentre hawkerCentre = HCList.get(0);
//                startHawkerCentreActivity(hawkerCentre);
//            }
//        });
//


        // wire buttonToMap
//        Button btn = (Button) findViewById(R.id.buttonToMap);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMapsActivity();
//            }
//        });

//        buttonToHCList = (Button) findViewById(R.id.buttonToHCList);
//        buttonToHCList.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                openHC();
//            }
//        });
//
//        buttonToMap = (Button) findViewById(R.id.buttonToMap);
//        buttonToMap.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                openMap();
//            }
//        });
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.e("Count", Integer.toString(count));

        setContentView(R.layout.activity_main);

//        Button stalls2 = findViewById(R.id.startStalls2);
//        stalls2.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                startStalls2();
//            }
//        });



//        Button nearbyHC = findViewById(R.id.nearby_hc);
//        nearbyHC.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                nearbyHC();
//            }
//        });


//        Button HawkerCentreActivity = findViewById(R.id.HawkerCentreActivity);
//        HawkerCentreActivity.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                HawkerCentre hawkerCentre = HCList.get(0);
//                startHawkerCentreActivity(hawkerCentre);
//            }
//        });
//


        // wire buttonToMap
//        Button btn = (Button) findViewById(R.id.buttonToMap);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMapsActivity();
//            }
//        });

        buttonToHCList = (Button) findViewById(R.id.buttonToHCList);
        buttonToHCList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openHC();
            }
        });

        buttonToMap = (Button) findViewById(R.id.buttonToMap);
        buttonToMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMap();
            }
        });
    }


//    public void toMain(ArrayList<HawkerCentre> data){
//        HCList = data;
//        Intent intent = new Intent(LoadingActivity.this,MainActivity.class).putParcelableArrayListExtra("toMain",HCList);
//        startActivity(intent);
//    }


    public void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
    }

    public void addCount(){
        count = 1;
    }

    public void storeData(ArrayList<HawkerCentre> hclist){
        HCList = hclist;
    }

    public void openHC() {
        Intent intent = new Intent(this, HC.class);
        startActivity(intent);
    }
//
//    public void nearbyHC() {
//        Intent intent = new Intent(this, NearbyHC.class);
//        startActivity(intent);
//    }
//    public void startStalls2 () {
//        Intent intent = new Intent(this, Stalls.class);
//        startActivity(intent);
//    }

    public void openMap () {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void startHawkerCentreActivity (HawkerCentre hawkerCentre) {
        Intent intent = new Intent(MainActivity.this,HawkerCentreActivity.class).putExtra("HawkerCentreActivity",hawkerCentre);
        startActivity(intent);
    }

    // Remove when done
    private void initialiseTempData () {

        HawkerStall hs1 = new HawkerStall("Chicken Rice 1", "C", 10, "22A Lor 7 Toa Pa Yoh");
        HawkerStall hs2 = new HawkerStall("Western 1", "A", 20, "22A Lor 7 Toa Pa Yoh");
        HawkerStall hs3 = new HawkerStall("Chicken Rice 2", "Z", 100, "4 Jelapang Drive");
        HawkerStall hs4 = new HawkerStall("Western 2", "Y", 200, "4 Jelapang Drive");

        ArrayList<HawkerStall> temphslist1 = new ArrayList<>();
        ArrayList<HawkerStall> temphslist2 = new ArrayList<>();

        temphslist1.add(hs1);
        temphslist1.add(hs2);

        temphslist2.add(hs3);
        temphslist2.add(hs4);

        HawkerCentre hc1 = new HawkerCentre("Kim Keat Market","22A Lor 7 Toa Pa Yoh",2,3.0,temphslist1);
        HawkerCentre hc2 = new HawkerCentre("Toh Kim Food Court","4 Jelapang Drive",2,8.0,temphslist2);

        HCList.add(hc1);
        HCList.add(hc2);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    private class RetrieveDataTask extends AsyncTask<Void, Void,  ArrayList<HawkerCentre>> {

        private Exception exception;
        InitMgr DataInitialised;
        ArrayList<HawkerCentre> HCList = new ArrayList<>();
        Context context;

        private RetrieveDataTask(Context context){this.context = context;}

        @Override
        protected ArrayList<HawkerCentre> doInBackground(Void... strings) {

            try{
                DataInitialised = new InitMgr();

            } catch(JSONException | IOException err){
                Log.e("Error Here", err.toString());
            }
            ArrayList<HawkerCentre> hclist = DataInitialised.getHCList();
            return hclist;
        }


        protected void onPostExecute(ArrayList<HawkerCentre> hclist) {
            HCList = DataInitialised.getHCList();
            storeData(HCList);
            addCount();
            Intent intent = new Intent(context,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }


//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.navigation_menu, menu);
//        return true;
//    }


}

//
//        val rollButton = findViewById<Button>(R.id.rollButton);
//        val resultsTextView = findViewById<TextView>(R.id.resultsTextView);
//        val seekBar =  findViewById<SeekBar>(R.id.seekBar);
//
//        rollButton.setOnClickListener {
//            val rand = Random().nextInt(seekBar.progress)
//                    resultsTextView.text =
//        }
