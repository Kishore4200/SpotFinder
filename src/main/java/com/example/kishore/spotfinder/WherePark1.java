package com.example.kishore.spotfinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class WherePark1 extends AppCompatActivity {
    String structure;
    String userUid;
    boolean foundSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_park1);
        structure = getIntent().getStringExtra("structure");
        userUid = getIntent().getStringExtra("userUid");
        final TextView textView = (TextView) findViewById(R.id.spotTextView);
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReferenceFromUrl("gs://spotfinder-1348.appspot.com");
        StorageReference structureMapReference = storageReference.child("StructureMaps/" + structure.toLowerCase() + ".jpg");
        final ImageView mapImage = (ImageView) findViewById(R.id.mapImageView);

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

        final DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(structure);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.child("reserver").getValue() != null &&
                        dataSnapshot.child("reserver").getValue().toString().equals(userUid) &&
                        dataSnapshot.child("status").getValue() != null &&
                        dataSnapshot.child("status").getValue().toString().equals("Full")) {
                    textView.setText(dataSnapshot.child("alias").getValue().toString().replace("Spot", "Spot "));
                    textView.setTextSize(24);
                    foundSpot = true;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
        if (!foundSpot) {
            textView.setText("No spot has been filled under your name.");
        }
    }
}
