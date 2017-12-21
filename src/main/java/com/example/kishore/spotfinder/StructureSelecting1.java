package com.example.kishore.spotfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class StructureSelecting1 extends AppCompatActivity {
    ArrayList<String> vicinityStructures = new ArrayList<>();
    Button structure1;
    Button structure2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure_selecting1);
        vicinityStructures = getIntent().getStringArrayListExtra("vicinityStructures");

        structure1 = (Button) findViewById(R.id.structure1Button);
        structure2 = (Button) findViewById(R.id.structure2Button);
        if (vicinityStructures.contains("structure1")) {
            structure1.setVisibility(View.VISIBLE);
        }
        if (vicinityStructures.contains("structure2")) {
            structure2.setVisibility(View.VISIBLE);
        }
    }

    public void structure1(View view) {
        Intent intent = new Intent(this, SpotFinding1.class);
        intent.putExtra("structure", "Structure1");
        startActivity(intent);
    }

    public void structure2(View view) {
        Intent intent = new Intent(this, SpotFinding1.class);
        intent.putExtra("structure", "Structure2");
        startActivity(intent);
    }

    public void help(View view) {
        Toast.makeText(StructureSelecting1.this,
                "This is a list of the supported parking structures near your location. Please select the one in which you would like to park.",
                Toast.LENGTH_LONG).show();
    }
}