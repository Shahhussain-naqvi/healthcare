package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class article extends AppCompatActivity {
    TextView name;
    ImageView imageView;
    Button back_a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        name=findViewById(R.id.topic_name);
        imageView=findViewById(R.id.imageView);
        back_a=findViewById(R.id.back_a);

        Intent intent=getIntent();
        String namet= intent.getStringExtra("position");
        name.setText(namet);
         if (namet.compareTo("COVID-19") == 0){
             imageView.setImageResource(R.drawable.covid);
        }
        if (namet.compareTo("SMOKING KILLS")==0){
            imageView.setImageResource(R.drawable.smoking);
        }
        if (namet.compareTo("HEALTHY EATING") == 0){
            imageView.setImageResource(R.drawable.water);
        }
        if (namet.compareTo("EXERCISE DAILY") == 0){
            imageView.setImageResource(R.drawable.exercise);
        }
        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(article.this,Home.class));
            }
        });

    }
}