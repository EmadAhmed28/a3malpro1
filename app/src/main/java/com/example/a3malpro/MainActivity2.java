package com.example.a3malpro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity2 extends AppCompatActivity {

    EditText username;
    EditText password;

    MaterialButton btnLogin;
    TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        if (!loginData.getString("userName", "").equals("")) {

            Intent I = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(I);
            finish();
        }

        username = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btn_login);
        tvSignUp = findViewById(R.id.tv_sign_up);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v); // استدعاء دالة الدكتور
            }
        });


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // تأكد أن اسم كلاس التسجيل عندك هو SignUpActivity أو activity_signup
                Intent intent = new Intent(MainActivity2.this, activity_signup.class);
                startActivity(intent);
            }
        });
    }


    public void login(View view){
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("userName", username.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.apply();

        Toast.makeText(MainActivity2.this, "تم تسجيل الدخول بنجاح!", Toast.LENGTH_SHORT).show();


        Intent I = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(I);
        finish();
    }


    public void getData(View view){
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        username.setText(loginData.getString("userName", "") );
        password.setText(loginData.getString("password", "") );
    }
}