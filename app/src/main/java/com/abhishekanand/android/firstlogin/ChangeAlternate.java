package com.abhishekanand.android.firstlogin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ChangeAlternate extends ActionBarActivity {

    UserdbHelper2 userdbHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText e1,e2,e3,e4;
    Cursor cursor;
    String a1,a2,a3,a4,p,p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_alternate);
    }

    public void change_alt_password(View view){
        p="ranDoMdATa";
        p1="ranDoMdATa";
        userdbHelper=new UserdbHelper2(getApplicationContext());
        sqLiteDatabase=userdbHelper.getWritableDatabase();
        e1=(EditText)findViewById(R.id.old_password);
        e2=(EditText)findViewById(R.id.new_username);
        e3=(EditText)findViewById(R.id.new_password);
        e3=(EditText)findViewById(R.id.cnf_password);
        a1=e1.getText().toString();
        a2=e2.getText().toString();
        a3=e3.getText().toString();
        a4=e3.getText().toString();
        a1=a1.trim();
        a2=a2.trim();
        a3=a3.trim();
        a4=a4.trim();
        cursor=userdbHelper.getpass(sqLiteDatabase);
        if(cursor.moveToFirst()){
            do{
                p=cursor.getString(0);
            }while (cursor.moveToNext());
        }
        cursor=userdbHelper.getpass2(sqLiteDatabase);
        if(cursor.moveToFirst()) {
            do {
                p1 = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        if(p.equals(a1)||p1.equals(a1)){
            if(a3.equals(a4)) {
                if (a3.length() >= 4 && a2.length() >=1) {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(UserContract.NewUserInfo.COL11, a2);
                    contentValues.put(UserContract.NewUserInfo.COL12, a3);
                    sqLiteDatabase.update(UserContract.NewUserInfo.TABLE3, contentValues, UserContract.NewUserInfo.COL13 + "= 1", null);
                    Toast.makeText(getBaseContext(), "Password changed", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent=new Intent(ChangeAlternate.this, Categories_accounts.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getBaseContext(), "Password Too Short", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(getBaseContext(), "Password Confirm Failed", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getBaseContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
        }
        e1.setText("");
        e2.setText("");
        e3.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_alternate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(ChangeAlternate.this,Categories_accounts.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChangeAlternate.this, Categories_accounts.class);
        startActivity(intent);
        finish();


    }
}
