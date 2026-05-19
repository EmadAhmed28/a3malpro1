package com.example.a3malpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class activity_congratulations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MaterialButton btnStartExploring;


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_congratulations);

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

            btnStartExploring = findViewById(R.id.btn_start_exploring);


            btnStartExploring.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity_congratulations.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }