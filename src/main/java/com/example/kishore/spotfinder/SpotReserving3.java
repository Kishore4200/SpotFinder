package com.example.kishore.spotfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class SpotReserving3 extends AppCompatActivity {
    String spotName;
    int requestedExit;
    String structure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_reserving3);
        spotName = getIntent().getStringExtra("spotName");
        requestedExit = getIntent().getIntExtra("requestedExit", 0);
        structure = getIntent().getStringExtra("structure");
        final TextView instructionTextView = (TextView) findViewById(R.id.instructionTextView);
        final Button confirmButton = (Button) findViewById(R.id.confirmButton);
        final Button denyButton = (Button) findViewById(R.id.denyButton);
        final DatabaseReference givenSpot = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(structure + "/" + spotName);

        instructionTextView.setText(spotName + " has been filled. If this was you, please confirm. If not, please click deny.");
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmButton.setVisibility(View.GONE);
                denyButton.setVisibility(View.GONE);
                Toast.makeText(SpotReserving3.this, "Thank you for confirming!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SpotReserving3.this, MainActivity.class);
                startActivity(intent);
            }
        });
        denyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("reserver", "");
                givenSpot.updateChildren(map, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            System.out.println("Data could not be saved. " + databaseError.getMessage());
                        } else {
                            System.out.println("Data saved successfully.");
                        }
                    }
                });
                Toast.makeText(SpotReserving3.this, "We apologize for any inconvenience. This incident has been logged", Toast.LENGTH_LONG).show();
                Toast.makeText(SpotReserving3.this, "Please feel free to reserve another spot", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SpotReserving3.this, SpotFinding2.class);
                intent.putExtra("exitChoice", requestedExit);
                intent.putExtra("structure", structure);
                startActivity(intent);
            }
        });
    }
}
