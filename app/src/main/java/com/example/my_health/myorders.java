package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class myorders extends AppCompatActivity {
    Button checkout,back;
    ListView cartdetails;
    TextView cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);
        cartdetails = findViewById(R.id.cartdetails);
        checkout = findViewById(R.id.checkout);
        back= findViewById(R.id.backtomain);
        cost= findViewById(R.id.totalcost);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        String type = sharedPreferences.getString("username","").toString();

        Database db = new Database(getApplicationContext(),"myhealth",null,1);
        float totalamount = 0;
        ArrayList data= db.getcartdata(username);
       // Toast.makeText(getApplicationContext(),""+data, Toast.LENGTH_LONG).show();
        ArrayAdapter adapt = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,data);

        cartdetails.setAdapter(adapt);

        for (int i = 0 ; i <data.size();i++){
            String d = (String) data.get(i).toString();
            String[] c = d.split(java.util.regex.Pattern.quote("$"));
            int k = 0;
            System.out.println(c[1]
            );
            totalamount = totalamount + Float.parseFloat(c[1]);

        }
        System.out.println(totalamount);
        cost.setText("TOTAL COST IS " +totalamount);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.removecart(username);
                Toast.makeText(myorders.this, "SUCCESSFULLY ORDERED", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(myorders.this,Home.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(myorders.this,Home.class));
            }
        });
    }
}