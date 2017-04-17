package com.example.kishore.spotfinder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SpotFinding1 extends AppCompatActivity {
    Button exit1;
    Button exit2;
    Button exit3;
    Button exit4;
    Button exit5;
    Button exit6;
    String structure;
    int numSpots;
    long numAttributes;
    String userUid;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_finding1);
        structure = getIntent().getStringExtra("structure");
        System.out.println("**structure is " + structure + "**");
        userUid = getIntent().getStringExtra("userUid");
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReferenceFromUrl("gs://spotfinder-1348.appspot.com");
        StorageReference structureMapReference = storageReference.child("StructureMaps/" + structure.toLowerCase() + ".jpg");
        final ImageView mapImage = (ImageView) findViewById(R.id.mapImageView);
        intent = new Intent(this, SpotFinding2.class);
        intent.putExtra("structure", structure);
        intent.putExtra("userUid", userUid);
        exit1 = (Button) findViewById(R.id.exit1Button);
        exit2 = (Button) findViewById(R.id.exit2Button);
        exit3 = (Button) findViewById(R.id.exit3Button);
        exit4 = (Button) findViewById(R.id.exit4Button);
        exit5 = (Button) findViewById(R.id.exit5Button);
        exit6 = (Button) findViewById(R.id.exit6Button);

        final DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(structure);                                                                  // new DatabaseReference object based on the FirebaseDatabase object's child structure already linked to this project
        databaseReference.addChildEventListener(new ChildEventListener() {                          // add new ChildEventListener to DatabaseReference object to monitor the database for changes in the child nodes
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {                         // when a child is added
                if (dataSnapshot.toString().toLowerCase().contains("spot")) {
                    numAttributes = dataSnapshot.getChildrenCount();                                // set numAttributesLocal to the number of children nodes, and therefore the number of attributes, under each Spot-level node
                    numSpots++;                                                                     // increment the numSpotsLocal variable, which counts the number of spots in the structure
                }
                System.out.println("numSpots: " + numSpots);
                System.out.println("numAttributes: " + numAttributes);
                long numberOfExits = numAttributes - 4;
                if (numberOfExits >= 2) {
                    exit2.setVisibility(View.VISIBLE);
                    if (numberOfExits >= 3) {
                        exit3.setVisibility(View.VISIBLE);
                        if (numberOfExits >= 4) {
                            exit4.setVisibility(View.VISIBLE);
                            if (numberOfExits >= 5) {
                                exit5.setVisibility(View.VISIBLE);
                                if (numberOfExits == 6) {
                                    exit6.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //nothing
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //nothing
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //nothing
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //nothing
            }
        });

        final long ONE_MEGABYTE = 1024 * 1024;
        structureMapReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                mapImage.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("StorageReference error!" + exception);
            }
        });

        exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("exitChoice", 1);
                intent.putExtra("numSpots", numSpots);
                startActivity(intent);
            }
        });
        exit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("exitChoice", 2);
                intent.putExtra("numSpots", numSpots);
                startActivity(intent);
            }
        });
        exit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("exitChoice", 3);
                intent.putExtra("numSpots", numSpots);
                startActivity(intent);
            }
        });
        exit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("exitChoice", 4);
                intent.putExtra("numSpots", numSpots);
                startActivity(intent);
            }
        });
        exit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("exitChoice", 5);
                intent.putExtra("numSpots", numSpots);
                startActivity(intent);
            }
        });
        exit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("exitChoice", 6);
                intent.putExtra("numSpots", numSpots);
                startActivity(intent);
            }
        });
    }
}