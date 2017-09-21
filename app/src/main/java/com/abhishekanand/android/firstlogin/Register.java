package com.abhishekanand.android.firstlogin;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class Register extends AppCompatActivity {

    UserdbHelper2 userdbHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText e1,e2,e3;
    String a1,a2,a3;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userdbHelper= new UserdbHelper2(getApplicationContext());
        sqLiteDatabase=userdbHelper.getWritableDatabase();

        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, LoginPassword.class);
            startActivity(intent);
            finish();
        } else {
            ed = pref.edit();

        }
    }

    public void acc_register(View view){
        e1=(EditText)findViewById(R.id.r_mainPass);
        e2=(EditText)findViewById(R.id.r_altUser);
        e3=(EditText)findViewById(R.id.r_altPass);
        a1=e1.getText().toString();
        a2=e2.getText().toString();
        a3=e3.getText().toString();
        a1=a1.trim();
        a2=a2.trim();
        a3=a3.trim();
        Log.e("check","pos1");
        if(a1.length()>=4 && a2.length()>=2 && a3.length()>=4){

            ContentValues contentValues=new ContentValues();
            contentValues.put(UserContract.NewUserInfo.COL1, a1);
            sqLiteDatabase.update(UserContract.NewUserInfo.TABLE2, contentValues, UserContract.NewUserInfo.COL2 + "= 1", null);

            ContentValues contentValues2=new ContentValues();
            contentValues2.put(UserContract.NewUserInfo.COL11, a2);
            contentValues2.put(UserContract.NewUserInfo.COL12, a3);
            sqLiteDatabase.update(UserContract.NewUserInfo.TABLE3, contentValues2, UserContract.NewUserInfo.COL13 + "= 1", null);
            Toast.makeText(getBaseContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
            ed.putBoolean("activity_executed", true);
            ed.commit();
            Intent intent=new Intent(Register.this, Categories_accounts.class);
            startActivity(intent);
            finish();
            }
        else{
            Toast.makeText(getBaseContext(), "Invalid Entry", Toast.LENGTH_LONG).show();
        }
    }
}
