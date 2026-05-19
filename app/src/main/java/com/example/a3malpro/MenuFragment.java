package com.example.a3malpro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MenuFragment extends Fragment {

    ListView list;
    Connection conn;
    Statement stat;
    public static Integer tot;

    ArrayList<CoffeeItem> coffeeList = new ArrayList<>();
    public static ArrayList<Buy> buys = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        list = view.findViewById(R.id.listView1);
        MaterialButton btnBuy = view.findViewById(R.id.button5);

        TextView tvAll = view.findViewById(R.id.tv_cat_all);
        TextView tvFeatured = view.findViewById(R.id.tv_cat_featured);
        TextView tvSingle = view.findViewById(R.id.tv_cat_single);
        TextView tvBlends = view.findViewById(R.id.tv_cat_blends);

        tvAll.setOnClickListener(v -> fetchCoffee("1"));
        tvFeatured.setOnClickListener(v -> fetchCoffee("2"));
        tvSingle.setOnClickListener(v -> fetchCoffee("3"));
        tvBlends.setOnClickListener(v -> fetchCoffee("4"));

        btnBuy.setOnClickListener(v -> showBuyDialog());

        fetchCoffee("1");

        return view;
    }

    private void showBuyDialog() {
        tot = 0;
        String ss = "";
        Integer subtot = 0;
        for (int i = 0; i < buys.size(); i++) {
            Buy item = buys.get(i);
            subtot = item.itemprice * item.itemquant;
            tot += subtot;
            ss += "\n" + item.itemname + "  " + item.itemquant + "x " + subtot + "SR";
        }
        ss += "\n\nTotal: " + tot + " SR";

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirm Purchase");
        builder.setMessage("Your Order: " + ss);


        builder.setPositiveButton("YES", (dialog, which) -> {

            Intent intent = new Intent(requireContext(), MainActivity3.class);
            startActivity(intent);
        });

        builder.setNegativeButton("NO", (dialog, which) -> {
            Toast.makeText(requireContext(), "Order Cancelled", Toast.LENGTH_SHORT).show();
        });

        builder.create().show();
    }

    private void fetchCoffee(String category) {
        coffeeList.clear();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://SQL5105.site4now.net/db_ac8a18_mady", "db_ac8a18_mady_admin", "E12345678!");
            stat = conn.createStatement();

            String query = "SELECT * FROM coffee_products WHERE cata = " + category;
            ResultSet rs = stat.executeQuery(query);

            while (rs.next()) {
                coffeeList.add(new CoffeeItem(rs.getString("Id"), rs.getString("title"), rs.getString("description"), rs.getString("image"), rs.getString("price")));
            }

            list.setAdapter(new ListViewCustomAdapter(requireActivity(), coffeeList));
            conn.close();

        } catch (Exception e) {
            Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public class CoffeeItem {
        String id, name, desc, image, price;
        public CoffeeItem(String id, String name, String desc, String image, String price) {
            this.id = id; this.name = name; this.desc = desc; this.image = image; this.price = price;
        }
    }

    public class Buy {
        String itemname;
        Integer itemprice, itemquant;
        public Buy(String name, Integer price, Integer quant) {
            this.itemname = name; this.itemprice = price; this.itemquant = quant;
        }
    }

    public class ViewHolder {
        ImageView imageView1;
    }

    public class ListViewCustomAdapter extends ArrayAdapter<CoffeeItem> {
        ViewHolder holder;
        private Activity context;

        public ListViewCustomAdapter(Activity context, ArrayList<CoffeeItem> items) {
            super(context, R.layout.item_coffee_row, items);
            this.context = context;
        }

        public View getView(int position, View view, ViewGroup parent) {
            holder = new ViewHolder();
            CoffeeItem item = coffeeList.get(position);
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.item_coffee_row, null, true);

            TextView itemTitle = rowView.findViewById(R.id.item);
            TextView itemDesc = rowView.findViewById(R.id.textView1);
            TextView pri = rowView.findViewById(R.id.textView2);
            ImageButton abtn = rowView.findViewById(R.id.addbtn);
            ImageButton rbtn = rowView.findViewById(R.id.removebtn);
            TextView quant = rowView.findViewById(R.id.txt1);

            itemTitle.setText(item.name);
            itemDesc.setText(item.desc);
            pri.setText(item.price + " SR");

            for (int i = 0; i < buys.size(); i++) {
                Buy selItem = buys.get(i);
                if (selItem.itemname.equals(itemTitle.getText().toString())) {
                    quant.setText(selItem.itemquant.toString());
                }
            }

            abtn.setOnClickListener(v -> {
                int qut = Integer.parseInt(quant.getText().toString()) + 1;
                quant.setText(String.valueOf(qut));
                String name = itemTitle.getText().toString();
                int flage = 0;
                for (int i = 0; i < buys.size(); i++) {
                    if (buys.get(i).itemname.equals(name)) {
                        buys.get(i).itemquant = qut;
                        flage = 1; break;
                    }
                }
                if (flage == 0) buys.add(new Buy(item.name, Integer.parseInt(item.price), qut));
            });

            rbtn.setOnClickListener(v -> {
                int qut = Integer.parseInt(quant.getText().toString()) - 1;
                if (qut >= 0) {
                    quant.setText(String.valueOf(qut));
                    String name = itemTitle.getText().toString();
                    for (int i = 0; i < buys.size(); i++) {
                        if (buys.get(i).itemname.equals(name)) {
                            buys.get(i).itemquant = qut; break;
                        }
                    }
                }
            });

            holder.imageView1 = rowView.findViewById(R.id.icon);


            int imageResource = context.getResources().getIdentifier(item.image, "drawable", context.getPackageName());
            if (imageResource != 0) {
                holder.imageView1.setImageResource(imageResource);
            } else {

                holder.imageView1.setImageResource(R.drawable.colombia_beans);
            }

            return rowView;
        }
    }
}