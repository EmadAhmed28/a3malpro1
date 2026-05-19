package com.example.a3malpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private WebView wv1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        wv1 = (WebView) view.findViewById(R.id.webView);


        String data = "<html> <head> <script> "
                + " function myFunction() { "
                + " var x = document.getElementById('fname'); "
                + " x.value = x.value.toUpperCase(); " // حركة الدكتور بالنص
                + " document.getElementById('msg').innerHTML = 'Enjoy your coffee, <b>' + x.value + '</b>! Use Code: ALGO20'; "
                + " } </script> "
                + " <style> "
                + " body { text-align: center; font-family: sans-serif; background-color: #FFF3E0; padding: 20px; border-radius: 10px; } "
                + " h2 { color: #4E342E; margin-bottom: 5px;} "
                + " p { color: #6D4C41; font-size: 14px;} "
                + " input { padding: 8px; border: 1px solid #BCAAA4; border-radius: 4px; } "
                + " button { background-color: #224C4A; color: white; padding: 10px; border: none; border-radius: 5px; margin-top: 15px; font-weight: bold;} "
                + " </style> </head> <body> "
                + " <h2>☕ Algorithm Roastery ☕</h2> "
                + " <p>Enter your name to claim your 20% discount code!</p> "
                + " <input type='text' id='fname' placeholder='Your Name' /> <br> "
                + " <button onclick='myFunction()'>Get Promo Code</button> "
                + " <h3 id='msg' style='color:#E64A19; margin-top: 20px;'></h3> "
                + " </body> </html>";


        wv1.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);


        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);


        wv1.getSettings().setDomStorageEnabled(true);

        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        return view;
    }
}