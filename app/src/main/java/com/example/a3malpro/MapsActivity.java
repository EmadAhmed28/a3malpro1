package com.example.a3malpro; // تأكد من اسم البكج حقك

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.example.a3malpro.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (MainActivity3.mylocation != null) {

            mMap.addMarker(new MarkerOptions().position(MainActivity3.mylocation).title("Delivery Location"));


            float zoomLevel = 15.0f;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MainActivity3.mylocation, zoomLevel));
        }
    }
}