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

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button buttonToHCList;
    private Button buttonToMap;
    public ArrayList<HawkerCentre> HCList = new ArrayList<HawkerCentre>();
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HC.updateActivity(this);
        checkLocationServices();
        isNetworkConnectionAvailable();

        Intent test = new Intent(this, LoadingActivity.class);
        startActivity(test);
        Context context = this.getApplicationContext();

        if (HCList.isEmpty()) {
            RetrieveDataTask AsyncInitialiseData = new RetrieveDataTask(context);
            AsyncInitialiseData.execute();
            Intent intent = new Intent(this, LoadingActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);
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

    }

    public ArrayList<HawkerCentre> getData() {
        return HCList;
    }

    private void checkLocationServices() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (!gps_enabled && !network_enabled) {
            checkGPSLocation();
        }
    }

    private void checkGPSLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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


    private void checkNetworkConnection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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


    private boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            Log.d("Network", "Connected");
            return true;
        } else {
            checkNetworkConnection();
            Log.d("Network", "Not Connected");
            return false;
        }
    }

    private void addCount() {
        count = 1;
    }

    private void storeData(ArrayList<HawkerCentre> hclist) {
        HCList = hclist;
    }

    private void openHC() {
        Intent intent = new Intent(this, HC.class);
        startActivity(intent);
    }

    private void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    private class RetrieveDataTask extends AsyncTask<Void, Void, ArrayList<HawkerCentre>> {

        private Exception exception;
        HttpClient httpclient;
        InitMgr DataInitialised;
        ArrayList<HawkerCentre> HCList = new ArrayList<>();
        Context context;

        private RetrieveDataTask(Context context) {
            this.context = context;
        }

        @Override
        protected ArrayList<HawkerCentre> doInBackground(Void... strings) {

            try {

                boolean isFilePresent = isFilePresent(MainActivity.this, "storage.json");
                if (isFilePresent) {
                    String jsonString1 = read(MainActivity.this, "storage.json");
                    JSONArray jsonList1 = new JSONArray(jsonString1);
                    String jsonString2 = read(MainActivity.this, "storage2.json");
                    JSONArray jsonList2 = new JSONArray(jsonString2);
                    DataInitialised = new InitMgr(jsonList1, jsonList2);
                } else {
                    httpclient = new HttpClient();
                    httpclient.initData();
                    DataInitialised = new InitMgr(httpclient.getArray1(), httpclient.getArray2());
                    create(MainActivity.this, "storage.json", httpclient.getArray1().toString());
                    create(MainActivity.this, "storage2.json", httpclient.getArray2().toString());
                }


            } catch (JSONException | IOException err) {
                Log.e("Error Here", err.toString());
            }
            ArrayList<HawkerCentre> hclist = DataInitialised.getHCList();
            return hclist;
        }


        protected void onPostExecute(ArrayList<HawkerCentre> hclist) {
            HCList = DataInitialised.getHCList();
            storeData(HCList);
            addCount();
            Intent intent = new Intent(context, MainActivity.class);
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

    private boolean create(Context context, String fileName, String jsonString) {
        String FILENAME = "storage.json";
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
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

    private boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }
}