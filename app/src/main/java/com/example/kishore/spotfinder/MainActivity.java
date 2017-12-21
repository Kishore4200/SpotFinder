package com.example.kishore.spotfinder;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int numSpotsLocal;
    long numAttributesLocal;
    int numSpots;
    long numAttributes;
    ArrayList<Integer> intArray = new ArrayList<>(1);
    final int RC_SIGN_IN = 1;
    FirebaseAuth mAuth;
    String userUid;
    String structure = "";
    boolean located = false;
    ArrayList<String> vicinityStructures = new ArrayList<>();
    FrameLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //structure = getIntent().getStringExtra("structure");
        //structure = "Structure2";
        intArray.add(0, 0);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (FrameLayout) findViewById(R.id.progressBarHolder);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)  // a new GoogleSignInOptions object to do the basic Google sign-in
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)                        // a new GoogleApiClient object to handle connections to the Google sign-in API
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.v("CONNECTIONFAILED", connectionResult.toString());
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();                                                                 // connect the GoogleApiClient above
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);               // new Intent object using the GoogleSignInApi
        startActivityForResult(signInIntent, RC_SIGN_IN);                                           // start the Intent upon receiving the result from GoogleSignInApi

        FirebaseAuth.AuthStateListener mAuthStateListener = new FirebaseAuth.AuthStateListener() {  // new AuthStateListener for monitoring the authentication state
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {                                                                 // if user logged in
                    Log.v("AUTH", "onAuthStateChanged:signed_in:" + user.getUid().toString());      // Log to logcat
                    userUid = user.getUid().toString();
                } else {                                                                            // if no user logged in
                    Log.v("AUTH", "onAuthStateChanged:signed_out");                                 // Log to logcat
                }
            }
        };
        mAuth.addAuthStateListener(mAuthStateListener);                                             // set the above AuthStateListener to the FirebaseAuth object mAuth

        final DatabaseReference databaseReference = FirebaseDatabase
                .getInstance()
                .getReference();                                                                    // new DatabaseReference object based on the FirebaseDatabase object's child structure already linked to this project

        try {
            System.out.println("INSIDE TRY!!!!!");
            final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            final android.location.LocationListener locationListener = new android.location.LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (located == false) {
                        System.out.println("--- LOCATION: " + location + " ---");
                        located = true;
                        GeoQuery geoQuery = new GeoFire(databaseReference).queryAtLocation(new GeoLocation(location.getLatitude(), location.getLongitude()), 0.2);
                        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
                            @Override
                            public void onKeyEntered(String key, GeoLocation location) {
                                System.out.println("--- " + key.replace("structure", "Structure ") + ": " + location.latitude + ", " + location.longitude + " ---");
                                vicinityStructures.add(key);

                                if (vicinityStructures.size() == 1) {
                                    structure = vicinityStructures.get(0);
                                    System.out.println("** structure set to " + structure + "! **");
                                    Toast toast = Toast.makeText(MainActivity.this, "You have been auto-located to be at " + structure + " (" + location.latitude + ", " + location.longitude + ").", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.TOP, 25, 0);
                                    toast.show();
                                } else {
                                    System.out.println("** vicinityStructures is " + vicinityStructures.size() + " long --> calling new intent! **");
                                    Intent intent = new Intent(MainActivity.this, StructureSelecting1.class);
                                    intent.putExtra("vicinityStructures", vicinityStructures);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onKeyExited(String key) {

                            }

                            @Override
                            public void onKeyMoved(String key, GeoLocation location) {

                            }

                            @Override
                            public void onGeoQueryReady() {
                                System.out.println("All initial data loaded!");
                                progressBar.setVisibility(View.GONE);

                            }

                            @Override
                            public void onGeoQueryError(DatabaseError error) {

                            }
                        });
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                    }
            };
            Location lastKnownLocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            System.out.println("lastKnownLocationNetwork: " + lastKnownLocationNetwork.getLatitude() + ", " + lastKnownLocationNetwork.getLongitude());
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0 ,0, locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.v("AUTH", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.v("AUTH", acct.getDisplayName() + " signed in!");
            firebaseAuthWithGoogle(acct);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.v("AUTH", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.v("AUTH", "signInWithCredential:onComplete:" + task.isSuccessful());
                        DatabaseReference databaseReference = FirebaseDatabase
                                .getInstance()
                                .getReference();
                                //.child(structure);
                        Log.v("AUTH", "Authenticated, heading in to ChildEventListener");
                        databaseReference.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                numAttributesLocal = dataSnapshot.getChildrenCount();                               // set numAttributesLocal to the number of children nodes, and therefore the number of attributes, under each Spot-level node
                                numSpotsLocal++;                                                                    // increment the numSpotsLocal variable, which counts the number of spots in the structure
                                Log.v("AUTH", "numSpots: " + numSpots + " numAttributes: " + numAttributes);
                                settingNumbers(numSpotsLocal, numAttributesLocal);
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
                                Log.v("AUTH", "Error: " + databaseError);
                            }
                        });
                        Log.v("AUTH", "Authenticated, out of ChildEventListener");
                        if (!task.isSuccessful()) {
                            Log.v("AUTH", "signInWithCredential", task.getException());
                        }
                    }
                });
    }


    public void settingNumbers(int spotsCount, long attributesCount) {
        numSpots = spotsCount;
        numAttributes = attributesCount;
        Log.v("AUTH", "numSpots: " + numSpots + " numAttributes: " + numAttributes);
    }

    public void findingClosestSpots(View view) {
        Intent intent = new Intent(this, SpotFinding1.class);
        intent.putExtra("structure", structure);
        intent.putExtra("userUid", userUid);
        startActivity(intent);
    }

    public void wherePark(View view) {
        Intent intent = new Intent(this, WherePark1.class);
        intent.putExtra("structure", structure);
        intent.putExtra("userUid", userUid);
        startActivity(intent);
    }

    public void help(View view) {
        Intent intent = new Intent(this, About1.class);
        intent.putExtra("structure", structure);
        intent.putExtra("numSpots", numSpots);
        intent.putExtra("numAttributes", numAttributes);
        intent.putExtra("userUid", userUid);
        startActivity(intent);
    }

    public void structureSelect(View view) {
        Intent intent = new Intent(this, StructureSelecting1.class);
        intent.putExtra("vicinityStructures", vicinityStructures);
        startActivity(intent);
    }
}
