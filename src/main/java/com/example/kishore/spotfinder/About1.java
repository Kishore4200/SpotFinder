package com.example.kishore.spotfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;

public class About1 extends AppCompatActivity {
    String structure;
    int numSpots;
    long numAttributes;
    String userUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about1);
        structure = getIntent().getStringExtra("structure");
        numSpots = getIntent().getIntExtra("numSpots", 0);
        numAttributes = getIntent().getLongExtra("numAttributes", 0);
        userUid = getIntent().getStringExtra("userUid");
    }

    public void findingClosestSpots(View view) {
        Intent intent = new Intent(this, SpotFinding1.class);
        intent.putExtra("structure", structure);
        intent.putExtra("numSpots", numSpots);
        intent.putExtra("numAttributes", numAttributes);
        intent.putExtra("userUid", userUid);
        startActivity(intent);
    }

    public void wherePark(View view) {
        Intent intent = new Intent(this, WherePark1.class);
        intent.putExtra("structure", structure);
        intent.putExtra("userUid", userUid);
        startActivity(intent);
    }
}
