package com.abhishekanand.android.firstlogin;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SearchResult extends ActionBarActivity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserdbHelper userdbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    static int f;static int search_val=0;
    static int[] actual_position=new int[100];


    public int check_search(){
        return search_val;
    }

    public int search_position(){
        return f;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        listView = (ListView) findViewById(R.id.List_view2);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);
        userdbHelper = new UserdbHelper(getApplicationContext());
        sqLiteDatabase = userdbHelper.getReadableDatabase();

        cursor = userdbHelper.getInformation(sqLiteDatabase);
        Categories_accounts a = new Categories_accounts();
        int k=0,position_id=1;
        TextView t=(TextView)findViewById(R.id.no_results);

        if (cursor.moveToFirst()) {
            do {
                t.setText("");
                String element;
                String name, mob, email, id;
                int n;
                element=a.getElement();
                n = cursor.getInt(2);
                name = cursor.getString(0);
                id = String.valueOf(cursor.getInt(1));
                mob = cursor.getString(3);
                email = cursor.getString(4);
                if(element.equals(name)||element.equals(mob)||element.equals(email))
                {
                    DataProvider dataProvider = new DataProvider(name, mob, email, id);
                    listDataAdapter.add(dataProvider);
                    actual_position[k]=position_id;
                    k++;
                }
                position_id++;
            } while (cursor.moveToNext());
        }
        else
        {
            t.setText("No matches found!!!");
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                Intent intent = new Intent(SearchResult.this, AccDetails.class);
                startActivity(intent);
                f = actual_position[position];
                search_val=1;
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SearchResult.this, Categories_accounts.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(SearchResult.this,Categories_accounts.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
