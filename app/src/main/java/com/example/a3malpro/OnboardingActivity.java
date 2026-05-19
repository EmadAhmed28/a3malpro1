package com.example.a3malpro;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class OnboardingActivity extends AppCompatActivity {


    MaterialButton btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        btnNext = findViewById(R.id.btn_next);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OnboardingActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
