package com.abhishekanand.android.firstlogin;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class LoginPassword extends ActionBarActivity {

    UserdbHelper2 userdbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    TextView t;
    EditText e;
    String a,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_password);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder()
                .build();
        mAdView.loadAd(request);
    }

    public void start_altLogIn(View view){
        Intent intent=new Intent(LoginPassword.this,AlternateLogin.class);
        startActivity(intent);
        finish();
    }

    public void check_password(View view){
        userdbHelper=new UserdbHelper2(getApplicationContext());
        sqLiteDatabase=userdbHelper.getReadableDatabase();
        cursor=userdbHelper.getpass(sqLiteDatabase);
        e=(EditText)findViewById(R.id.password_onelogin);
        a=e.getText().toString();
        a=a.trim();
        p="rAndOmKeY";
        if(cursor.moveToFirst()){
            do{
                p=cursor.getString(0);
            }while (cursor.moveToNext());
        }
        if(p.equals(a)){
            Intent intent=new Intent(LoginPassword.this,Categories_accounts.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(getBaseContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
            e.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
