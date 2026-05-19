package com.example.a3malpro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_profile extends Fragment {

    TextView tvUserName, tvUserEmail;
    LinearLayout layoutLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        tvUserName = view.findViewById(R.id.tv_user_name);
        tvUserEmail = view.findViewById(R.id.tv_user_email);
        layoutLogout = view.findViewById(R.id.layout_logout);

        SharedPreferences loginData = requireActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String savedName = loginData.getString("userName", "Guest User");

        tvUserName.setText(savedName);

        tvUserEmail.setText(savedName + "@coffee.com");

        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = loginData.edit();
                editor.clear();
                editor.apply();

                Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);


                requireActivity().finish();
            }
        });

        return view;
    }
}