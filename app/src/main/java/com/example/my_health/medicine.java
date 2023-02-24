package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class medicine extends AppCompatActivity {
    TextView mname,mprice;
    Button add_cart,bback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        mname = findViewById(R.id.med_name);
        mprice = findViewById(R.id.med_price);
        add_cart = findViewById(R.id.add_cart_med);
        bback = findViewById(R.id.back_button_med);
        Intent intent=getIntent();
        String name= intent.getStringExtra("name");
        String price= intent.getStringExtra("price");
        mname.setText(name);
        mprice.setText(price);

        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medicine.this,medicines.class));
            }
        });

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product= mname.getText().toString();
                float price= Float.parseFloat(intent.getStringExtra("price").toString());
                Database db = new Database(getApplicationContext(),"myhealth",null,1);
                if (db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addingtocart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(), "ADDED TO THE CART", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(medicine.this,medicines.class));
                }

            }
        });
    }
}