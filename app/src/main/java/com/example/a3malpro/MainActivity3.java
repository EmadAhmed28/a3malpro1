package com.example.a3malpro;

import android.app.DatePickerDialog; // مكتبة التاريخ
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker; // مكتبة التاريخ
import android.widget.EditText; // مكتبة حقل النص
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.braintreepayments.cardform.view.CardForm;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar; // مكتبة التقويم

public class MainActivity3 extends AppCompatActivity {

    private Button b, b2;
    private TextView t1, t2;
    private LocationManager locationManager;
    private LocationListener listener;
    public static LatLng mylocation;

    CardForm cardForm;
    MaterialButton btnBuy;
    TextView tvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        cardForm = findViewById(R.id.card_form);
        btnBuy = findViewById(R.id.btnBuy);
        tvTotalPrice = findViewById(R.id.tv_total_price);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .setup(MainActivity3.this);

        if (MenuFragment.tot != null) {
            tvTotalPrice.setText("Total: " + MenuFragment.tot + " SR");
        } else {
            tvTotalPrice.setText("Total: 0 SR");
        }

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()) {
                    Intent I = new Intent(MainActivity3.this, activity_final.class);
                    startActivity(I);
                } else {
                    Toast.makeText(MainActivity3.this, "Please complete card details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        t1 = findViewById(R.id.tv_longitude);
        t2 = findViewById(R.id.tv_latitude);
        b = findViewById(R.id.btn_get_location);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGPS();
            }
        });

        b2 = findViewById(R.id.btn_show_location);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity3.this, MapsActivity.class);
                startActivity(I);
            }
        });


        EditText bb = (EditText) findViewById(R.id.etDeliveryDate);
        bb.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    DatePickerDialog picker = new DatePickerDialog(MainActivity3.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int y, int m, int d) {
                                    bb.setText(String.valueOf(d) + "/" + String.valueOf(m + 1) + "/" + String.valueOf(y));
                                }
                            }, year, month, day);
                    picker.show();

                    bb.clearFocus();
                }
            }
        });
        // ==========================================

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                t1.setText("Longitude: " + String.valueOf(location.getLongitude()));
                t2.setText("Latitude: " + String.valueOf(location.getLatitude()));
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) { }
            @Override
            public void onProviderEnabled(String s) { }
            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }};
    }

    void startGPS() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}, 10);
            return;
        }
        locationManager.requestLocationUpdates("gps", 5000, 10, listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        {
            startGPS();
        }
    }
}