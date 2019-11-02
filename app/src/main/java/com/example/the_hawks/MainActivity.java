package com.example.the_hawks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
                openSearchResultsActivity();
            }
        });


    }


    public void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
    }



    public void openSearchResultsActivity() {
        Intent intent = new Intent(this, SearchResultsActivity.class);
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

