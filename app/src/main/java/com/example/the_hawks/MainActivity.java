package com.example.the_hawks;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.the_hawks.HC.HC;
import com.example.the_hawks.Stalls.Stalls;

public class MainActivity extends AppCompatActivity {
    private Button rollButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button stalls = findViewById(R.id.startStalls);
        stalls.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startStalls();
            }
        });
//
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(myToolbar);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_search:
                //start search dialog
                super.onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
    }



    public void openHC() {
        Intent intent = new Intent(this, HC.class);
        startActivity(intent);
    }
    public void startStalls () {
        Intent intent = new Intent(this, Stalls.class);
        startActivity(intent);
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

