package com.example.the_hawks;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import com.example.the_hawks.HC.HC;
import com.example.the_hawks.Maps.MapsActivity;
import com.example.the_hawks.NearbyHC.NearbyHC;
import com.example.the_hawks.Stalls.Stalls;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button buttonToHCList;
    private Button buttonToMap;
    private static WeakReference<MainActivity> mActivityRef;
    public ArrayList<HawkerCentre> HCList = new ArrayList<HawkerCentre>();
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("count1", Integer.toString(count));
        checkLocationServices();
        isNetworkConnectionAvailable();
        HawkerMgr.updateActivity(this);
        HC.updateActivity(this);


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

    public void checkLocationServices(){
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch (Exception ex){}
        try{
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch (Exception ex){}
        if(!gps_enabled && !network_enabled){
            checkGPSLocation();
        }
    }

    public void checkGPSLocation(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Location Services Not Enabled");
        builder.setMessage("Please Enable Location Services To Use All Functions In The App.");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    public void checkNetworkConnection(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please Turn On Internet Connection To Continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public boolean isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            Log.d("Network", "Connected");
            return true;
        }
        else{
            checkNetworkConnection();
            Log.d("Network","Not Connected");
            return false;
        }
    }


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

    public ArrayList<HawkerCentre> getData() {
        return HCList;
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

    public static void updateActivity(MainActivity activity) {
        mActivityRef = new WeakReference<>(activity);
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
