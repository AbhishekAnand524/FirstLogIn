package com.abhishekanand.android.firstlogin;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;




public class AlternateLogin extends ActionBarActivity {


    UserdbHelper2 userdbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    EditText altUser,altPass;
    String a1,a2,p1,p2;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternate_login);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder()
                .build();
        mAdView.loadAd(request);

    }



    public void check_password2(View view){
        userdbHelper=new UserdbHelper2(getApplicationContext());
        sqLiteDatabase=userdbHelper.getReadableDatabase();
        cursor=userdbHelper.getpass2(sqLiteDatabase);
        altUser=(EditText)findViewById(R.id.altUsername);
        altPass=(EditText)findViewById(R.id.altPassword);
        a1=altUser.getText().toString();
        a1=a1.trim();
        a2=altPass.getText().toString();
        a2=a2.trim();
        p1="blAnk";
        p2="blAnk";
        if(cursor.moveToFirst()){
            do{
                p1=cursor.getString(0);
                p2=cursor.getString(1);
            }while (cursor.moveToNext());
        }
        if(p1.equals(a1)&& p2.equals(a2)){
            Intent intent=new Intent(AlternateLogin.this,Categories_accounts.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(getBaseContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
            altUser.setText("");
            altPass.setText("");
            Log.e("chc",p1);
            Log.e("chc",p2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alternate_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(AlternateLogin.this,LoginPassword.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(AlternateLogin.this,LoginPassword.class);
        startActivity(intent);
        finish();
    }


}
