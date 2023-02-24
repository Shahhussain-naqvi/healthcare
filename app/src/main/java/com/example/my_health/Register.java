package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText r_email,r_username,r_phone,r_password,r_Cpassword;
    Button register_button;
    TextView login_nu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        r_email = findViewById(R.id.r_email);
        r_username = findViewById(R.id.med_name);
        r_phone = findViewById(R.id.baddress);
        r_password = findViewById(R.id.med_price);
        r_Cpassword = findViewById(R.id.r_Cpassword);
        register_button = findViewById(R.id.add_cart_med);
        login_nu = findViewById(R.id.registered);
        login_nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = r_username.getText().toString();
                String email = r_email.getText().toString();
                String phone = r_phone.getText().toString();
                String password = r_password.getText().toString();
                String Cpassword = r_Cpassword.getText().toString();
                Database db = new Database(getApplicationContext(),"myhealth",null,1);
                if (username.length() == 0 || password.length() == 0 || email.length() == 0 || phone.length() == 0|| Cpassword.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill the complete information", Toast.LENGTH_SHORT).show();
                }else{
                    if (password.compareTo(Cpassword)==0){
                            if( valid(password)){
                                db.register(username,email,phone,password);
                            Toast.makeText(getApplicationContext(), "REGISTERED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,Login.class));
                        }
                            else{
                            Toast.makeText(getApplicationContext(), "PASSWORD MUST CONTAIN AT LEAST 8 CHARACTERS , HAVING LETTERS AND DIGITS", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "CONFIRM PASSWORD DIDN'T MATCH", Toast.LENGTH_SHORT).show();
                    }
                    }
            }



        });

    }
    public static boolean valid(String password){
        int failure1=0,failure2=0;

        if(password.length()<8){
            return false;
        }
        else{
            for (int d=0 ;  d< password.length();d++){
                if (Character.isDigit(password.charAt(d))){
                    failure1 = 1;
                }}
            for (int l=0 ;  l< password.length();l++){
                if (Character.isLetter(password.charAt(l))){
                    failure2 = 1;
                }}
            if(failure1 == 1 && failure2 == 1){


                return true;
            }
            return false;

        }

    }
}