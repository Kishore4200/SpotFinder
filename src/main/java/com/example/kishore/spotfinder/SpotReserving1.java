package com.example.kishore.spotfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SpotReserving1 extends AppCompatActivity {
    String spotName;
    SpotInformation spotInformation;
    String userUid;
    int requestedExit;
    String structure;
    boolean reservedOtherSpot;
    int numSpots;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_reserving1);
        spotInformation = new SpotInformation();
        spotName = getIntent().getStringExtra("spotName");
        userUid = getIntent().getStringExtra("userUid");
        requestedExit = getIntent().getIntExtra("requestedExit", 0);
        structure = getIntent().getStringExtra("structure");
        numSpots = getIntent().getIntExtra("numSpots", 0);
        final DatabaseReference givenSpot = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(structure + "/" + spotName);
        System.out.println(">>> INITIAL VALUE: " + reservedOtherSpot);

        givenSpot.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                spotInformation.status = dataSnapshot.getValue(SpotInformation.class).getStatus();
                System.out.println("status: " + spotInformation.status);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final TextView reserveCompleted = (TextView) findViewById(R.id.reservationCompleteTextView);
        final TextView textView = (TextView) findViewById(R.id.instructionTextView);
        textView.setText("Please confirm that you would like to reserve " + spotName);

        final Button reserveDeny = (Button) findViewById(R.id.reserveDenyButton);
        final Button reserveConfirm = (Button) findViewById(R.id.reserveConfirmButton);
        reserveConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spotInformation.getStatus().equals("Empty")) {
                    final Map<String, Object> map = new HashMap<String, Object>();
                    map.put("status", "Reserved");
                    map.put("reserver", userUid);
                    DatabaseReference databaseReference = FirebaseDatabase
                            .getInstance()
                            .getReference()
                            .child(structure);
                    databaseReference.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                            System.out.println("dataSnapshot.child('reserver'): " + dataSnapshot.child("reserver").getValue());
                            if (dataSnapshot.child("reserver").getValue() != null &&
                                    dataSnapshot.child("reserver").getValue().toString().equals(userUid)) {
                                reservedOtherSpot = true;
                                System.out.println("=== RESERVED OTHER SPOT ===");
                                System.out.println(dataSnapshot.child("alias").getValue().toString() + ": " + dataSnapshot.child("reserver").getValue().toString() + " = " + userUid + ": " + dataSnapshot.child("reserver").getValue().toString().equals(userUid));
                            }
                            counter++;
                            if (counter == numSpots) {
                                if (reservedOtherSpot) {
                                    Toast.makeText(SpotReserving1.this, "" + spotName + " could not be reserved because you have already reserved another spot in this structure", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SpotReserving1.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    System.out.println(false);
                                    givenSpot.updateChildren(map, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                            if (databaseError != null) {
                                                System.out.println("Data could not be saved. " + databaseError.getMessage());
                                            } else {
                                                System.out.println("Data saved successfully.");
                                                Toast.makeText(SpotReserving1.this, "" + spotName + " has successfully been reserved!", Toast.LENGTH_SHORT).show();
                                                Toast.makeText(SpotReserving1.this, "You have 3 minutes to park there!", Toast.LENGTH_LONG).show();
                                                System.out.println("Starting Intent SpotReserving2!");
                                                Intent intent = new Intent(SpotReserving1.this, SpotReserving2.class);
                                                intent.putExtra("structure", structure);
                                                intent.putExtra("spotName", spotName);
                                                intent.putExtra("requestedExit", requestedExit);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    //make this whole next section a callback for comment on line 78???
                    System.out.println("reservedOtherSpot: " + reservedOtherSpot);

                    /*if (reservedOtherSpot == false) {
                        System.out.println(reservedOtherSpot);
                        givenSpot.updateChildren(map, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                                } else {
                                    System.out.println("Data saved successfully.");
                                    Toast.makeText(SpotReserving1.this, "" + spotName + " has successfully been reserved!", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(SpotReserving1.this, "You have 3 minutes to park there!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SpotReserving1.this, SpotReserving2.class);
                                    intent.putExtra("structure", structure);
                                    intent.putExtra("spotName", spotName);
                                    intent.putExtra("requestedExit", requestedExit);
                                    startActivity(intent);
                                }
                            }
                        });
                    } else {
                        Toast.makeText(SpotReserving1.this, "" + spotName + " could not be reserved because you have already reserved another spot in this structure", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SpotReserving1.this, MainActivity.class);
                        startActivity(intent);
                    }*/
                } else {
                    Toast.makeText(SpotReserving1.this, "" + spotName + " could not be reserved as it has been taken by another individual", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SpotReserving1.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        reserveDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpotReserving1.this, MainActivity.class);
                intent.putExtra("structure", structure);
                startActivity(intent);
            }
        });
    }

    public void settingBoolean(boolean reservedOtherSpotLocal) {
        reservedOtherSpot = reservedOtherSpotLocal;
        System.out.println(">>> Local var GLOBALIZED <<<");
    }
}
