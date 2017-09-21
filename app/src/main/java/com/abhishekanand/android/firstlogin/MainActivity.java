package com.abhishekanand.android.firstlogin;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserdbHelper userdbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    static int f;
    TextView t1;

    public void Add_contents(View view) {
        Intent intent = new Intent(MainActivity.this, AddContent.class);
        startActivity(intent);
        finish();
    }

    public int fsa() {
        return f;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.List_view);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);
        userdbHelper = new UserdbHelper(getApplicationContext());
        sqLiteDatabase = userdbHelper.getReadableDatabase();

        cursor = userdbHelper.getInformation(sqLiteDatabase);
        Categories_accounts a = new Categories_accounts();
        if (cursor.moveToFirst()) {
            do {

                String name, mob, email, id;
                int n;
                n = cursor.getInt(2);
                if (n == a.asa()) {
                    name = cursor.getString(0);
                    if(name.equals("")) {
                        name = "(no title)";
                        if(a.asa()==3)
                            name="(no card number)";
                    }
                    id = String.valueOf(cursor.getInt(1));
                    mob = cursor.getString(3);
                    if(mob.equals("")) {
                        mob = "(no username)";
                        if(a.asa()==3)
                            mob="(no cardholder's name)";
                    }
                    email = cursor.getString(4);
                    if(email.equals("")) {
                        email = "(no company or website)";
                        if(a.asa()==3)
                            email="(no cvv number)";
                        if(a.asa()==4)
                            email="(no pc details)";
                    }
                    DataProvider dataProvider = new DataProvider(name, mob, email, id);
                    listDataAdapter.add(dataProvider);
                }

            } while (cursor.moveToNext());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AccDetails.class);
                startActivity(intent);
                f = position + 1;
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(MainActivity.this,Categories_accounts.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, Categories_accounts.class);
        startActivity(intent);
        finish();


    }
}