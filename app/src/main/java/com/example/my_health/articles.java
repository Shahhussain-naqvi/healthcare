package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class articles extends AppCompatActivity {
    ListView art_list;
    Button art_back;
    SimpleAdapter adapt;
    String[] lists;
    HashMap<String, String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        art_list = findViewById(R.id.art_list);
        art_back = findViewById(R.id.art_back);

        lists= new String[]{"COVID-19", "SMOKING KILLS", "HEALTHY EATING"};

        ArrayAdapter adapt = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lists);
        art_list.setAdapter(adapt);
        art_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(articles.this,article.class);
                intent.putExtra("position",lists[position]);
                System.out.println(lists[position]);
                startActivity(intent);
            }
        });
        art_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(articles.this,Home.class));
            }
        });
    }
}