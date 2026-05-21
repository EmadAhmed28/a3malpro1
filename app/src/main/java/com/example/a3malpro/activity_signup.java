package com.example.a3malpro;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class activity_signup extends AppCompatActivity {
    ImageView ivBack;
    MaterialButton btnRegister;
    TextView tvSignIn;

    EditText username,password,email;
    TextView tv;
    String nn,pp;
    Connection conn;
    Statement stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        username = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

        } catch (Exception e) {
            tv.setText("Error"+e.getMessage());
        }



        ivBack = findViewById(R.id.iv_back);
        btnRegister = findViewById(R.id.btn_register);
        tvSignIn = findViewById(R.id.tv_sign_in);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  
            }
        });


        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); 
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity_signup.this, activity_congratulations.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
