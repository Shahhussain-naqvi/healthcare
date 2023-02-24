package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class medicines extends AppCompatActivity {

    Button myorders,back;
    ListView list;
    SimpleAdapter adapt;
    ArrayList lists;
    HashMap<String, String> item;
    private String[][] packages={{"Xalatan Eye Drop 2.5ml ","1691"},
            {"Cosopt Eye Drop 5ml ","1405 "},
            {"Acefyl 125ml Syp " , "107 "},
            {"Acenac Sr Tablets 200mg 30's " , "470 "},
            {"Augmentin Suspension 156.25mg 90ml " , "192 "},
            {"Babynol Cough Syrup 60ml " , "58"},


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);
        list =findViewById(R.id.art_list);
        myorders =findViewById(R.id.tomyorders_med);
        back =findViewById(R.id.art_back);
        lists = new ArrayList();
        for(int i = 0 ; i < packages.length;i++){
            item = new HashMap<String, String>();
            item.put("name",packages[i][0]);
            item.put("price","PRICE = "+packages[i][1]);
            lists.add(item);
        }

        adapt= new SimpleAdapter(this,lists,R.layout.medicine_adap,new String[]{"name","price"},
                new int[]{R.id.m_name, R.id.m_price});

        list.setAdapter(adapt);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(medicines.this,medicine.class);
                intent.putExtra("name",packages[i][0]);
                intent.putExtra("price",packages[i][1]);
                startActivity(intent);
            }
        });
        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medicines.this,myorders.class) );
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medicines.this,Home.class));
            }
        });
    }
}