package com.example.kishore.spotfinder;

import android.app.Notification;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;


public class SpotReserving2 extends AppCompatActivity {
    String spotName;
    int requestedExit;
    boolean intentStarted = false;
    String structure;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_reserving2);
        final TextView timer = (TextView) findViewById(R.id.timerTextView);
        TextView instructionTextView = (TextView) findViewById(R.id.instructionTextView);
        final Intent intent = new Intent(SpotReserving2.this, MainActivity.class);
        spotName = getIntent().getStringExtra("spotName");
        requestedExit = getIntent().getIntExtra("requestedExit",  0);
        structure = getIntent().getStringExtra("structure");
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReferenceFromUrl("gs://spotfinder-1348.appspot.com");
        StorageReference structureMapReference = storageReference.child("StructureMaps/" + structure.toLowerCase() + ".jpg");
        final ImageView mapImage = (ImageView) findViewById(R.id.mapImageView);
        final DatabaseReference givenSpot = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(structure + "/" + spotName);

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

        instructionTextView.setText("Time Remaining to Park in " + spotName);
        new CountDownTimer(185000, 1000) {
            public void onTick(long millisUntilFinished) {
                // add value event listener to listen for status changes
                givenSpot.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println(dataSnapshot.child("alias").getValue() + ": " + dataSnapshot.child("status").getValue());
                        if (dataSnapshot.child("status").getValue().toString().equals("Full") && intentStarted == false) {
                            Intent intent1 = new Intent(SpotReserving2.this, SpotReserving3.class);
                            intent1.putExtra("spotName", spotName);
                            intent1.putExtra("requestedExit", requestedExit);
                            intent1.putExtra("structure", structure);
                            intentStarted = true;
                            cancel();
                            startActivity(intent1);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                long seconds = (millisUntilFinished/1000)%60;
                long minutes = ((millisUntilFinished/1000)/60);
                if (seconds >= 10) {
                    timer.setText(minutes + ":" + seconds);
                } else {
                    timer.setText(minutes + ":0" + seconds);
                }
            }
            public void onFinish() {
                timer.setText("Time Up!");
                Toast.makeText(SpotReserving2.this, "Your reserved spot has been released from your reservation due to exceeding the time limit.", Toast.LENGTH_LONG).show();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("status", "Empty");
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
                startActivity(intent);
            }
        }.start();
    }
}
