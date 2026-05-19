package com.example.a3malpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class fragment_cart extends Fragment {

    ListView lvCartItems;
    TextView tvCartTotal;
    MaterialButton btnCheckout;
    int totalAmount = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        lvCartItems = view.findViewById(R.id.lv_cart_items);
        tvCartTotal = view.findViewById(R.id.tv_cart_total);
        btnCheckout = view.findViewById(R.id.btn_go_to_checkout);


        buildCartList();


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MenuFragment.buys.isEmpty()) {
                    Toast.makeText(getActivity(), "Your cart is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), MainActivity3.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private void buildCartList() {
        totalAmount = 0;
        ArrayList<String> displayList = new ArrayList<>();


        for (int i = 0; i < MenuFragment.buys.size(); i++) {
            MenuFragment.Buy item = MenuFragment.buys.get(i);
            int subTotal = item.itemprice * item.itemquant;
            totalAmount += subTotal;


            if (item.itemquant > 0) {
                displayList.add(item.itemname + "  (x" + item.itemquant + ")  = " + subTotal + " SR");
            }
        }


        MenuFragment.tot = totalAmount;
        tvCartTotal.setText(totalAmount + " SR");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, displayList);
        lvCartItems.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        buildCartList();
    }
}