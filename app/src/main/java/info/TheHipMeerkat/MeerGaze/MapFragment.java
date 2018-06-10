package info.TheHipMeerkat.MeerGaze;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class MapFragment extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private Button mButton;

    private DatabaseReference mRef;
    private FirebaseAuth mAuth;

    int position;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
//         Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);


       position = (int) getIntent().getExtras().getLong("position");

        mButton = (Button) findViewById(R.id.button2);

        mAuth = FirebaseAuth.getInstance();
        mRef = mDatabase = FirebaseDatabase.getInstance().getReference().child("user");;

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view){
                if(mAuth.getCurrentUser() != null) {
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userEmail = firebaseUser.getEmail();
                    userEmail = userEmail.split("@")[0].toLowerCase().trim();

                    mRef.child(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            Log.v("tag", "user = " + user);

                            if(user.locFound(position)){
                                mRef.setValue(user);
                                Toast.makeText(view.getContext(), "Congratulations on your discovery fellow meerkat!", Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(view.getContext(), "You already discovered this location", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }else{
                    Toast.makeText(view.getContext(), "Please sign in first", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        if (position == 0){
            LatLng Porter = new LatLng(36.993187, -122.065201);
            mMap.addMarker(new MarkerOptions().position(Porter).title("Marked"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Porter));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Porter, 17));
        }

        if (position == 1){
            LatLng Porter = new LatLng(37.002440169950745, -122.06022843541257);
            mMap.addMarker(new MarkerOptions().position(Porter).title("Marked"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Porter));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Porter, 17));
        }

        if (position == 2) {
            LatLng Porter = new LatLng(36.996966, -122.053883);
            mMap.addMarker(new MarkerOptions().position(Porter).title("Marked"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Porter));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Porter, 17));
        }

        if (position == 3){
            LatLng Porter = new LatLng(37.000020, 122.054591);
            mMap.addMarker(new MarkerOptions().position(Porter).title("Marked"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Porter));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Porter, 17));
        }

        if (position == 4){
            LatLng Porter = new LatLng(36.999339, -122.049099);
            mMap.addMarker(new MarkerOptions().position(Porter).title("Marked"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Porter));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Porter, 17));
        }

        if (position == 5){
            LatLng Porter = new LatLng(36.996102, -122.068807);
            mMap.addMarker(new MarkerOptions().position(Porter).title("Marked"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Porter));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Porter, 17));
        }

        if (position == 6){
            LatLng Porter = new LatLng(36.997817, -122.062262);
            mMap.addMarker(new MarkerOptions().position(Porter).title("Marked"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Porter));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Porter, 17));
        }

        if (position == 7){
            LatLng Porter = new LatLng(36.995912, -122.052142);
            mMap.addMarker(new MarkerOptions().position(Porter).title("Marked"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(Porter));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Porter, 17));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //Log.d(TAG, "callback");
        switch (requestCode) {
            case 99:
                // If the permissions aren't set, then return. Otherwise, proceed.
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                                , 10);
                    }
                    //Log.d(TAG, "returning program");
                    return;
                }
                else{
                    // Create Intent to reference MyService, start the Service.
                    //Log.d(TAG, "adding location");
                    mMap.setMyLocationEnabled(true);

                }
                break;
            default:
                break;
        }
    }

}