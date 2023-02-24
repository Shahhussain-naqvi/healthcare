package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText login_username ,login_password;
    Button login_button;
    TextView register_nu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        login_username= findViewById(R.id.r_email);
        login_password= findViewById(R.id.r_Cpassword);
        login_button= findViewById(R.id.add_cart_med);
        register_nu= findViewById(R.id.registered);
        Database db = new Database(getApplicationContext(),"myhealth",null,1);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//startActivity(new Intent(Login.this,Home.class));
               String username = login_username.getText().toString();
                String password = login_password.getText().toString();
                if (username.length() == 0 || password.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill the complete information", Toast.LENGTH_SHORT).show();
                }else{
                    if(db.login(username,password)==1){
                        Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.apply();
                        startActivity(new Intent(Login.this,Home.class));
                    }
                else{Toast.makeText(getApplicationContext(), "INVALID USERNAME AND PASSWORD", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    register_nu.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Login.this,Register.class));
                }
            }
    );
    }
}