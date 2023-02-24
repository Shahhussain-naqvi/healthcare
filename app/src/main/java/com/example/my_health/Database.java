package com.example.my_health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table users(username text ,email text,password text,phone text ) ";
        db.execSQL(query1);

        String query2 = "create table cart(username text ,product text,price float,type text ) ";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String username,String email,String phone,String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("phone",phone);
        cv.put("password",password);
        SQLiteDatabase db =getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username, String password){
        int match = 0;
        String [] str= new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cr = db.rawQuery("select * from users where username = ? and password = ?",str);
        if (cr.moveToFirst()){match=1;}
        return match;


    }

    public void addingtocart(String username,String product,float price,String type){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("type",type);
        SQLiteDatabase db =getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    public int checkcart(String username,String product){
        int result=0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cr = db.rawQuery("select * from cart where username = ? and product = ?",str);
        if (cr.moveToFirst()){result=1;}
        db.close();
        return  result;

    }

    public int removecart(String username){
        int result=0;
        String[] str = new String[1];
        str[0] = username;

        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username = ?",str);
        db.close();
        return  result;

    }

    public ArrayList getcartdata(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[1];

        str[0] = username;

        Cursor cr = db.rawQuery("select * from cart where username = ? ",str);
        if (cr.moveToFirst()){
            do {
                String product = cr.getString(1);
                String price = cr.getString(2);
                arr.add(product+"$"+price);
            }while(cr.moveToNext());

        }
        db.close();
        return arr;
    }

}
