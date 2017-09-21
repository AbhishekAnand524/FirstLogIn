package com.abhishekanand.android.firstlogin;


import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ChangePassword extends ActionBarActivity {


    UserdbHelper2 userdbHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText e1,e2,e3;
    Cursor cursor;
    String a1,a2,a3,p,p1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

    }

    public void change_password(View view){
        p="rAndOmKeywoRd";
        p1="rAndOmKeywoRd";
        userdbHelper= new UserdbHelper2(getApplicationContext());
        sqLiteDatabase=userdbHelper.getWritableDatabase();
        e1=(EditText)findViewById(R.id.old_password);
        e2=(EditText)findViewById(R.id.new_password);
        e3=(EditText)findViewById(R.id.cnf_password);
        a1=e1.getText().toString();
        a2=e2.getText().toString();
        a3=e3.getText().toString();
        a1=a1.trim();
        a2=a2.trim();
        a3=a3.trim();
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
            if(a2.equals(a3)) {
                if (a2.length() >= 4 ) {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(UserContract.NewUserInfo.COL1, a2);
                    sqLiteDatabase.update(UserContract.NewUserInfo.TABLE2, contentValues, UserContract.NewUserInfo.COL2 + "= 1", null);
                    Toast.makeText(getBaseContext(), "Password changed", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent=new Intent(ChangePassword.this, Categories_accounts.class);
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
        getMenuInflater().inflate(R.menu.menu_change_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(ChangePassword.this,Categories_accounts.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChangePassword.this, Categories_accounts.class);
        startActivity(intent);
        finish();


    }
}
