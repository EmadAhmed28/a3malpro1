package com.example.a3malpro;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bottomNavigation = findViewById(R.id.bottom_navigation);


        HomeFragment firstFragment = new HomeFragment();
        MenuFragment secondFragment = new MenuFragment();
        fragment_cart thirdFragment = new fragment_cart();
        fragment_profile fourthFragment = new fragment_profile();


        BadgeDrawable badge = bottomNavigation.getOrCreateBadge(R.id.nav_cart);
        badge.setVisible(true);
        badge.setNumber(3);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).commit();

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();


                if (itemId == R.id.nav_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).commit();
                    return true;

                } else if (itemId == R.id.nav_menu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, secondFragment).commit();
                    return true;

                } else if (itemId == R.id.nav_cart) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, thirdFragment).commit();
                    return true;

                } else if (itemId == R.id.nav_profile) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fourthFragment).commit();
                    return true;
                }
                return true;
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        return true;
    }
}