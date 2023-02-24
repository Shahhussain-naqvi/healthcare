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

public class labtest extends AppCompatActivity {
    Button myorders,back;
    ListView list;
    SimpleAdapter adapt;
    ArrayList lists;
    HashMap<String, String> item;
    private String[][] packages={
            {"PACKAGE 1: E.C.G ","1100"},
            {"PACKAGE 2: Echocardiography ","4800"},
            {"PACKAGE 3: HAV Total Antibody ","2600"},
            {"PACKAGE 4: Transferrin ","600"},
            {"PACKAGE 5: IGf -1 (Insulin-like growth factor 1)", "7200"},
            {"PACKAGE 6: IGRA	","15600"},
            {"PACKAGE 7: Gamma-Glutamyl Transpeptidase ","660"},
            {"PACKAGE 8: Albumin-to-creatinine ratio (ACR) ","1050"},
            {"PACKAGE 9: Anti SARS-CoV-2 IgG,IgM ","3500"},
            {"PACKAGE 10: Anti HCV (Hepatitis C Antibody) Screening ","1050"},
            {"PACKAGE 11: COVID-19","5000"}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest);
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

        adapt= new SimpleAdapter(this,lists,R.layout.labtest_adap,new String[]{"name","price"},
                new int[]{R.id.t_name, R.id.t_price});

        list.setAdapter(adapt);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(labtest.this,test_1.class);
                intent.putExtra("name",packages[i][0]);
                intent.putExtra("price",packages[i][1]);
                startActivity(intent);
            }
        });
        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(labtest.this,myorders.class) );
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(labtest.this,Home.class));
            }
        });
    }
}