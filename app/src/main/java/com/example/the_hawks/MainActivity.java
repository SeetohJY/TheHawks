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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.the_hawks.HC.HC;
import com.example.the_hawks.Maps.MapsActivity;
import com.example.the_hawks.Search.SearchableActivity;
import com.example.the_hawks.NearbyHC.NearbyHC;
import com.example.the_hawks.Stalls.Stalls;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button buttonToHCList;
    private Button buttonToMap;
    public ArrayList<HawkerCentre> HCList = new ArrayList<HawkerCentre>();
    public int count = 0;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchableActivity.updateActivity(this);
        //HC.updateActivity(this);
//        HCFragment.updateActivity(this);
        Log.e("count1", Integer.toString(count));
        context = this.getApplicationContext();
        checkLocationServices();
        isNetworkConnectionAvailable();
        //getJSONString();



//        setContentView(R.layout.activity_main);

//        initialiseTempData();


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

//        rollButton = (Button) findViewById(R.id.rollButton);
//        rollButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                openHC();
//            }
//        });
    }
    // Following 2 functions for Maps HashMap
    protected String getJSONString(){
        String json = null;
        try {

            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier("hc_geojson",
                            "raw", getPackageName()));

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e("failure", ex.toString());
            return null;
        }
        return json;
    }

    public String getGeoJSON(){
        return getJSONString();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.e("Count", Integer.toString(count));

        setContentView(R.layout.activity_main);

        buttonToHCList = findViewById(R.id.buttonToHCList);
        buttonToHCList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openHC();
            }
        });

        buttonToMap = findViewById(R.id.buttonToMap);
        buttonToMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMapsActivity();
            }
        });



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

    }


//    public void toMain(ArrayList<HawkerCentre> data){
//        HCList = data;
//        Intent intent = new Intent(LoadingActivity.this,MainActivity.class).putParcelableArrayListExtra("toMain",HCList);
//        startActivity(intent);
//    }

    public ArrayList<HawkerCentre> getData(){
        return HCList;
    }
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
        startActivity(intent);
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

    public void nearbyHC() {
        Intent intent = new Intent(this, NearbyHC.class);
        startActivity(intent);
    }
    public void startStalls2 () {
        Intent intent = new Intent(this, Stalls.class);
        startActivity(intent);
    }

    public void startStalls () {
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
        HttpClient httpclient;
        InitMgr DataInitialised;
        ArrayList<HawkerCentre> HCList = new ArrayList<>();
        Context context;

        private RetrieveDataTask(Context context){this.context = context;}

        @Override
        protected ArrayList<HawkerCentre> doInBackground(Void... strings) {

            try{

                boolean isFilePresent = isFilePresent(MainActivity.this, "storage.json");
                if(isFilePresent) {
                    String jsonString1 = read(MainActivity.this, "storage.json");
                    JSONArray jsonList1 = new JSONArray(jsonString1);
                    String jsonString2 = read(MainActivity.this, "storage2.json");
                    JSONArray jsonList2 = new JSONArray(jsonString2);
                    DataInitialised = new InitMgr(jsonList1, jsonList2);
                    Log.e("Hello", jsonList1.toString());
                    //do the json parsing here and do the rest of functionality of app
                } else {
                    httpclient = new HttpClient();
                    httpclient.initData();
                    DataInitialised = new InitMgr(httpclient.getArray1(), httpclient.getArray2());
                    create(MainActivity.this, "storage.json", httpclient.getArray1().toString());
                    create(MainActivity.this, "storage2.json", httpclient.getArray2().toString());
                    Log.e("Hi", httpclient.getArray1().toString());
                }


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

    private String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    private boolean create(Context context, String fileName, String jsonString){
        String FILENAME = "storage.json";
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
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