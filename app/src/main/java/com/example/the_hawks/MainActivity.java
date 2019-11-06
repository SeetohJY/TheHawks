package com.example.the_hawks;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.the_hawks.HC.HC;
import com.example.the_hawks.Maps.MapsActivity;
import com.example.the_hawks.Stalls.Stalls;
import com.example.the_hawks.NearbyHC.NearbyHC;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button rollButton;
    public ArrayList<HawkerCentre> HCList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseTempData();

        Button stalls2 = findViewById(R.id.startStalls2);
        stalls2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startStalls2();
            }
        });



        Button nearbyHC = findViewById(R.id.nearby_hc);
        nearbyHC.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                nearbyHC();
            }
        });


        Button HawkerCentreActivity = findViewById(R.id.HawkerCentreActivity);
        HawkerCentreActivity.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            HawkerCentre hawkerCentre = HCList.get(0);
            startHawkerCentreActivity(hawkerCentre);
            }
        });

//        if (HCList.isEmpty()){
//            RetrieveDataTask AsyncInitialiseData = new RetrieveDataTask();
//            AsyncInitialiseData.execute();
//        }






        // wire buttonToMap
//        Button btn = (Button) findViewById(R.id.buttonToMap);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMapsActivity();
//            }
//        });


        rollButton = (Button) findViewById(R.id.rollButton);
        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openHC();
            }
        });


    }


    public void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
    }


    public void storeData(ArrayList<HawkerCentre> data){
        HCList = data;
        Log.e("Log:", HCList.toString());
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

        //Log.e("temp data:", HCList.get(0).toString());

    }

    private class RetrieveDataTask extends AsyncTask<Void, Void, ArrayList<HawkerCentre>> {

        private Exception exception;
        InitMgr DataInitialised;
        ArrayList<HawkerCentre> HCList = new ArrayList<>();
        @Override
        protected ArrayList<HawkerCentre> doInBackground(Void... strings) {

            try{
                DataInitialised = new InitMgr();


            } catch(JSONException | IOException err){
                Log.e("Error Here", err.toString());
            }
            return HCList;
        }

        protected void onPostExecute() {
            HCList = DataInitialised.getHCList();
            storeData(HCList);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

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

