package com.abhishekanand.android.firstlogin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class Categories_accounts extends ActionBarActivity {

    static int t;
    static int z, check_search = 0;
    String[] countries = {"1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1","1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1","1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1","1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1"};

    SQLiteDatabase sqLiteDatabase;
    UserdbHelper userdbHelper;
    Cursor cursor;
    AutoCompleteTextView actv;
    static String element;

    private ListView mListView;

    protected ListView getListView() {
        if (mListView == null) {
            mListView = (ListView) findViewById(android.R.id.list);
        }
        return mListView;
    }

    protected void setListAdapter(ListAdapter adapter) {
        getListView().setAdapter(adapter);
    }

    protected ListAdapter getListAdapter() {
        ListAdapter adapter = getListView().getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            return ((HeaderViewListAdapter)adapter).getWrappedAdapter();
        } else {
            return adapter;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_accounts);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest request = new AdRequest.Builder()
                .build();
        mAdView.loadAd(request);

        String[] items = {"E-mail", "Social Network", "E-shop", "Travel & Bookings", "Computer Login", "Other Accounts", "Quick Access"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, items);
        getListView().setAdapter(adapter);
        actv = (AutoCompleteTextView) findViewById(R.id.autocomplete_search);
        actv.setAdapter(new ArrayAdapter<String>(this, R.layout.list_detail, countries));
        mListView=(ListView) findViewById(android.R.id.list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(Categories_accounts.this, MainActivity.class);
                startActivity(intent);
                t=position;
                finish(); }
        });

//-----------AutoComplete List--------------------------------------------------------------------------------

        userdbHelper = new UserdbHelper(getApplicationContext());
        sqLiteDatabase = userdbHelper.getReadableDatabase();

        cursor = userdbHelper.getInformation(sqLiteDatabase);
        Categories_accounts a = new Categories_accounts();
        if (cursor.moveToFirst()) {
            int i = 0;
            do {

                String name, mob, email, id;
                int n,loop_count,repeat=0;
                n = cursor.getInt(2);

                name = cursor.getString(0);
                for(loop_count=0;loop_count<=40;loop_count++)
                {
                    if(countries[loop_count].equals(name))
                        repeat=1;
                }
                if(repeat==0) {
                    countries[i] = name;
                    i++;
                }
                else {
                    repeat=0;
                }
                id = String.valueOf(cursor.getInt(1));
                mob = cursor.getString(3);
                for(loop_count=0;loop_count<=40;loop_count++)
                {
                    if(countries[loop_count].equals(mob))
                        repeat=1;
                }
                if(repeat==0) {
                    countries[i] = mob;
                    i++;
                }
                else {
                    repeat=0;
                }
                email = cursor.getString(4);
                for(loop_count=0;loop_count<=40;loop_count++)
                {
                    if(countries[loop_count].equals(email))
                        repeat=1;
                }
                if(repeat==0) {
                    countries[i] = email;
                    i++;
                }
                else{
                    repeat=0;
                }
            } while (cursor.moveToNext());
        }
        check_search=0;
    }

    public void search(View view){
        Intent intent=new Intent(Categories_accounts.this,SearchResult.class);
        startActivity(intent);
        check_search=1;
        actv = (AutoCompleteTextView) findViewById(R.id.autocomplete_search);
        element=actv.getText().toString();
        finish();
    }

    public int check(){
        return check_search;
    }
    public String getElement(){
        return element;
    }






    public int asa() {
        return t;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories_accounts, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id){
            case R.id.action_settings:
                Intent intent=new Intent(Categories_accounts.this,ChangePassword.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.alter_login:
                Intent intent1=new Intent(Categories_accounts.this,ChangeAlternate.class);
                startActivity(intent1);
                finish();
                return true;
            case R.id.backup:

                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setMessage("BackUp Data? Your file will be saved in FIRSTLOGIN_DATABASE directory!");
                builder1.setCancelable(true);

                builder1.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        try {
                            File sd = Environment.getExternalStorageDirectory();
                            File data = Environment.getDataDirectory();

                            if (sd.canWrite()) {
                                File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"FIRSTLOGIN_DATABASE");
                                directory.mkdirs();

                                String currentDBPath = "//data//"+ "com.abhishekanand.android.firstlogin" +"//databases//"+ "USER_INFO.DB";
                                String backupDBPath = "//FIRSTLOGIN_DATABASE//" + "USER_INFO.DB";
                                File currentDB = new File(data, currentDBPath);
                                File backupDB = new File(sd, backupDBPath);

                                FileChannel src = new FileInputStream(currentDB).getChannel();
                                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                                dst.transferFrom(src, 0, src.size());
                                src.close();
                                dst.close();
                                Toast.makeText(getApplicationContext(), "Backup Successful!",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Storage Permission Error!!!",
                                        Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Backup Failed!", Toast.LENGTH_SHORT)
                                    .show();
                        }

                    }
                });
                builder1.show();

                return true;
            case R.id.restore:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to Restore previous Data.This will erase your present Data?");
                builder.setCancelable(true);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        try {
                            File sd = Environment.getExternalStorageDirectory();
                            File data = Environment.getDataDirectory();
                            if (sd.canWrite()) {
                                String currentDBPath = "//data//" + "com.abhishekanand.android.firstlogin"
                                        + "//databases//" + "USER_INFO.DB";
                                String backupDBPath = "//FIRSTLOGIN_DATABASE//" + "USER_INFO.DB";
                                File backupDB = new File(data, currentDBPath);
                                File currentDB = new File(sd, backupDBPath);

                                FileChannel src = new FileInputStream(currentDB).getChannel();
                                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                                dst.transferFrom(src, 0, src.size());
                                src.close();
                                dst.close();
                                Toast.makeText(getApplicationContext(), "Restore Successful!",
                                        Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(), "Restore Failed!", Toast.LENGTH_SHORT)
                                    .show();

                        }


                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                return true;

            case R.id.about_us:
                Intent intent3=new Intent(Categories_accounts.this,AboutUs.class);
                startActivity(intent3);
                finish();
                return true;
                }
        return super.onOptionsItemSelected(item);
    }
}