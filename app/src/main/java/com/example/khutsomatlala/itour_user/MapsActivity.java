package com.example.khutsomatlala.itour_user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public String dec, lat, lon;
    public Boolean checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

     Intent i = getIntent();

     String lati = i.getStringExtra("lat");
     String loni = i.getStringExtra("lon");
     String name = i.getStringExtra("name");

            try {
                Double lat = Double.parseDouble(lati);
                Double lng = Double.parseDouble(loni);

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(lat, lng);

                mMap.addMarker(new MarkerOptions().position(sydney).title(name));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));

            } catch (NullPointerException e) {

                e.printStackTrace();

                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException e) {

                Toast.makeText(this, "Place not allocated on the map", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, DisplayPlacesActivity.class);
                startActivity(intent);
            }
        }

    public void places(View view) {
        Intent intent = new Intent(MapsActivity.this, DisplayPlacesActivity.class);
        startActivity(intent);

    }
}
