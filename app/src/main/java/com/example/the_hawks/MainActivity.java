package com.example.the_hawks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button rollButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollButton = (Button) findViewById(R.id.rollButton);
        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openSearchResultsActivity();
            }
        });
    }

    public void openSearchResultsActivity(){
        Intent intent = new Intent(this,SearchResultsActivity.class);
        startActivity(intent);
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

    }

