package com.example.the_hawks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button stalls = findViewById(R.id.startStalls);
        stalls.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startStalls();
            }
        });
    }

    public void startStalls () {
        Intent intent = new Intent(this, Stalls.class);
        startActivity(intent);
    }
}
