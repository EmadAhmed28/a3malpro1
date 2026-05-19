package com.example.a3malpro;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class activity_final extends AppCompatActivity {

    Connection conn;
    Statement stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();


            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://SQL5105.site4now.net/db_ac8a18_mady", "db_ac8a18_mady_admin", "E12345678!");
            stat = conn.createStatement();


            SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            String nn = loginData.getString("userName", "Guest"); // إذا ما لقى اسم بيحط Guest


            String query = "insert into coffee_order (custname,total) values ( '" + nn + "', '" + MenuFragment.tot + "' ) ";
            int rs = stat.executeUpdate(query);


            query = "select * from coffee_order where custname= '" + nn + "' order by id desc";
            ResultSet re = stat.executeQuery(query);
            Integer idd = 0;
            if (re.next()) {
                idd = re.getInt("id");
            }


            for (int i = 0; i < MenuFragment.buys.size(); i++) {
                MenuFragment.Buy item = MenuFragment.buys.get(i);


                query = "insert into coffee_orderline (orderid,itemname,itemquant,itemprice) values ( '" + idd + "' ,'" + item.itemname + "' ,'" + item.itemquant + "','" + item.itemprice + "') ";
                rs = stat.executeUpdate(query);


                query = "update coffee_products set quantity = quantity - '" + item.itemquant + "' where title = '" + item.itemname + "'";
                rs = stat.executeUpdate(query);
            }
            conn.close();


            TextView tv = (TextView) findViewById(R.id.txtfinal);
            tv.setText("Thank You For your Purchase");

        } catch (Exception e) {
            Toast.makeText(activity_final.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}