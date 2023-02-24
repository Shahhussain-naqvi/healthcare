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

public class test_1 extends AppCompatActivity {
    TextView test_name,test_price;
    Button add_cart,bback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        test_name = findViewById(R.id.med_name);
        test_price = findViewById(R.id.med_price);
        add_cart = findViewById(R.id.add_cart_med);
        bback = findViewById(R.id.back_button_med);
        Intent intent=getIntent();
        String name= intent.getStringExtra("name");
        String price= intent.getStringExtra("price");
        test_name.setText(name);
        test_price.setText(price);

        bback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(test_1.this,labtest.class));
            }
        });

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product= test_name.getText().toString();
                float price= Float.parseFloat(intent.getStringExtra("price").toString());
                Database db = new Database(getApplicationContext(),"myhealth",null,1);
                if (db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addingtocart(username,product,price,"lab test");
                    Toast.makeText(getApplicationContext(), "ADDED TO THE CART", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(test_1.this,labtest.class));
                }

            }
        });
    }
}