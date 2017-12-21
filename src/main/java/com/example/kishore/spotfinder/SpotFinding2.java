package com.example.kishore.spotfinder;

import android.content.Intent;
import android.content.res.ObbInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SpotFinding2 extends AppCompatActivity {

    int requestedExit;
    ArrayList<String> spots = new ArrayList<String>();
    TextView firstSpotName;
    TextView firstSpotDist;
    TextView secondSpotName;
    TextView secondSpotDist;
    TextView thirdSpotName;
    TextView thirdSpotDist;
    String userUid;
    String structure;
    int numSpots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_finding2);
        firstSpotName = (TextView) findViewById(R.id.firstSpotName);
        firstSpotDist = (TextView) findViewById(R.id.firstSpotDist);
        secondSpotName = (TextView) findViewById(R.id.secondSpotName);
        secondSpotDist = (TextView) findViewById(R.id.secondSpotDist);
        thirdSpotName = (TextView) findViewById(R.id.thirdSpotName);
        thirdSpotDist = (TextView) findViewById(R.id.thirdSpotDist);
        structure = getIntent().getStringExtra("structure");
        System.out.println("**structure is " + structure + "**");
        userUid = getIntent().getStringExtra("userUid");
        requestedExit = getIntent().getIntExtra("exitChoice", 0);
        numSpots = getIntent().getIntExtra("numSpots", 0);

        ((TextView) findViewById(R.id.instructionTextView)).setText("These are the closest empty spots to Exit " + requestedExit + ".");
        final DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(structure);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                Query query = databaseReference.orderByChild("dist"+requestedExit);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                        if (dataSnapshot.getValue().getClass().toString().contains("HashMap")) {
                            SpotInformation spotInformation = (SpotInformation) dataSnapshot.getValue(SpotInformation.class);
                            double tempDistance = 0;
                            if (spotInformation.getStatus().equals("Empty")) {
                                if (requestedExit == 1) {
                                    tempDistance = spotInformation.getDist1();
                                }
                                if (requestedExit == 2) {
                                    tempDistance = spotInformation.getDist2();
                                }
                                if (requestedExit == 3) {
                                    tempDistance = spotInformation.getDist3();
                                }
                                if (requestedExit == 4) {
                                    tempDistance = spotInformation.getDist4();
                                }
                                if (requestedExit == 5) {
                                    tempDistance = spotInformation.getDist5();
                                }
                                if (requestedExit == 6) {
                                    tempDistance = spotInformation.getDist6();
                                }
                                if (!spots.contains(dataSnapshot.getKey())) {
                                    spots.add(dataSnapshot.getKey());
                                    spots.add(String.valueOf(tempDistance));
                                }
                            }
                        }
                        populateTextViews(spots);
                    }

                    @Override
                    public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                Query query = databaseReference.orderByChild("dist"+requestedExit);
                query.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
                    @Override
                    public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                        if (dataSnapshot.getValue().getClass().toString().contains("HashMap")) {
                            SpotInformation spotInformation = (SpotInformation) dataSnapshot.getValue(SpotInformation.class);
                            double tempDistance = 0;
                            if (spotInformation.getStatus().equals("Empty")) {
                                if (requestedExit == 1) {
                                    tempDistance = spotInformation.getDist1();
                                }
                                if (requestedExit == 2) {
                                    tempDistance = spotInformation.getDist2();
                                }
                                if (requestedExit == 3) {
                                    tempDistance = spotInformation.getDist3();
                                }
                                if (requestedExit == 4) {
                                    tempDistance = spotInformation.getDist4();
                                }
                                if (requestedExit == 5) {
                                    tempDistance = spotInformation.getDist5();
                                }
                                if (requestedExit == 6) {
                                    tempDistance = spotInformation.getDist6();
                                }
                                if (!spots.contains(dataSnapshot.getKey())) {
                                    spots.add(dataSnapshot.getKey());
                                    spots.add(String.valueOf(tempDistance));
                                }
                            }
                        }
                        populateTextViews(spots);
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

        firstSpotName.setOnClickListener(spotOnClick);
        firstSpotDist.setOnClickListener(spotOnClick);
        secondSpotName.setOnClickListener(spotOnClick);
        secondSpotDist.setOnClickListener(spotOnClick);
        thirdSpotName.setOnClickListener(spotOnClick);
        thirdSpotDist.setOnClickListener(spotOnClick);
    }

    final View.OnClickListener spotOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SpotFinding2.this, SpotReserving1.class);
            switch (view.getId()) {
                case R.id.firstSpotName:
                    intent.putExtra("structure", structure);
                    intent.putExtra("spotName", firstSpotName.getText());
                    intent.putExtra("userUid", userUid);
                    intent.putExtra("numSpots", numSpots);
                    intent.putExtra("requestedExit", requestedExit);
                    startActivity(intent);
                    break;
                case R.id.firstSpotDist:
                    intent.putExtra("structure", structure);
                    intent.putExtra("spotName", firstSpotName.getText());
                    intent.putExtra("userUid", userUid);
                    intent.putExtra("numSpots", numSpots);
                    intent.putExtra("requestedExit", requestedExit);
                    startActivity(intent);
                    break;
                case R.id.secondSpotName:
                    intent.putExtra("structure", structure);
                    intent.putExtra("spotName", secondSpotName.getText());
                    intent.putExtra("userUid", userUid);
                    intent.putExtra("numSpots", numSpots);
                    intent.putExtra("requestedExit", requestedExit);
                    startActivity(intent);
                    break;
                case R.id.secondSpotDist:
                    intent.putExtra("structure", structure);
                    intent.putExtra("spotName", secondSpotName.getText());
                    intent.putExtra("userUid", userUid);
                    intent.putExtra("numSpots", numSpots);
                    intent.putExtra("requestedExit", requestedExit);
                    startActivity(intent);
                    break;
                case R.id.thirdSpotName:
                    intent.putExtra("structure", structure);
                    intent.putExtra("spotName", thirdSpotName.getText());
                    intent.putExtra("userUid", userUid);
                    intent.putExtra("numSpots", numSpots);
                    intent.putExtra("requestedExit", requestedExit);
                    startActivity(intent);
                    break;
                case R.id.thirdSpotDist:
                    intent.putExtra("structure", structure);
                    intent.putExtra("spotName", thirdSpotName.getText());
                    intent.putExtra("userUid", userUid);
                    intent.putExtra("numSpots", numSpots);
                    intent.putExtra("requestedExit", requestedExit);
                    startActivity(intent);
                    break;
            }
        }
    };

    public void populateTextViews(ArrayList<String> spots) {
        if (spots.size() >= 2) {
            firstSpotName.setText(spots.get(0));
            firstSpotDist.setText(spots.get(1) + " meters");
            if (spots.size() >= 4) {
                secondSpotName.setText(spots.get(2));
                secondSpotDist.setText(spots.get(3) + " meters");
                if (spots.size() >= 6) {
                    thirdSpotName.setText(spots.get(4));
                    thirdSpotDist.setText(spots.get(5) + " meters");
                }
            }
        }
    }
}
